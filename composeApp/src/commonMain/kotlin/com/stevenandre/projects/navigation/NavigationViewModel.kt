package com.stevenandre.projects.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationViewModel : ViewModel() {
    private val _navigationState = MutableStateFlow<NavigationState>(NavigationState.Idle)
    val navigationState = _navigationState.asStateFlow()

    private val _canNavigateBack = MutableStateFlow(false)
    val canNavigateBack = _canNavigateBack.asStateFlow()

    fun navigateTo(destination: String) {
        _navigationState.value = NavigationState.Navigate(destination)
    }

    fun navigateBack() {
        _navigationState.value = NavigationState.NavigateBack
    }

    fun clearNavigation() {
        _navigationState.value = NavigationState.Idle
    }

    fun updateBackStackState(canGoBack: Boolean) {
        _canNavigateBack.value = canGoBack
    }
}
sealed class NavigationState {
    data object Idle : NavigationState()
    data class Navigate(val destination: String) : NavigationState()
    data object NavigateBack : NavigationState()
}