package com.compose.timerwithcolor

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuLightGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import com.compose.timerwithcolor.ui.theme.TimerWithColorTheme
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerWithColorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MikuLightGreen,
                ) {

                    var isPressed by remember { mutableStateOf(false) }
                    var color by remember { mutableStateOf(MikuDarkGreen) }
                    var color1 by remember { mutableStateOf(MikuPink) }

                    LaunchedEffect(isPressed) {
                        while (isPressed) {
                            color = Color(
                                red = Random.nextInt(0..255),
                                blue = Random.nextInt(0..255),
                                green = Random.nextInt(0..255)
                            )
                            color1 = Color(
                                red = Random.nextInt(0..255),
                                blue = Random.nextInt(0..255),
                                green = Random.nextInt(0..255)
                            )

                            delay(1)
                        }
                    }

                    Box(
                        modifier = Modifier
                            .background(
                                color
                            )
                            .pointerInteropFilter { event ->
                                if (event.action == MotionEvent.ACTION_DOWN) {
                                    isPressed = true
                                } else if (event.action == MotionEvent.ACTION_UP) {
                                    isPressed = false
                                }
                                true
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        CurrentTime(color1)
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentTime(color: Color) {
    var now by remember { mutableStateOf(LocalDateTime.now()) }
    val formatter by remember { mutableStateOf(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) }

    LaunchedEffect(now) {
        while (true) {
            delay(1)
            now = LocalDateTime.now()
        }
    }

    Text(
        text = now.format(formatter),
        color = color
    )
}