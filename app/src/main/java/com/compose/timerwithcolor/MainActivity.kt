package com.compose.timerwithcolor

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuLightGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import com.compose.timerwithcolor.ui.theme.TimerWithColorTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : ComponentActivity() {

    /**
     * Set up the window to hide the status and navigation bars
     */
    private fun setupWindow() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    // Call this function in your activity's onCreate method
    private fun setFullscreen(activity: Activity) {
        // Hide the status and navigation bar
        setupWindow()

        // Check if we are in immersive mode. If not, enter it
        @Suppress("DEPRECATION")
        activity.window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_IMMERSIVE
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullscreen(this)
        setContent {
            TimerWithColorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MikuLightGreen,
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(modifier = Modifier.weight(1f)) {
                            Section()
                        }
                        Row(modifier = Modifier.weight(1f)) {
                            Section()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Section() {
    var isPressed by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf(MikuDarkGreen) }
    var color1 by remember { mutableStateOf(MikuPink) }

    LaunchedEffect(isPressed) {
        flow {
            while (isPressed) {
                val a = Color(
                    red = Random.nextInt(0..255),
                    blue = Random.nextInt(0..255),
                    green = Random.nextInt(0..255)
                )
                val b = Color(
                    red = Random.nextInt(0..255),
                    blue = Random.nextInt(0..255),
                    green = Random.nextInt(0..255)
                )

                emit(Pair(a, b))
                delay(16) // Update the time once per second
            }
        }.collectLatest { res ->
            // Update the UI with the new time
            val (a, b) = res
            color = a
            color1 = b
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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

@Composable
fun CurrentTime(color: Color) {
    var now by remember { mutableStateOf(LocalDateTime.now()) }
    val formatter by remember { mutableStateOf(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) }
    var textSize by remember { mutableStateOf(0.sp) }
    var fitSet by remember { mutableStateOf(false) }

    val localDensity = LocalDensity.current
    val fontScaleFactor = LocalDensity.current.fontScale

    LaunchedEffect(Unit) {
        flow {
            while (true) {
                emit(LocalDateTime.now())
                delay(16) // Update the time once per second
            }
        }.collectLatest { time ->
            // Update the UI with the new time
            now = time
        }
    }

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .onSizeChanged {
                if (!fitSet) {
                    val columnWidthDp = with(localDensity) { it.width.toDp() }
                    val text = now.format(formatter)
                    var fontSize = TextUnit(100f * fontScaleFactor, TextUnitType.Sp)
                    var result = android.graphics
                        .Paint()
                        .apply {
                            this.textSize = fontSize.value
                        }
                        .measureText(text).dp

                    while (result > columnWidthDp) {
                        fontSize.value
                            .dec()
                            .also { value ->
                                fontSize = value.sp
                            }
                        result = android.graphics
                            .Paint()
                            .apply {
                                this.textSize = (fontSize * fontScaleFactor).value
                            }
                            .measureText(text).dp
                    }
                    textSize = fontSize
                    fitSet = true
                }
            },
        style = TextStyle(fontSize = textSize, textAlign = TextAlign.Center),
        text = now.format(formatter),
        color = color,
    )
}
