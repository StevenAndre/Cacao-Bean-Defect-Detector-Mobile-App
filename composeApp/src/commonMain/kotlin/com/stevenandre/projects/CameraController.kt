package com.stevenandre.projects

expect class CameraController {



    fun startCamera(onFrameProcessed: (ByteArray) -> Unit)
    fun stopCamera()
    fun getPreviewView(): Any
}