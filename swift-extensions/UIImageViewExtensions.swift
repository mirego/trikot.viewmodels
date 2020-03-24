import UIKit
import TRIKOT_FRAMEWORK_NAME

private let USER_CONTENT_MODE_KEY = UnsafeMutablePointer<Int8>.allocate(capacity: 1)
private let USER_PLACEHOLDER_CONTENT_MODE_KEY = UnsafeMutablePointer<Int8>.allocate(capacity: 1)
private let USER_IMAGERESOURCE_CONTENT_MODE_KEY = UnsafeMutablePointer<Int8>.allocate(capacity: 1)

protocol ViewModelImage: ViewModelView {
    associatedtype ViewModelType = ImageViewModel
}

extension UIImageView: ViewModelImage {

    func downloadImageIfNeeded(_ cancellableManager: CancellableManager) {
        observeImageFlow(cancellableManager: cancellableManager, imageFlowPublisher: viewModel.imageFlow(width: Int32(frame.width) * 2, height: Int32(frame.height) * 2))
    }

    func observeImageFlow(cancellableManager: CancellableManager, imageFlowPublisher: Publisher) {
        DispatchQueue.main.async {[weak self] in
            guard let self = self else { return }
            let cancellableManagerProvider = CancellableManagerProvider()
            cancellableManager.add(cancellable: cancellableManagerProvider)

            self.observe(cancellableManager: cancellableManager, publisher: self.viewModel.imageFlow(width: Int32(self.frame.width) * 2, height: Int32(self.frame.height) * 2)) {[weak self] (imageFlow: ImageFlow) in
                self?.doLoadImageFlow(cancellableManager: cancellableManagerProvider.cancelPreviousAndCreate(), imageFlow: imageFlow)
            }
        }
    }

    func doLoadImageFlow(cancellableManager: CancellableManager, imageFlow: ImageFlow) {
        var unProcessedImage: UIImage?
        if let imageResource = imageFlow.imageResource {
            self.contentMode = imageResourceContentMode
            unProcessedImage = ImageViewModelResourceManager.shared.image(fromResource: imageResource)
        }
        if let imageResource = imageFlow.placeholderImageResource {
            self.contentMode = placeholderContentMode
            unProcessedImage = ImageViewModelResourceManager.shared.image(fromResource: imageResource)
        }
        if let unProcessedImage = unProcessedImage {
            if let tintColor = imageFlow.tintColor {
                self.image = unProcessedImage.imageWithTintColor(tintColor.color())
            } else {
                self.image = unProcessedImage
            }
        }

        downloadImageFlowIfNeeded(cancellableManager: cancellableManager, imageFlow: imageFlow)
    }

    func downloadImageFlowIfNeeded(cancellableManager: CancellableManager, imageFlow: ImageFlow) {
        guard let url = imageFlow.url, let URL = URL(string: url) else { return }
        MrFreeze().freeze(objectToFreeze: cancellableManager)

        URLSession.shared.dataTask(with: URL) {[weak self] data, response, error in
            if let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                let data = data, error == nil,
                let image = UIImage(data: data) {

                DispatchQueue.main.async {
                    guard let self = self else { return }
                    self.contentMode = objc_getAssociatedObject(self, USER_CONTENT_MODE_KEY) as? ContentMode ?? .scaleToFill
                    self.image = image

                    if let onSuccess = imageFlow.onSuccess {
                        self.observeImageFlow(cancellableManager: cancellableManager, imageFlowPublisher: onSuccess)
                    }
                }
            } else if let onError = imageFlow.onError {
                DispatchQueue.main.async {
                    self?.observeImageFlow(cancellableManager: cancellableManager, imageFlowPublisher: onError)
                }
            }
        }.resume()
    }

    public var imageResourceContentMode: ContentMode {
        set(value) {
            objc_setAssociatedObject(self, USER_IMAGERESOURCE_CONTENT_MODE_KEY, value, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN)
        }
        get {
            return objc_getAssociatedObject(self, USER_IMAGERESOURCE_CONTENT_MODE_KEY) as? ContentMode ?? .center
        }
    }

    public var placeholderContentMode: ContentMode {
        set(value) {
            objc_setAssociatedObject(self, USER_PLACEHOLDER_CONTENT_MODE_KEY, value, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN)
        }
        get {
            return objc_getAssociatedObject(self, USER_PLACEHOLDER_CONTENT_MODE_KEY) as? ContentMode ?? .center
        }
    }
}

extension ViewModelImage where Self: UIImageView {
    var viewModel: ImageViewModel {
        get { return trikotViewModel()!! }
        set {
            var view = self as UIView
            view.viewModel = newValue

            let cancellableManagerProvider = CancellableManagerProvider()

            downloadImageIfNeeded(cancellableManagerProvider.cancelPreviousAndCreate())

            let sizeObservationCancellation = KeyValueObservationHolder(view.observe(\UIImageView.bounds, options: [.old, .new]) {[weak self] (_, change) in
                if change.newValue?.size != change.oldValue?.size { self?.downloadImageIfNeeded(cancellableManagerProvider.cancelPreviousAndCreate()) }
            })

            trikotInternalPublisherCancellableManager.add(cancellable: sizeObservationCancellation)
            trikotInternalPublisherCancellableManager.add(cancellable: cancellableManagerProvider)

            objc_setAssociatedObject(self, USER_CONTENT_MODE_KEY, contentMode, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN)
        }
    }
}
