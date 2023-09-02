package com.compose.timerwithcolor.ui

import android.graphics.Paint
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.timerwithcolor.ui.model.SectionViewModel
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.random.nextInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Section(id: Int) {

    val sectionViewModel: SectionViewModel = viewModel()
    val sectionState = sectionViewModel.getSectionState(id)

    var isPressed by remember { mutableStateOf(sectionState.value?.isFlashing ?: false) }
    var bgColor by remember { mutableStateOf(sectionState.value?.bgColor ?: MikuDarkGreen) }
    var textColor by remember { mutableStateOf(sectionState.value?.textColor ?: MikuPink) }

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
            bgColor = a
            textColor = b
        }
    }

    // allow touch down and click handling for change colors
    // can start touch in one box and still enable click in another with a separate touch
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                bgColor
            )
            .clickable(
                enabled = true,
                onClick = {
                    isPressed = !isPressed
                })
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
        CurrentTime(textColor)
    }
}

@Composable
fun CurrentTime(color: Color) {
    var now by remember { mutableStateOf(LocalDateTime.now()) }
    var textSize by remember { mutableStateOf(0.sp) }
    var fitSet by remember { mutableStateOf(false) }

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
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
                    var result = Paint()
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
                        result = Paint()
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
