import UIKit
import TRIKOT_FRAMEWORK_NAME

public struct TextAppearanceAttributes {
    let font: UIFont
    let foregroundColor: UIColor
    let backgroundColor: UIColor
}

public protocol TextAppearanceViewModelResourceProvider {
    func textAppearance(fromResource resource: TextAppearanceResource) -> TextAppearanceAttributes?
}

class DefaultTextAppearanceViewModelResourceProvider: TextAppearanceViewModelResourceProvider {
    func textAppearance(fromResource resource: TextAppearanceResource) -> TextAppearanceAttributes? {
        nil
    }
}

public class TextAppearanceViewModelResourceManager {
    public static var shared: TextAppearanceViewModelResourceProvider = DefaultTextAppearanceViewModelResourceProvider()
}
