import UIKit
import ViewModelsSample

class ButtonListItem: UIView {
    private var button: UIButton?

    var item: ButtonListItemViewModel? {
        didSet {
            if let button = button {
                button.removeFromSuperview()
            }
            recreateButton()
            viewModel = item
            button?.buttonViewModel = item?.button
        }
    }

    private func recreateButton() {
        let button = UIButton(frame: .zero)
        addSubview(button)
        button.translatesAutoresizingMaskIntoConstraints = false

        button.backgroundColor = #colorLiteral(red: 0.1764705882, green: 0.2196078431, blue: 0.6784313725, alpha: 1)
        button.setTitleColor(.yellow, for: .normal)
        button.setTitleColor(.red, for: .highlighted)
        button.setTitleColor(.blue, for: .disabled)
        button.setTitleColor(.green, for: .selected)
        button.titleLabel?.numberOfLines = 2
        button.titleLabel?.minimumScaleFactor = 0.4

        NSLayoutConstraint.activate([
            button.topAnchor.constraint(equalTo: topAnchor),
            button.bottomAnchor.constraint(equalTo: bottomAnchor),
            button.widthAnchor.constraint(equalToConstant: 200),
            button.heightAnchor.constraint(equalToConstant: 50),
            button.centerXAnchor.constraint(equalTo: centerXAnchor),
            widthAnchor.constraint(greaterThanOrEqualTo: button.widthAnchor)
        ])
        self.button = button
    }
}
