package com.stevenandre.projects.models

data class HomeUiState(
    val userName: String = "Usuario",
    val menuItems: List<MenuItem> = emptyList()
)