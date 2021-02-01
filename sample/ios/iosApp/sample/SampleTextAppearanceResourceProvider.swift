import Trikot_viewmodels
import ViewModelsSample

class SampleTextAppearanceResourceProvider: TextAppearanceViewModelResourceProvider {
    func textAppearance(fromResource resource: TextAppearanceResource) -> TextAppearanceAttributes? {
        if let sampleResource = resource as? SampleTextAppearanceResource {
            switch sampleResource {
            case .textAppearanceBold:
                return TextAppearanceAttributes(font: UIFont.boldSystemFont(ofSize: 12), foregroundColor: UIColor.black)
            case .textAppearanceItalic:
                return TextAppearanceAttributes(font: UIFont.italicSystemFont(ofSize: 12), foregroundColor: UIColor.black)
            case .textAppearanceColored:
                return TextAppearanceAttributes(font: UIFont.systemFont(ofSize: 12), foregroundColor: UIColor.green)
            case .textAppearanceGrayed:
                return TextAppearanceAttributes(font: UIFont.systemFont(ofSize: 12), foregroundColor: UIColor.gray)
            case .textAppearanceHighlighted:
                return TextAppearanceAttributes(font: UIFont.systemFont(ofSize: 12), foregroundColor: UIColor.black, backgroundColor: UIColor.yellow)
            case .textAppearanceBold:
                return TextAppearanceAttributes(font: UIFont.systemFont(ofSize: 12), foregroundColor: UIColor.black, backgroundColor: UIColor.yellow)
            case .textAppearanceSuperscript:
                return TextAppearanceAttributes(font: UIFont.systemFont(ofSize: 7), foregroundColor: UIColor.black)
            default:
                return TextAppearanceAttributes(font: UIFont.systemFont(ofSize: 12), foregroundColor: UIColor.black)
            }
        } else {
            return TextAppearanceAttributes(font: UIFont.systemFont(ofSize: 12), foregroundColor: UIColor.black)
        }
    }
}
