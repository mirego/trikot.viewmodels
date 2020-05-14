import UIKit
import TRIKOT_FRAMEWORK_NAME

public class Slider : UISlider {
    private var oldValue: Int32 = 0

    public var impactFeedbackStyle: FeedbackStyle? = .light

    public var sliderViewModel: SliderViewModel? {
        get { return trikotViewModel() }
        set(value) {
            viewModel = value
            guard let sliderViewModel = value else { return }
            self.minimumValue = Float(sliderViewModel.minValue)
            self.maximumValue = Float(sliderViewModel.maxValue)

            self.addTarget(self, action: #selector(Slider.onValueChanged), for: .valueChanged)
            observe(sliderViewModel.selectedValue) { [weak self] (value: Int32) in
                self?.value = Float(value)
            }
        }
    }

    @objc
    func onValueChanged(sender: UISlider) {
        guard let integerValue = Int32(String(format: "%.0f", self.value)) else { return }

        if integerValue != oldValue {
            if #available(iOS 10.0, *), let style = impactFeedbackStyle {
                UIImpactFeedbackGenerator(style: getFeedbackStyle(style: style)).impactOccurred()
            }
            oldValue = integerValue
            sliderViewModel?.setSelectedValue(value: integerValue)
        }
    }
    
    public enum FeedbackStyle {
        case light
        case medium
        case heavy
        case soft
        case rigid
    }
    
    @available(iOS 10.0, *)
    private func getFeedbackStyle(style: FeedbackStyle) -> UIImpactFeedbackGenerator.FeedbackStyle {
        switch style {
        case .light:
            return .light
        case .medium:
            return .medium
        case .heavy:
            return .heavy
        case .soft:
            if #available(iOS 13.0, *) {
                return .soft
            } else {
                return .light
            }
        case .rigid:
            if #available(iOS 13.0, *) {
                return .rigid
            } else {
                return .heavy
            }
        }
    }
}
