import UIKit
import TRIKOT_FRAMEWORK_NAME

public protocol ImageViewModelResourceProvider {
    func image(fromResource resource: NDImageResource?) -> UIImage?
}

class DefaultImageViewModelResourceProvider: ImageViewModelResourceProvider {
    func image(fromResource resource: NDImageResource?) -> UIImage? {
        return nil
    }
}

public class ImageViewModelResourceManager {
    public static var shared: ImageViewModelResourceProvider = DefaultImageViewModelResourceProvider()
}
