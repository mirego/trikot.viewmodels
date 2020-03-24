import UIKit
import TRIKOT_FRAMEWORK_NAME

protocol ViewModelButton: ViewModelView {
    associatedtype ViewModelType = ButtonViewModel
}

extension UIButton: ViewModelButton {
    private struct AssociatedKeys {
        static var actionKey = UnsafeMutablePointer<Int8>.allocate(capacity: 1)
    }

    fileprivate var tapAction: ViewModelAction? {
        get {
            return objc_getAssociatedObject(self, AssociatedKeys.actionKey) as? ViewModelAction
        }
        set {
            objc_setAssociatedObject(self, AssociatedKeys.actionKey, newValue, objc_AssociationPolicy.OBJC_ASSOCIATION_RETAIN)
        }
    }

    fileprivate func setColoredAttributedTitle(_ attributedString: NSAttributedString, _ colorSelector: Any?, forState state: UIControl.State) {
        if let color = (colorSelector as? Color)?.safeColor() {
            let coloredAttribuedString = NSMutableAttributedString(attributedString: attributedString)
            coloredAttribuedString.addAttribute(.foregroundColor, value: color, range: NSRange(location: 0, length: attributedString.length))
            self.setAttributedTitle(coloredAttribuedString, for: state)
        }
    }

    public func positionSubviews(_ alignment: Alignment?) {
        guard let alignment = alignment, [Alignment.right, Alignment.left].contains(alignment) else { resetAlignment() ; return }

        observe(viewModel.imageResource.first()) {[weak self] (imageSelector: StateSelector) in

            guard let titleLabel = self?.titleLabel, let image = ImageViewModelResourceManager.shared.image(fromResource: imageSelector.defaultValue() as ImageResource?) else { return }

            titleLabel.sizeToFit()

            var titleValue: CGFloat = 0
            var imageValue: CGFloat = 0
            self?.contentHorizontalAlignment = .center
            switch alignment {
            case .right:
                titleValue = -(image.size.width)
                imageValue = titleLabel.bounds.width
            case .left:
                self?.contentHorizontalAlignment = .left
            default:
                break
            }
            self?.imageEdgeInsets = UIEdgeInsets(top: 0, left: imageValue, bottom: 0, right: -imageValue)
            self?.titleEdgeInsets = UIEdgeInsets(top: 0, left: titleValue, bottom: 0, right: -titleValue)

            DispatchQueue.main.async {
                self?.superview?.setNeedsLayout()
            }
        }
    }

    func resetAlignment() {
        imageEdgeInsets = .zero
        titleEdgeInsets = .zero
    }

    func updateBackgroundColor(_ backgroundColorSelector: StateSelector) {
        if !isEnabled, let buttonBackgroundColor = (backgroundColorSelector.disabledValue() as Color?)?.safeColor() {
            backgroundColor = buttonBackgroundColor
        } else if isHighlighted, let buttonBackgroundColor = (backgroundColorSelector.highlightedValue() as Color?)?.safeColor() {
            backgroundColor = buttonBackgroundColor
        } else if isSelected, let buttonBackgroundColor = (backgroundColorSelector.selectedValue() as Color?)?.safeColor() {
            backgroundColor = buttonBackgroundColor
        } else if let buttonBackgroundColor = (backgroundColorSelector.defaultValue() as Color?)?.safeColor() {
            backgroundColor = buttonBackgroundColor
        }
    }

    func updateBackgroundColor() {
        observe(viewModel.backgroundColor.first()) {[weak self] (selector: StateSelector) in
            self?.updateBackgroundColor(selector)
        }
    }

    @objc
    fileprivate func onButtonTouchUp() {
        guard let tapAction = tapAction else { return }
        tapAction.execute(actionContext: self ?? nil)
    }
}

extension ViewModelButton where Self: UIButton {
    var viewModel: ButtonViewModel {
        get { return trikotViewModel()!! }
        set {
            var view = self as UIButton
            removeTarget(self, action: #selector(onButtonTouchUp), for: .touchUpInside)
            view.viewModel = newValue
            trikotInternalPublisherCancellableManager.add(cancellable: KeyValueObservationHolder(view.observe(\UIButton.isHighlighted) { (button, _) in
                button.updateBackgroundColor()
            }))

            trikotInternalPublisherCancellableManager.add(cancellable: KeyValueObservationHolder(view.observe(\UIButton.isSelected) { (button, _) in
                button.updateBackgroundColor()
            }))

            trikotInternalPublisherCancellableManager.add(cancellable: KeyValueObservationHolder(view.observe(\UIButton.isEnabled) { (button, _) in
                button.updateBackgroundColor()
            }))

            observe(newValue.action) { [weak self](tapAction: ViewModelAction) in
                guard let self = self else { return }
                self.tapAction = tapAction
                if tapAction == ViewModelAction.Companion().None {
                    self.removeTarget(self, action: #selector(self.onButtonTouchUp), for: .touchUpInside)
                } else {
                    self.addTarget(self, action: #selector(self.onButtonTouchUp), for: .touchUpInside)
                }
            }

            bind(newValue.enabled, \UIButton.isEnabled)

            bind(newValue.selected, \UIButton.isSelected)

            observe(newValue.backgroundImageResource) {[weak self] (imageResourceSelector: StateSelector) in
                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageResourceSelector.default_ as? ImageResource)) {
                    self?.setBackgroundImage(image, for: .normal)
                }

                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageResourceSelector.selected as? ImageResource)) {
                    self?.setBackgroundImage(image, for: .selected)
                }

                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageResourceSelector.disabled as? ImageResource)) {
                    self?.setBackgroundImage(image, for: .disabled)
                }

                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageResourceSelector.highlighted as? ImageResource)) {
                    self?.setBackgroundImage(image, for: .highlighted)
                }
            }

            observe(newValue.backgroundColor) {[weak self] (selector: StateSelector) in
                self?.updateBackgroundColor(selector)
            }

            let imageResourceCombineLatest = CombineLatest.Companion().combine2(pub1: newValue.imageResource, pub2: newValue.tintColor)
            observe(imageResourceCombineLatest) { [weak self] (result: CombineLatestResult2) in
                guard let imageSelector = result.component1() as? StateSelector,
                    let tintSelector = result.component2() as? StateSelector
                    else { return }

                var tintColor = (tintSelector.defaultValue() as Color?)?.safeColor()
                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageSelector.defaultValue() as ImageResource?)) {
                    self?.setImage(tintColor != nil ? image.imageWithTintColor(tintColor!) : image, for: .normal)
                }

                tintColor = (tintSelector.selectedValue() as Color?)?.safeColor()
                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageSelector.selectedValue() as ImageResource?)) {
                    self?.setImage(tintColor != nil ? image.imageWithTintColor(tintColor!) : image, for: .selected)
                }

                tintColor = (tintSelector.highlightedValue() as Color?)?.safeColor()
                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageSelector.highlightedValue() as ImageResource?)) {
                    self?.setImage(tintColor != nil ? image.imageWithTintColor(tintColor!) : image, for: .highlighted)
                }

                tintColor = (tintSelector.disabledValue() as Color?)?.safeColor()
                if let image = ImageViewModelResourceManager.shared.image(fromResource: (imageSelector.disabledValue() as ImageResource?)) {
                    self?.setImage(tintColor != nil ? image.imageWithTintColor(tintColor!) : image, for: .disabled)
                }
            }

            if let richText = newValue.richText {
                let observable = CombineLatestProcessorExtensionsKt.combine(newValue.textColor, publishers: [richText])

                observe(observable) {[weak self] (value: [Any?]) in
                    let colorSelector = value[0] as! StateSelector
                    let richText = value[1] as! RichText

                    guard let self = self else { return }
                    let font = self.titleLabel?.font ?? UIFont.systemFont(ofSize: 12)
                    let attributedString = self.richTextToAttributedString(richText, referenceFont: font)

                    self.setColoredAttributedTitle(attributedString, colorSelector.default_, forState: .normal)
                    self.setColoredAttributedTitle(attributedString, colorSelector.selected, forState: .selected)
                    self.setColoredAttributedTitle(attributedString, colorSelector.disabled, forState: .disabled)
                    self.setColoredAttributedTitle(attributedString, colorSelector.highlighted, forState: .highlighted)
                }
            } else {
                observe(newValue.text) {[weak self] (string: String) in
                    self?.setTitle(string, for: .normal)
                }

                observe(newValue.textColor) {[weak self] (colorSelector: StateSelector) in
                    if let color = (colorSelector.default_ as? Color)?.safeColor() {
                        self?.setTitleColor(color, for: .normal)
                    }
                    if let color = (colorSelector.selected as? Color)?.safeColor() {
                        self?.setTitleColor(color, for: .selected)
                    }
                    if let color = (colorSelector.disabled as? Color)?.safeColor() {
                        self?.setTitleColor(color, for: .disabled)
                    }
                    if let color = (colorSelector.highlighted as? Color)?.safeColor() {
                        self?.setTitleColor(color, for: .highlighted)
                    }
                }
            }

            observe(newValue.imageAlignment) {[weak self] (alignment: Alignment) in
                self?.positionSubviews(alignment)
            }
        }
    }
}
