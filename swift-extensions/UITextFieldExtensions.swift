import UIKit
import TRIKOT_FRAMEWORK_NAME

protocol ViewModelTextField: ViewModelView {
    associatedtype ViewModelType = InputTextViewModel
}

extension UITextField: ViewModelTextField {
    @objc
    fileprivate func onEditingChanged() {
        viewModel.setUserInput(value: text ?? "")
    }
}

extension ViewModelTextField where Self: UITextField {
    var viewModel: InputTextViewModel {
        get { return trikotViewModel()!! }
        set {
            var view = self as UIView
            removeTarget(self, action: #selector(onEditingChanged), for: .editingChanged)
            view.viewModel = newValue
            addTarget(self, action: #selector(onEditingChanged), for: .editingChanged)

            bindColor(newValue.textColor, \UITextField.textColor)

            observe(newValue.userInput) {[weak self] (text: String) in
                if self?.text != text {
                    self?.text = text
                }
            }

            observe(newValue.placeholderText) {[weak self] (placeholder: String) in
                self?.placeholder = placeholder
            }

            observe(newValue.inputType) {[weak self] (inputType: InputTextType) in
                switch inputType {
                case InputTextType.email:
                    self?.autocapitalizationType = .none
                    self?.keyboardType = .emailAddress
                    self?.isSecureTextEntry = false
                case InputTextType.text:
                    self?.keyboardType = .default
                    self?.isSecureTextEntry = false
                case InputTextType.password:
                    self?.keyboardType = .default
                    self?.isSecureTextEntry = true
                case InputTextType.phoneNumber:
                    self?.keyboardType = .phonePad
                    self?.isSecureTextEntry = false
                case InputTextType.number:
                    self?.keyboardType = .numberPad
                    self?.isSecureTextEntry = false
                case InputTextType.multiline:
                    self?.keyboardType = .default
                    self?.isSecureTextEntry = false
                default:
                    break
                }
            }

        }
    }
}
