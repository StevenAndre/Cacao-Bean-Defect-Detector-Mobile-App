package com.stevenandre.projects.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Settings : Screen("settings")

    data class Profile(val userId: String = "{userId}") : Screen("profile/$userId") {
        fun createRoute(userId: String) = "profile/$userId"

        companion object {
            const val ROUTE_WITH_ARGS = "profile/{userId}"
        }
    }

    data class ProductDetail(val productId: String = "{productId}") : Screen("product/$productId") {
        fun createRoute(productId: String) = "product/$productId"

        companion object {
            const val ROUTE_WITH_ARGS = "product/{productId}"
        }
    }
}