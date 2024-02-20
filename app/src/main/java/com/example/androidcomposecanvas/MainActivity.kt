package com.example.androidcomposecanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidcomposecanvas.ui.theme.AndroidComposeCanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeCanvasTheme {
                //Simple rectangle
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val rectSize = size / 2f
                    inset(horizontal = 50f, vertical = 100f) {
                        drawRect(
                            color = Color.Magenta,
                            size = rectSize
                        )
                    }


                    //Drawing a line
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawLine(
                        start = Offset(x = canvasWidth, y = 0f),
                        end = Offset(x = 0f, y = canvasHeight),
                        color = Color.Blue
                    )

                    //Scaling the drawing content
                    translate(left = 100f, top = 300f) {
                        scale(scaleX = 1f, scaleY = 1f){
                            drawCircle(color = Color.Blue, radius = 120.dp.toPx())
                        }
                    }

                        rotate(degrees = 34F) {
                        drawRect(
                            color = Color.Gray,
                            topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                            size = size / 3F
                        )
                    }
                }
                Spacer(
                    modifier = Modifier
                        .drawWithCache {
                            val path = Path()
                            path.moveTo(0f, 0f)
                            path.lineTo(x = size.width/2f,y=size.height)
                            path.lineTo(size.width, 0f)
                            path.close()
                            onDrawBehind {
                                drawPath(path, Color.Magenta, style = Stroke(width = 10f))
                            }
                        }
                        .fillMaxSize()
                )
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
    AndroidComposeCanvasTheme {
        Greeting("Android")
    }
}