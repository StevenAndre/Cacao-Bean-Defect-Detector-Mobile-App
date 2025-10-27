package com.stevenandre.projects.ui.home

import androidx.lifecycle.ViewModel
import com.stevenandre.projects.models.HomeUiState
import com.stevenandre.projects.models.MenuIcon
import com.stevenandre.projects.models.MenuItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {


    fun processImage(imageBytes: ByteArray) {
        // Aquí procesas la imagen capturada
        println("Imagen capturada: ${imageBytes.size} bytes")

        // Navegar a pantalla de análisis o procesar
        // navigateToAnalysis(imageBytes)
    }


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMenuItems()
    }

    private fun loadMenuItems() {
        val items = listOf(
            MenuItem(
                id = "nuevo_analisis",
                title = "NUEVO ANÁLISIS",
                icon = MenuIcon.CAMERA,
                route = "nuevo_analisis"
            ),
            MenuItem(
                id = "historial",
                title = "HISTORIAL",
                icon = MenuIcon.HISTORY,
                route = "historial"
            ),
            MenuItem(
                id = "tipos_defectos",
                title = "TIPOS DE DEFECTOS",
                icon = MenuIcon.DEFECTS,
                route = "tipos_defectos"
            ),
            MenuItem(
                id = "configuracion",
                title = "CONFIGURACIÓN",
                icon = MenuIcon.SETTINGS,
                route = "configuracion"
            )
        )
        _uiState.value = _uiState.value.copy(menuItems = items)
    }

    fun onMenuItemClick(menuItem: MenuItem) {

    }
}