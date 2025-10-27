package com.stevenandre.projects.ui.camera

import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.stevenandre.projects.CameraController

@Composable
actual fun CameraPreview(controller: CameraController) {
    val context = LocalContext.current

    AndroidView(
        factory = { controller.getPreviewView() as PreviewView },
        modifier = Modifier.fillMaxSize()
    )

}