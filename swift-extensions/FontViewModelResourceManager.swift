import UIKit
import TRIKOT_FRAMEWORK_NAME

public protocol FontViewModelResourceProvider {
    func font(fromResource resource: TextAppearanceResource) -> UIFont?
}

class DefaultFontViewModelResourceProvider: FontViewModelResourceProvider {
    func font(fromResource resource: TextAppearanceResource) -> UIFont? {
        nil
    }
}

public class FontViewModelResourceManager {
    public static var shared: FontViewModelResourceProvider = DefaultFontViewModelResourceProvider()
}
