package com.mirego.trikot.viewmodels

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.mirego.trikot.streams.reactive.asLiveData
import com.mirego.trikot.streams.reactive.observe

object PickerViewModelBinder {
    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("view_model", "lifecycleOwnerWrapper")
    fun bind(
        picker: Spinner,
        pickerViewModel: PickerViewModel<*>?,
        lifecycleOwnerWrapper: LifecycleOwnerWrapper
    ) {
        pickerViewModel?.let { viewModel ->
            viewModel.elements.observe(lifecycleOwnerWrapper.lifecycleOwner) { list ->
                picker.adapter =
                    ArrayAdapter(picker.context, android.R.layout.simple_spinner_item, list.map { it.displayName })
            }
            viewModel.selectedValueIndex.observe(lifecycleOwnerWrapper.lifecycleOwner) {
                picker.setSelection(it)
            }
            viewModel.enabled
                .asLiveData()
                .observe(lifecycleOwnerWrapper.lifecycleOwner) { picker.isEnabled = it }

            picker.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.elements.observe(lifecycleOwnerWrapper.lifecycleOwner) {
                        viewModel.setSelectedValueIndex(position)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // NO-OP
                }
            }
        }
    }
}
