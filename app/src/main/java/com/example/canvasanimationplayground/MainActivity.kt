package com.example.canvasanimationplayground

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvasanimationplayground.ui.theme.CanvasAnimationPlaygroundTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasAnimationPlaygroundTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Black), color = Color.Black
                    ) {
                        for (i in 1..6) {
                            val colors = listOf(
                                Color(0xFFFA7070),
                                Color(0xFF7ABA78),
                                Color(0xFFF3CA52),
                                Color(0xFFCAE6B2),

                            )
                            val initialPositionOffset = 45f
                            val color = remember { Animatable(Color(0xFFFA7070)) }
                            val rotationY =
                                remember { Animatable((-(i * initialPositionOffset))) }
                            LaunchedEffect(i) {
                                delay(1200L - (200L * i))
                                while (true) {
                                    rotationY.animateTo(
                                        targetValue = -490f - (i * initialPositionOffset),
                                        animationSpec = tween(durationMillis = 1000)
                                    )
                                    delay(200L)
                                    rotationY.animateTo(
                                        targetValue = (-(i * initialPositionOffset)),
                                        animationSpec = tween(durationMillis = 1000)
                                    )
                                    delay(200)
                                }
                            }
                            LaunchedEffect(i) {
                                delay(400L - (100L * i))
                                while (true) {
                                    colors.forEach { targetColor ->
                                        color.animateTo(
                                            targetValue = targetColor,
                                            animationSpec = tween(400)
                                        )
                                        delay(180)
                                    }
                                }
                            }
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .size(100.dp)
                            ) {
                                Canvas(
                                    modifier = Modifier
                                        .graphicsLayer {

                                            rotationZ = 30f
                                            translationX = (-(i * 15f))
                                            translationY = rotationY.value
                                        }
                                ) {
                                    val strokeWidth = 2.dp.toPx()
                                    val cornerRadius = CornerRadius(20.dp.toPx(), 20.dp.toPx())
                                    drawRoundRect(
                                        color = color.value,
                                        size = Size(100f + (i * 80f), 100f + (i * 80f)),
                                        cornerRadius = cornerRadius,
                                        style = Stroke(width = strokeWidth)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CanvasAnimationPlaygroundTheme {
        Greeting("Android")
    }
}