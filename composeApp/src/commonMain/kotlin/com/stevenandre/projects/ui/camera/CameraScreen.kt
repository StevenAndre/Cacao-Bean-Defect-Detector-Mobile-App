package com.stevenandre.projects.ui.camera

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.stevenandre.projects.CameraController

@Composable
fun CameraScreen() {
    val cameraController = remember { CameraController() }
    var frameInfo by remember { mutableStateOf("Esperando frames...") }
    var isProcessing by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        cameraController.startCamera { frameData ->
            // Aquí procesas cada frame
            isProcessing = true
            processFrame(frameData)
            frameInfo = "Frame procesado: ${frameData.size} bytes"
            isProcessing = false
        }

        onDispose {
            cameraController.stopCamera()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Vista previa de la cámara
        CameraPreview(cameraController)

        // Información superpuesta
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = frameInfo,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    if (isProcessing) {
                        Spacer(modifier = Modifier.height(8.dp))
                        LinearProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
expect fun CameraPreview(controller: CameraController)

// Función para procesar frames (personalízala según tus necesidades)
fun processFrame(frameData: ByteArray) {
    // Aquí puedes hacer:
    // - Detección de objetos
    // - Reconocimiento facial
    // - Análisis de colores
    // - OCR
    // - etc.

    // Ejemplo básico: analizar brillo promedio
    val brightness = frameData.take(100).map { it.toInt() and 0xFF }.average()
    println("Brillo promedio del frame: $brightness")
}