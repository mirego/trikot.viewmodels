import UIKit
import TRIKOT_FRAMEWORK_NAME

extension UITextView {
    public var labelViewModel: LabelViewModel? {
        get { trikotViewModel() }
        set(value) {
            viewModel = value
            if let labelViewModel = value {
                bind(labelViewModel.text, \UITextView.text)
                bindColorSelectorDefaultValue(labelViewModel.textColor, \UITextView.textColor)
            }
        }
    }
}
