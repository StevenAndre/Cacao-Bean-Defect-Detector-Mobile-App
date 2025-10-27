package com.stevenandre.projects.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import app_defectos_cacao.composeapp.generated.resources.Res
import app_defectos_cacao.composeapp.generated.resources.cacao_logo
import app_defectos_cacao.composeapp.generated.resources.compose_multiplatform
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.stevenandre.projects.CacaoColors
import com.stevenandre.projects.models.MenuIcon
import com.stevenandre.projects.models.MenuItem
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()


    val cameraLauncher = rememberImagePickerLauncher(
        selectionMode = SelectionMode.Single,
        scope = scope,
        onResult = { byteArrays ->
            byteArrays.firstOrNull()?.let { imageBytes ->

                viewModel.processImage(imageBytes)
            }
        }
    )

    Scaffold(
        containerColor = CacaoColors.Beige,
        bottomBar = { BottomNavigationBar() }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            CacaoLogo()
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Cacao Bean Scan",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = CacaoColors.Brown900
            )

            Text(
                text = "Análisis de Semillas",
                fontSize = 14.sp,
                color = CacaoColors.Brown700
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Hola, ${uiState.userName}",
                fontSize = 16.sp,
                color = CacaoColors.Brown700,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(24.dp))

            MenuGrid(
                items = uiState.menuItems,
                onItemClick = { item ->
                    when (item.icon) {
                        MenuIcon.CAMERA -> {
                            cameraLauncher.launch(

                            )
                        }
                        else -> viewModel.onMenuItemClick(item)
                    }
                }
            )
        }
    }
}

@Composable
private fun CacaoLogo() {
    Box(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CacaoColors.Brown700),
        contentAlignment = Alignment.Center
    ) {


        Image(painter = painterResource(Res.drawable.cacao_logo)
        , contentDescription = ""
            , modifier = Modifier.size(100.dp)
        )


    }
}

@Composable
private fun MenuGrid(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.chunked(2).forEach { rowItems ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                rowItems.forEach { item ->
                    MenuCard(
                        item = item,
                        onClick = { onItemClick(item) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun MenuCard(
    item: MenuItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(CacaoColors.Brown700)
            .clickable(onClick = onClick)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MenuIconComponent(icon = item.icon)

            Text(
                text = item.title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = CacaoColors.Beige,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun MenuIconComponent(icon: MenuIcon) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CacaoColors.Brown900.copy(alpha = 0.3f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = when (icon) {
                MenuIcon.CAMERA -> "📷"
                MenuIcon.HISTORY -> "🕐"
                MenuIcon.DEFECTS -> "🌱"
                MenuIcon.SETTINGS -> "⚙️"
            },
            fontSize = 32.sp,
            color = CacaoColors.GreenLight
        )
    }
}

@Composable
private fun BottomNavigationBar() {
    NavigationBar(
        containerColor = CacaoColors.Beige,
        tonalElevation = 0.dp,
        modifier = Modifier.border(1.dp, CacaoColors.Brown900)
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = { Text("🏠", fontSize = 24.sp) },
            label = {
                Text(
                    "Inicio",
                    fontSize = 10.sp,
                    color = Color(255, 255, 255)
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CacaoColors.Brown900,
                unselectedIconColor = CacaoColors.Brown500,
                indicatorColor = CacaoColors.GreenDark.copy(alpha = 0.2f)
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("❓", fontSize = 24.sp) },
            label = {
                Text(
                    "Tutorial",
                    fontSize = 10.sp,
                    color = CacaoColors.Brown700
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CacaoColors.Brown900,
                unselectedIconColor = CacaoColors.Brown500
            )
        )

        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = { Text("👤", fontSize = 24.sp) },
            label = {
                Text(
                    "Perfil",
                    fontSize = 10.sp,
                    color = CacaoColors.Brown700
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CacaoColors.Brown900,
                unselectedIconColor = CacaoColors.Brown500
            )
        )
    }
}