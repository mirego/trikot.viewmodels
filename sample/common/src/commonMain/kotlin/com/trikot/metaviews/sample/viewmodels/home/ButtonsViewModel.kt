package com.trikot.metaviews.sample.viewmodels.home

import com.mirego.trikot.metaviews.properties.MetaAction
import com.mirego.trikot.metaviews.text.RichText
import com.mirego.trikot.metaviews.text.RichTextRange
import com.mirego.trikot.metaviews.text.StyleTransform
import com.mirego.trikot.streams.reactive.just
import com.trikot.metaviews.sample.metaviews.*
import com.trikot.metaviews.sample.navigation.NavigationDelegate

class ButtonsViewModel(navigationDelegate: NavigationDelegate): ListViewModel {
    override val items: List<MetaListItem> = listOf(
        MutableHeaderListItem("Button can have text"),
        MutableMetaButtonListItem().also {
            it.button.text = "The text is here".just()
        },
        MutableHeaderListItem("But can also have rich text"),
        MutableMetaButtonListItem().also {
            it.button.richText = RichText("normal, bold, underlined, italic, bold italic",
                listOf(
                    RichTextRange(IntRange(0, 8), StyleTransform(StyleTransform.Style.NORMAL)),
                    RichTextRange(IntRange(8, 14), StyleTransform(StyleTransform.Style.BOLD)),
                    RichTextRange(IntRange(14, 26), StyleTransform(StyleTransform.Style.UNDERLINE)),
                    RichTextRange(IntRange(26, 34), StyleTransform(StyleTransform.Style.ITALIC)),
                    RichTextRange(IntRange(34, 45), StyleTransform(StyleTransform.Style.BOLD_ITALIC))
                )).just()
        },
        MutableHeaderListItem("Can have alpha"),
        MutableMetaButtonListItem().also {
            it.button.alpha = 0.5f.just()
            it.button.text = "I have 50% alpha".just()
        },
        MutableHeaderListItem("Can be hidden"),
        MutableMetaButtonListItem().also {
            it.button.hidden = true.just()
            it.button.text = "You shall not see me".just()
        },
        MutableHeaderListItem("Can be tapped"),
        MutableMetaButtonListItem().also {
            it.button.onTap = MetaAction { navigationDelegate.showAlert("Tapped $it") }.just()
            it.button.text = "Tap me".just()
        },
        MutableHeaderListItem("Can be Disabled"),
        MutableMetaButtonListItem().also {
            it.button.enabled = false.just()
            it.button.text = "I am disabled".just()
        },
        MutableHeaderListItem("Can be selected"),
        MutableMetaButtonListItem().also {
            it.button.selected = true.just()
            it.button.text = "I am selected".just()
        },
        MutableHeaderListItem("Can have background resource (TODO ALL STATE)"),
        MutableHeaderListItem("Can have image resource (TODO ALL STATE)")
    )
}
