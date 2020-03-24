import UIKit
import TRIKOT_FRAMEWORK_NAME

protocol ViewModelLabel: ViewModelView {
    associatedtype ViewModelType = LabelViewModel
}

extension UILabel: ViewModelLabel {}

extension ViewModelLabel where Self: UILabel {
    var viewModel: LabelViewModel {
        get { return trikotViewModel()!! }
        set {
            var view = self as UIView
            view.viewModel = newValue
            if let richText = newValue.richText {
                observe(richText) {[weak self] (richText: RichText) in
                    guard let self = self else { return }
                    self.attributedText = self.richTextToAttributedString(richText, referenceFont: self.font)
                }
            } else {
                bind(newValue.text, \UILabel.text)
            }

            bindColorSelectorDefaultValue(newValue.textColor, \UILabel.textColor)
        }
    }
}
