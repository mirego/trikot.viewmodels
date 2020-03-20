package com.trikot.viewModels.sample.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trikot.viewModels.sample.R
import com.trikot.viewModels.sample.viewModels.*

class MetaListItemAdapter :
    DataBindingAdapter<ListItemViewModel>(diffCallback = DefaultDiffUtilCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is NavigableListItemViewModel -> R.layout.item_navigable
            is LabelListItemViewModel -> R.layout.item_label
            is HeaderListItemViewModel -> R.layout.item_header
            is ViewListItemViewModel -> R.layout.item_view
            is ButtonListItemViewModel -> R.layout.item_button
            is ImageListItemViewModel -> R.layout.item_image
            is InputTextListItemViewModel -> R.layout.item_input_text
            else -> TODO()
        }
    }
}

@BindingAdapter("items")
fun bind(recyclerView: RecyclerView, data: List<ListItemViewModel>?) {
    data?.let {
        recyclerView.adapter?.let { (it as MetaListItemAdapter).submitList(data) }
    }
}
