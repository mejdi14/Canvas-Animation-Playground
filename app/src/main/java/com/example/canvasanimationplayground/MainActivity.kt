package com.example.canvasanimationplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.canvasanimationplayground.ui.theme.CanvasAnimationPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CanvasAnimationPlaygroundTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    // Adjust padding as needed to center the rectangle

                    // LaunchedEffect to handle the animation loop

                    for(i in 1..4) {
                    val rotationY = remember { Animatable((-(i * 45f))) } // Initial rotation
                        LaunchedEffect(i) {
                            kotlinx.coroutines.delay(1600L -(400L * i))
                            while (true) {
                                // Animate from 45f to 0
                                rotationY.animateTo(
                                    targetValue = -490f - (i * 45f),
                                    animationSpec = tween(durationMillis = 1000)
                                )
                                // Pause for 1 second
                                kotlinx.coroutines.delay(400L )
                                // Animate back from 0 to 45f
                                rotationY.animateTo(
                                    targetValue = (-(i * 45f)),
                                    animationSpec = tween(durationMillis = 1000)
                                )
                                // Pause for another 1 second
                                kotlinx.coroutines.delay(1000)
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(16.dp).size(100.dp)
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
                                    color = Color.Black,
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

@Composable
fun DrawRoundedSquare(size: Float, color: androidx.compose.ui.graphics.Color, cornerRadius: Float, strokeWidth: Float) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRoundRect(
            color = color,
            size = androidx.compose.ui.geometry.Size(50f, 50f),
            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
            style = Stroke(width = strokeWidth)
        )
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