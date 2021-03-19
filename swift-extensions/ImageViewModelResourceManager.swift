import UIKit
import ViewModelsSample

public protocol ImageViewModelResourceProvider {
    func image(fromResource resource: ImageResource?) -> UIImage?
}

class DefaultImageViewModelResourceProvider: ImageViewModelResourceProvider {
    func image(fromResource resource: ImageResource?) -> UIImage? {
        return nil
    }
}

public class ImageViewModelResourceManager {
    public static var shared: ImageViewModelResourceProvider = DefaultImageViewModelResourceProvider()
}
