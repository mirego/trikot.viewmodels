
import UIKit
import TRIKOT_FRAMEWORK_NAME

class Slider : UISlider {
    private var oldValue: Int32 = 0

    override init(frame: CGRect) {
        super.init(frame: frame)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    var sliderViewModel: SliderViewModel? {
        get { return trikotViewModel() }
        set(value) {
            viewModel = value
            if let sliderViewModel = value {
                self.minimumValue = Float(sliderViewModel.minValue)
                self.maximumValue = Float(sliderViewModel.maxValue)

                self.addTarget(self, action: #selector(UISlider.onValueChanged), for: .valueChanged)
                observe(sliderViewModel.selectedValue) { [weak self] (value: Int32) in
                    self?.slider.value = Float(value)
                }
            }
        }
    }

    @objc
    func onValueChanged(sender: UISlider) {
        guard let integerValue = Int32(String(format: "%.0f", self.value)) else { return }

        if integerValue != oldValue {
            UIImpactFeedbackGenerator(style: .light).impactOccurred()
            oldValue = integerValue
            sliderViewModel?.setSelectedValue(value: integerValue)
        }
    }
}

