package com.stevenandre.projects

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import app_defectos_cacao.composeapp.generated.resources.Res
import app_defectos_cacao.composeapp.generated.resources.compose_multiplatform
import com.stevenandre.projects.navigation.Navigation
import com.stevenandre.projects.ui.home.HomeScreen
import com.stevenandre.projects.ui.home.HomeViewModel


@Composable
@Preview
fun App() {

        val homeViewModel: HomeViewModel = viewModel { HomeViewModel() }
    CacaoAITheme{


        Navigation(
            homeViewModel = homeViewModel
        )

        }

}