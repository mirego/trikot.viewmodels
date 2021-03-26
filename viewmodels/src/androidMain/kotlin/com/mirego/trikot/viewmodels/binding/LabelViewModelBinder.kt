package com.mirego.trikot.viewmodels.binding

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import com.mirego.trikot.streams.reactive.just
import com.mirego.trikot.streams.reactive.observe
import com.mirego.trikot.viewmodels.LabelViewModel
import com.mirego.trikot.viewmodels.extension.asSpannableString
import com.mirego.trikot.viewmodels.extension.toColorStateList
import com.mirego.trikot.viewmodels.extension.toIntColor
import com.mirego.trikot.viewmodels.lifecycle.LifecycleOwnerWrapper
import com.mirego.trikot.viewmodels.mutable.MutableLabelViewModel

object LabelViewModelBinder {

    private val NoLabelViewModel =
        MutableLabelViewModel().apply { hidden = true.just() } as LabelViewModel

    @JvmStatic
    @BindingAdapter("view_model", "hiddenVisibility", "lifecycleOwnerWrapper")
    fun bind(
        textView: TextView,
        labelViewModel: LabelViewModel?,
        hiddenVisibility: HiddenVisibility,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        val label = labelViewModel ?: NoLabelViewModel

        label.richText?.observe(lifecycleOwnerWrapper.lifecycleOwner) { richText ->
            textView.text = richText.asSpannableString(textView.context)
        }

        label.takeUnless { it.richText != null }?.text
            ?.observe(lifecycleOwnerWrapper.lifecycleOwner, textView::setText)

        label.textColor
            .observe(lifecycleOwnerWrapper.lifecycleOwner) { selector ->
                selector.default?.let {
                    textView.setTextColor(it.toIntColor())
                }
            }

        bindExtraViewProperties(textView, label, hiddenVisibility, lifecycleOwnerWrapper)
    }

    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        textView: TextView,
        labelViewModel: LabelViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        bind(textView, labelViewModel, HiddenVisibility.GONE, lifecycleOwnerWrapper)
    }

    @JvmStatic
    fun bindWithoutTextPublishers(
        textView: TextView,
        labelViewModel: LabelViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        bindExtraViewProperties(
            textView,
            labelViewModel ?: NoLabelViewModel,
            HiddenVisibility.GONE,
            lifecycleOwnerWrapper
        )
    }

    @JvmStatic
    private fun bindExtraViewProperties(
        textView: TextView,
        labelViewModel: LabelViewModel,
        hiddenVisibility: HiddenVisibility,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        labelViewModel.hidden.observe(lifecycleOwnerWrapper.lifecycleOwner) { hidden ->
            with(textView) { visibility = if (hidden) hiddenVisibility.value else View.VISIBLE }
        }

        labelViewModel.alpha.observe(lifecycleOwnerWrapper.lifecycleOwner, textView::setAlpha)

        labelViewModel.backgroundColor
            .observe(lifecycleOwnerWrapper.lifecycleOwner) { selector ->
                if (selector.isEmpty) {
                    return@observe
                }

                textView.background ?: run {
                    ViewCompat.setBackground(textView, ColorDrawable(Color.WHITE))
                }

                ViewCompat.setBackgroundTintList(textView, selector.toColorStateList())
            }

        textView.bindAction(labelViewModel, lifecycleOwnerWrapper)
    }
}

enum class HiddenVisibility(val value: Int) {
    GONE(View.GONE),
    INVISIBLE(View.INVISIBLE);
}
