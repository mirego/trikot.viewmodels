package com.trikot.viewModels.sample.viewModels.home

import com.mirego.trikot.viewModels.properties.Color
import com.mirego.trikot.viewModels.properties.ViewModelAction
import com.mirego.trikot.viewModels.properties.InputTextType
import com.mirego.trikot.viewModels.properties.StateSelector
import com.mirego.trikot.streams.reactive.just
import com.trikot.viewModels.sample.viewModels.ListItemViewModel
import com.trikot.viewModels.sample.viewModels.MutableHeaderListItemViewModel
import com.trikot.viewModels.sample.viewModels.MutableInputTextListItemViewModel
import com.trikot.viewModels.sample.navigation.NavigationDelegate

class InputTextViewModel(navigationDelegate: NavigationDelegate) : ListViewModel {
    override val items: List<ListItemViewModel> = listOf(
        MutableHeaderListItemViewModel(".backgroundColor"),
        MutableInputTextListItemViewModel().also {
            it.inputText.backgroundColor = StateSelector(Color(255, 0, 0)).just()
        },
        MutableHeaderListItemViewModel(".alpha"),
        MutableInputTextListItemViewModel().also {
            it.inputText.alpha = 0.5f.just()
        },
        MutableHeaderListItemViewModel(".hidden"),
        MutableInputTextListItemViewModel().also {
            it.inputText.hidden = true.just()
        },
        MutableHeaderListItemViewModel(".onTap"),
        MutableInputTextListItemViewModel().also {
            it.inputText.action = ViewModelAction { navigationDelegate.showAlert("Tapped $it") }.just()
        },
        MutableHeaderListItemViewModel(".placeholder"),
        MutableInputTextListItemViewModel().also {
            it.inputText.placeholderText = "Placeholder text".just()
        },
        MutableHeaderListItemViewModel(".textColor"),
        MutableInputTextListItemViewModel().also {
            it.inputText.textColor = Color(255, 0, 0).just()
        },
        MutableHeaderListItemViewModel(".inputType = password"),
        MutableInputTextListItemViewModel().also {
            it.inputText.inputType = InputTextType.PASSWORD.just()
        },
        MutableHeaderListItemViewModel(".inputType = number decimal"),
        MutableInputTextListItemViewModel().also {
            it.inputText.inputType = InputTextType.NUMBER_DECIMAL.just()
        }
    )
}
