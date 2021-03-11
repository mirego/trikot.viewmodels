package com.mirego.trikot.viewmodels

import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.mirego.trikot.streams.reactive.observe

object PickerViewModelBinder {
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        picker: Spinner,
        pickerViewModel: PickerViewModel<PickerItemViewModel>?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        pickerViewModel?.let { viewModel ->
            viewModel.elements.observe(lifecycleOwnerWrapper.lifecycleOwner) {
                picker.adapter =
                    ArrayAdapter(picker.context, android.R.layout.simple_spinner_item, it)
            }
            viewModel.selectedValue.observe(lifecycleOwnerWrapper.lifecycleOwner) {
                picker.setSelection(it)
            }
            picker.setOnItemClickListener { _, _, position, _ ->
                viewModel.elements.observe(lifecycleOwnerWrapper.lifecycleOwner) {
                    viewModel.setSelectedValue(it[position])
                }
            }
            picker.bindViewModel(viewModel as ViewModel, lifecycleOwnerWrapper)
        }
    }
}
