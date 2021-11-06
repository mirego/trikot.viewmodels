package com.mirego.trikot.viewmodels

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import com.mirego.trikot.streams.reactive.just
import com.mirego.trikot.streams.reactive.observe
import com.mirego.trikot.viewmodels.mutable.NDMutableLabelViewModel
import com.mirego.trikot.viewmodels.utils.BindingUtils

object LabelViewModelBinder {

    private val NoLabelViewModel = NDMutableLabelViewModel().apply { hidden = true.just() } as NDLabelViewModel

    @JvmStatic
    @BindingAdapter("view_model")
    fun bind(
        textView: TextView,
        labelViewModel: NDLabelViewModel?
    ) {
        bind(textView, labelViewModel, BindingUtils.getLifecycleOwnerWrapperFromView(textView))
    }

    @JvmStatic
    @BindingAdapter("view_model", "hiddenVisibility", "lifecycleOwnerWrapper")
    fun bind(
        textView: TextView,
        labelViewModel: NDLabelViewModel?,
        hiddenVisibility: HiddenVisibility,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        val label = labelViewModel ?: NoLabelViewModel

        label.richText?.observe(lifecycleOwnerWrapper.lifecycleOwner) { richText ->
            textView.text = richText.asSpannableString(textView.context)
        }

        label.takeUnless { it.richText != null }?.text
            ?.observe(lifecycleOwnerWrapper.lifecycleOwner) {
                textView.text = it
            }

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
        labelViewModel: NDLabelViewModel?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        bind(textView, labelViewModel, HiddenVisibility.GONE, lifecycleOwnerWrapper)
    }

    @JvmStatic
    fun bindWithoutTextPublishers(
        textView: TextView,
        labelViewModel: NDLabelViewModel?,
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
        labelViewModel: NDLabelViewModel,
        hiddenVisibility: HiddenVisibility,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        labelViewModel.hidden.observe(lifecycleOwnerWrapper.lifecycleOwner) { hidden ->
            with(textView) { visibility = if (hidden) hiddenVisibility.value else View.VISIBLE }
        }

        labelViewModel.alpha.observe(lifecycleOwnerWrapper.lifecycleOwner) { alpha ->
            textView.alpha = alpha
        }

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
