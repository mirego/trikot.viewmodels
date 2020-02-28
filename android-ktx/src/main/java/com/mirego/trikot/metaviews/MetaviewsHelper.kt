package com.mirego.trikot.metaviews

import android.graphics.Typeface
import android.text.ParcelableSpan
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.View
import com.mirego.trikot.metaviews.text.ActionTransform
import com.mirego.trikot.metaviews.text.RichText
import com.mirego.trikot.metaviews.text.RichTextRange
import com.mirego.trikot.metaviews.text.StyleTransform
import com.mirego.trikot.streams.android.ktx.observe

fun RichText.asSpannableString(lifecycleOwnerWrapper: LifecycleOwnerWrapper): SpannableString {
    return SpannableString(text).apply {
        ranges.forEach {
            setSpan(
                it.asSpan(lifecycleOwnerWrapper),
                it.range.first,
                it.range.last,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }
}

private fun RichTextRange.asSpan(lifecycleOwnerWrapper: LifecycleOwnerWrapper): Any {
    return when (transform) {
        is StyleTransform -> {
            when ((transform as StyleTransform).style) {
                StyleTransform.Style.NORMAL -> StyleSpan(Typeface.NORMAL)
                StyleTransform.Style.BOLD -> StyleSpan(Typeface.BOLD)
                StyleTransform.Style.ITALIC -> StyleSpan(Typeface.ITALIC)
                StyleTransform.Style.UNDERLINE -> UnderlineSpan()
                StyleTransform.Style.BOLD_ITALIC -> StyleSpan(Typeface.BOLD_ITALIC)
            }
        }
        is ActionTransform -> {
            object: ClickableSpan() {
                override fun onClick(widget: View) {
                    (transform as ActionTransform).action.observe(lifecycleOwnerWrapper.lifecycleOwner) {
                        it.execute()
                    }
                }
            }
        }
        else -> TODO("RichTextRange $transform not implemented")
    }
}

