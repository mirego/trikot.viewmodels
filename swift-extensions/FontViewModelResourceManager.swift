import UIKit
import SportsAppCore

public protocol FontViewModelResourceProvider {
    func font(fromResource resource: TextAppearanceResource) -> UIFont?
}

class DefaultFontViewModelResourceProvider: FontViewModelResourceProvider {
    func font(fromResource resource: TextAppearanceResource) -> UIFont? {
        nil
    }
}

public class FontViewModelResourceManager {
    public static var shaded: FontViewModelResourceProvider = DefaultFontViewModelResourceProvider
}
