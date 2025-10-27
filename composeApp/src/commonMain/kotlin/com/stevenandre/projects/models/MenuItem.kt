package com.stevenandre.projects.models

data class MenuItem(
    val id: String,
    val title: String,
    val icon: MenuIcon,
    val route: String
)