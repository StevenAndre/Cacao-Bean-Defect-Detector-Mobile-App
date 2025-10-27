package com.stevenandre.projects

import androidx.compose.runtime.Composable


@Composable
actual fun RequestCameraPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
}