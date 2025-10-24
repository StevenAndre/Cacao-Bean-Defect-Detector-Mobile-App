package com.stevenandre.projects.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.min

@Composable
fun CacaoDefectsHome(
    onLiveAnalysis: () -> Unit = {},
    onUploadImage: () -> Unit = {},
    onBrowseSamples: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF07080E))
    ) {
        NeonAuroraBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 28.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Encabezado
            Column {
                Text(
                    text = "Cacao Bean Defect Detector",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFFE8E9FF)
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Análisis inteligente de defectos en granos de cacao",
                    fontSize = 14.sp,
                    color = Color(0xFFAAB0FF).copy(alpha = 0.85f)
                )
            }

            // Centro: Holo Scanner + Acciones
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HoloScanner(modifier = Modifier.size(260.dp))

                Spacer(Modifier.height(18.dp))

                Text(
                    "Listo para escanear tu siguiente lote",
                    color = Color(0xFFE8E9FF),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(22.dp))

                // Botones de acción
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    GlowButton(
                        text = "🚀 Iniciar análisis en vivo",
                        onClick = onLiveAnalysis,
                        width = 280.dp,
                        height = 52.dp
                    )
                    Spacer(Modifier.height(12.dp))
                    OutlineGlassButton(
                        text = "⬆️ Cargar imagen",
                        onClick = onUploadImage,
                        width = 280.dp,
                        height = 48.dp
                    )
                    Spacer(Modifier.height(4.dp))
                    TextButton(onClick = onBrowseSamples) {
                        Text("🔎 Ver muestras", color = Color(0xFFD0D4FF))
                    }
                }
            }

            // Métricas rápidas (glass chips)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                GlassStatChip(title = "Exactitud", value = "98.4%")
                GlassStatChip(title = "Defectos prom.", value = "4.2%")
                GlassStatChip(title = "Último lote", value = "120 granos")
            }
        }
    }
}

/* ---------- Fondo animado tipo aurora/neón ---------- */
@Composable
private fun NeonAuroraBackground() {
    val inf = rememberInfiniteTransition(label = "bg")
    val t1 by inf.animateFloat(
        initialValue = 0f, targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = tween(9000, easing = LinearEasing)),
        label = "t1"
    )
    val t2 by inf.animateFloat(
        initialValue = 1f, targetValue = 0f,
        animationSpec = infiniteRepeatable(animation = tween(12000, easing = LinearEasing)),
        label = "t2"
    )

    Canvas(Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height

        // Capa 1
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF6C63FF).copy(0.35f), Color.Transparent),
                center = Offset(w * (0.25f + 0.15f * t1), h * (0.35f + 0.1f * t2)),
                radius = min(w, h) * 0.6f
            ),
            radius = min(w, h) * 0.6f,
            center = Offset(w * (0.25f + 0.15f * t1), h * (0.35f + 0.1f * t2))
        )
        // Capa 2
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFF00E5FF).copy(0.28f), Color.Transparent),
                center = Offset(w * (0.75f - 0.2f * t2), h * (0.65f - 0.1f * t1)),
                radius = min(w, h) * 0.7f
            ),
            radius = min(w, h) * 0.7f,
            center = Offset(w * (0.75f - 0.2f * t2), h * (0.65f - 0.1f * t1))
        )
        // Velo general
        drawRect(
            brush = Brush.linearGradient(
                listOf(
                    Color(0xFF0B0E1A).copy(alpha = 0.85f),
                    Color(0xFF090B14).copy(alpha = 0.85f)
                ),
                start = Offset.Zero,
                end = Offset(w, h)
            ),
            size = size
        )
    }
}

/* ---------- Aro holográfico con barrido y pulso ---------- */
@Composable
private fun HoloScanner(modifier: Modifier = Modifier) {
    val inf = rememberInfiniteTransition(label = "scanner")
    val sweep by inf.animateFloat(
        initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(6000, easing = LinearEasing)),
        label = "sweep"
    )
    val pulse by inf.animateFloat(
        initialValue = 0.85f, targetValue = 1.15f,
        animationSpec = infiniteRepeatable(tween(1400, easing = FastOutSlowInEasing), RepeatMode.Reverse),
        label = "pulse"
    )

    var percent by remember { mutableIntStateOf(0) }
    var dir by remember { mutableIntStateOf(1) }


    LaunchedEffect(Unit) {
        while (true) {
            delay(100) // velocidad del conteo (ajústalo)
            percent += dir
            if (percent >= 100) dir = 0
            else if (percent <= 0) dir = 1
        }
    }


    val numberStyle = TextStyle(
        color = Color(0xFFE8E9FF),
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace
    )

    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()
    val maxWidthDp = remember(numberStyle, density, textMeasurer) {
        with(density) {
            textMeasurer
                .measure(AnnotatedString("100%"), style = numberStyle)
                .size.width
                .toDp()
        }
    }



    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val s = min(size.width, size.height)
            val r = s / 2f * 0.76f
            val center = Offset(size.width / 2f, size.height / 2f)

            // Disco tenue interior
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFF1B1F3B), Color(0xFF0D1122)),
                    center = center,
                    radius = r * 0.9f
                ),
                radius = r * 0.9f,
                center = center
            )

            // Aro base
            drawCircle(
                color = Color(0xFF3C3F66),
                center = center,
                radius = r,
                style = Stroke(width = 2.dp.toPx())
            )

            // Aro punteado (como retícula)
            drawCircle(
                color = Color(0xFF8A90FF).copy(alpha = 0.35f),
                center = center,
                radius = r * 0.7f * pulse,
                style = Stroke(
                    width = 1.5.dp.toPx(),
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 12f), phase = 0f)
                )
            )

            // Barrido holográfico
            drawArc(
                brush = Brush.sweepGradient(
                    listOf(
                        Color(0xFF00E5FF),
                        Color(0xFF6C63FF),
                        Color(0xFFFF00E5),
                        Color(0xFF00E5FF)
                    ),
                    center = center
                ),
                startAngle = sweep,
                sweepAngle = 270f,
                useCenter = false,
                topLeft = Offset(center.x - r, center.y - r),
                size = Size(r * 2, r * 2),
                style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
            )
        }

        // Label central
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.width(maxWidthDp)) {
                Text(
                    text = "$percent%",
                    style = numberStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text= if(percent==100)"Scanner Listo" else "Preparando Scanner…",
                color = Color(0xFFAAB0FF),
                fontSize = 12.sp
            )
        }
    }

}

/* ---------- Botón neon con gradiente ---------- */
@Composable
private fun GlowButton(
    text: String,
    onClick: () -> Unit,
    width: Dp,
    height: Dp
) {
    val gradient = Brush.linearGradient(
        colors = listOf(Color(0xFF6C63FF), Color(0xFF00E5FF))
    )
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .background(brush = gradient)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(Color.White.copy(0.65f), Color.Transparent)
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFF0A0D1A),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

/* ---------- Botón estilo vidrio (glass) ---------- */
@Composable
private fun OutlineGlassButton(
    text: String,
    onClick: () -> Unit,
    width: Dp,
    height: Dp
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFFFFFF).copy(alpha = 0.06f))
            .border(
                1.dp,
                Brush.linearGradient(
                    listOf(Color(0xFFB9BEFF).copy(0.6f), Color(0xFF5C63FF).copy(0.2f))
                ),
                RoundedCornerShape(16.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color(0xFFD0D4FF),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/* ---------- Chips de métrica con efecto glass ---------- */
@Composable
private fun GlassStatChip(title: String, value: String) {
    Column(
        modifier = Modifier

            .fillMaxWidth(1f)
            .clip(RoundedCornerShape(14.dp))
            .background(Color(0xFFFFFFFF).copy(alpha = 0.06f))
            .border(
                1.dp,
                Brush.linearGradient(
                    listOf(Color(0xFFB0B6FF).copy(0.5f), Color(0xFF7A80FF).copy(0.2f))
                ),
                RoundedCornerShape(14.dp)
            )
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        Text(title, color = Color(0xFFAAB0FF), fontSize = 12.sp)
        Spacer(Modifier.height(2.dp))
        Text(
            value,
            color = Color(0xFFE8E9FF),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
