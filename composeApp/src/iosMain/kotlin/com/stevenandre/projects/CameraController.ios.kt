package com.stevenandre.projects

actual class CameraController {
    actual fun startCamera(onFrameProcessed: (ByteArray) -> Unit) {
    }

    actual fun stopCamera() {
    }

    actual fun getPreviewView(): Any {
        TODO("Not yet implemented")
    }
}