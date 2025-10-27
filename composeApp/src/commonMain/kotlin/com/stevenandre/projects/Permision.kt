package com.stevenandre.projects

import androidx.compose.runtime.Composable

@Composable
expect fun RequestCameraPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
)