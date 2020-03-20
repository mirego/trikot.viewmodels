package com.trikot.viewModels.sample.viewModels.home

import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.ViewModelAction
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.viewModels.text.RichText
import com.mirego.trikot.viewModels.text.RichTextRange
import com.mirego.trikot.viewModels.text.StyleTransform
import com.mirego.trikot.streams.reactive.just
import com.trikot.viewModels.sample.viewModels.ListItemViewModel
import com.trikot.viewModels.sample.viewModels.MutableHeaderListItemViewModel
import com.trikot.viewModels.sample.viewModels.MutableLabelListItemViewModel
import com.trikot.viewModels.sample.navigation.NavigationDelegate

class LabelsViewModel(navigationDelegate: NavigationDelegate) : ListViewModel {
    override val items: List<ListItemViewModel> = listOf(
        MutableHeaderListItemViewModel(".backgroundColor"),
        MutableLabelListItemViewModel().also {
            it.label.text = "Text on red background".just()
            it.label.backgroundColor = StateSelector(Color(255, 0, 0)).just()
        },
        MutableHeaderListItemViewModel(".alpha"),
        MutableLabelListItemViewModel().also {
            it.label.alpha = 0.5f.just()
            it.label.text = "I have 50% alpha".just()
        },
        MutableHeaderListItemViewModel(".hidden"),
        MutableLabelListItemViewModel().also {
            it.label.hidden = true.just()
            it.label.text = "You shall not see me".just()
        },
        MutableHeaderListItemViewModel(".onTap"),
        MutableLabelListItemViewModel().also {
            it.label.action = ViewModelAction { navigationDelegate.showAlert("Tapped $it") }.just()
            it.label.text = "Tap me".just()
        },
        MutableHeaderListItemViewModel(".text"),
        MutableLabelListItemViewModel().also {
            it.label.text = "The text is here".just()
        },
        MutableHeaderListItemViewModel(".richText"),
        MutableLabelListItemViewModel().also {
            it.label.richText = RichText("normal, bold, underlined, italic, bold italic",
                listOf(
                    RichTextRange(IntRange(0, 8), StyleTransform(StyleTransform.Style.NORMAL)),
                    RichTextRange(IntRange(8, 14), StyleTransform(StyleTransform.Style.BOLD)),
                    RichTextRange(IntRange(14, 26), StyleTransform(StyleTransform.Style.UNDERLINE)),
                    RichTextRange(IntRange(26, 34), StyleTransform(StyleTransform.Style.ITALIC)),
                    RichTextRange(IntRange(34, 45), StyleTransform(StyleTransform.Style.BOLD_ITALIC))
                )).just()
        },
        MutableHeaderListItemViewModel(".textColor"),
        MutableLabelListItemViewModel().also {
            it.label.action = ViewModelAction { navigationDelegate.showAlert("Tapped $it") }.just()
            it.label.text = "I am red".just()
            it.label.textColor = StateSelector(Color(255, 0, 0)).just()
        }
    )
}
