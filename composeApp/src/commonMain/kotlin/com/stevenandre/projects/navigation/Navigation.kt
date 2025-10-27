package com.stevenandre.projects.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stevenandre.projects.ui.history.HistoryScreen
import com.stevenandre.projects.ui.home.HomeScreen
import com.stevenandre.projects.ui.home.HomeViewModel
import com.stevenandre.projects.ui.scan.ScanScreen


@Composable
fun Navigation(

    homeViewModel: HomeViewModel,


){

    val navController= rememberNavController()
    NavHost(navController=navController,
        startDestination = "home"
        ){

        composable("home"){
            HomeScreen(
                viewModel = homeViewModel,
                onScanClick = {
                    navController.navigate("scan")
                },
                onHistoryClick = {
                    navController.navigate("history")
                }
            )
        }

        composable ("scan"){
            ScanScreen()

        }

        composable("history") {
            HistoryScreen()
        }

    }

}