package com.stevenandre.projects.ui.scan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.stevenandre.projects.CameraView
import com.stevenandre.projects.RequestCameraPermission
import com.stevenandre.projects.ui.camera.CameraScreen


@Composable
fun ScanScreen(){




    CameraView()

}

@Composable
fun CameraScreenWithPermissions() {
    var permissionGranted by remember { mutableStateOf(false) }

    if (permissionGranted) {
        CameraScreen()
    } else {
        RequestCameraPermission(
            onPermissionGranted = { permissionGranted = true },
            onPermissionDenied = {

            }
        )
    }
}