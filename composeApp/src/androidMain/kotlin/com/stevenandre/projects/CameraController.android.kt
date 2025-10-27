package com.stevenandre.projects

import android.content.Context
import android.graphics.ImageFormat
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.nio.ByteBuffer
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

actual class CameraController (private val context: Context) {
    private val previewView = PreviewView(context)
    private var cameraExecutor: ExecutorService? = null
    private var imageAnalysis: ImageAnalysis? = null

    actual fun startCamera(onFrameProcessed: (ByteArray) -> Unit) {
        cameraExecutor = Executors.newSingleThreadExecutor()

        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            imageAnalysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor!!) { imageProxy ->
                        val buffer = imageProxy.planes[0].buffer
                        val data = ByteArray(buffer.remaining())
                        buffer.get(data)

                        onFrameProcessed(data)
                        imageProxy.close()
                    }
                }

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    context as LifecycleOwner,
                    cameraSelector,
                    preview,
                    imageAnalysis
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(context))
    }

    actual fun stopCamera() {
        cameraExecutor?.shutdown()
        imageAnalysis?.clearAnalyzer()
    }

    actual fun getPreviewView(): Any = previewView
}

