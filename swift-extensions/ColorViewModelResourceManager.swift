import UIKit
import TRIKOT_FRAMEWORK_NAME

public protocol ColorViewModelResourceProvider {
    func foregroundColor(fromResource resource: TextAppearanceResource) -> UIColor?
    func backgroundColor(fromResource resource: TextAppearanceResource) -> UIColor?
}

class DefaultColorViewModelResourceProvider: ColorViewModelResourceProvider {
    func foregroundColor(fromResource resource: TextAppearanceResource) -> UIColor? {
        nil
    }

    func backgroundColor(fromResource resource: TextAppearanceResource) -> UIColor? {
        nil
    }
}

public class ColorViewModelResourceManager {
    public static var shared: ColorViewModelResourceProvider = DefaultColorViewModelResourceProvider()
}
