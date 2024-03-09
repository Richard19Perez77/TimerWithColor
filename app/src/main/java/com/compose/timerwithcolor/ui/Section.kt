package com.compose.timerwithcolor.ui

import android.graphics.Paint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Section(id: Int) {

    val sectionViewModel: SectionViewModel = viewModel()
    val sectionState = sectionViewModel.getSectionState(id)

    var isPressed by remember { mutableStateOf(sectionState.value?.isFlashing ?: false) }
    var bgColor by remember { mutableStateOf(sectionState.value?.bgColor ?: MikuDarkGreen) }
    var textColor by remember { mutableStateOf(sectionState.value?.textColor ?: MikuPink) }

    var isWarm by remember { mutableStateOf(false) }
    var addBlacks by remember { mutableStateOf(false) }

    val warm = if (isWarm) {
        "Warm Colors"
    } else {
        "Cool Colors"
    }

    val dark = if (addBlacks) {
        "With Darkness"
    } else {
        "No Darkness"
    }

    LaunchedEffect(isPressed) {
        flow {
            while (isPressed) {
                if (isWarm) {
                    var hue = Random.nextFloat() * 360
                    var sat = 1f // Random.nextFloat()
                    var value = if (addBlacks) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue > 90 && hue < 270) {
                        hue = Random.nextFloat() * 360
                    }

                    val warmColorA = Color.hsv(hue, sat, value)

                    hue = Random.nextFloat() * 360
                    sat = 1f
                    value = if (addBlacks) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue > 90 && hue < 270) {
                        hue = Random.nextFloat() * 360
                    }

                    val warmColorB = Color.hsv(hue, sat, value)

//                    var red = Random.nextInt(0..255)
//                    var blue = Random.nextInt(0..255)
//                    var green = Random.nextInt(0..255)
//
//                    while (red < blue) {
//                        red = Random.nextInt(0..255)
//                        blue = Random.nextInt(0..255)
//                        green = Random.nextInt(0..255)
//                    }
//
//                    @Suppress("UNUSED_VARIABLE") val a = Color(
//                        red = red,
//                        blue = blue,
//                        green = green
//                    )
//
//                    red = Random.nextInt(0..255)
//                    blue = Random.nextInt(0..255)
//                    green = Random.nextInt(0..255)
//
//                    while (red < blue) {
//                        red = Random.nextInt(0..255)
//                        blue = Random.nextInt(0..255)
//                        green = Random.nextInt(0..255)
//                    }
//
//                    @Suppress("UNUSED_VARIABLE") val b = Color(
//                        red = red,
//                        blue = blue,
//                        green = green
//                    )

                    //emit(Pair(a, b))
                    emit(Pair(warmColorA, warmColorB))
                } else {

                    var hue = Random.nextFloat() * 360
                    var sat = 1f
                    var value = if (addBlacks) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue < 90 || hue > 270) {
                        hue = Random.nextFloat() * 360
                    }

                    val warmColorA = Color.hsv(hue, sat, value)

                //emit(Pair(a, b))
                delay(16) // run color change at 60 fps
                    hue = Random.nextFloat() * 360
                    sat = 1f // Random.nextFloat()
                    value = if (addBlacks) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue < 90 || hue > 270) {
                        hue = Random.nextFloat() * 360
                    }

                    val warmColorB = Color.hsv(hue, sat, value)

//                    var red = Random.nextInt(0..255)
//                    var blue = Random.nextInt(0..255)
//                    var green = Random.nextInt(0..255)
//
//                    while (blue < red) {
//                        red = Random.nextInt(0..255)
//                        blue = Random.nextInt(0..255)
//                        green = Random.nextInt(0..255)
//                    }
//
//                    @Suppress("UNUSED_VARIABLE") val a = Color(
//                        red = red,
//                        blue = blue,
//                        green = green
//                    )
//
//                    red = Random.nextInt(0..255)
//                    blue = Random.nextInt(0..255)
//                    green = Random.nextInt(0..255)
//
//                    while (blue < red) {
//                        red = Random.nextInt(0..255)
//                        blue = Random.nextInt(0..255)
//                        green = Random.nextInt(0..255)
//                    }
//
//                    @Suppress("UNUSED_VARIABLE") val b = Color(
//                        red = red,
//                        blue = blue,
//                        green = green
//                    )

                    //emit(Pair(a, b))
                    emit(Pair(warmColorA, warmColorB))
                }
                delay(17) // Update the time once per second
            }
        }.collectLatest { res ->
            // Update the UI with the new time
            val (a, b) = res
            bgColor = a
            textColor = b
        }
    }

    // allow touch down and click handling for change colors can start touch in one box and still enable click in another with a separate touch
    Box(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
            .background(
                bgColor
            )
            .combinedClickable(
                enabled = true,
                onClick = {
                    isPressed = !isPressed
                },
                onLongClick = {
                    addBlacks = !addBlacks
                },
                onDoubleClick = {
                    isWarm = !isWarm
                },
            ),
        contentAlignment = Alignment.Center
    ) {
        CurrentTime(textColor, warm, dark)
    }
}

@Composable
fun CurrentTime(color: Color, warm: String, dark: String) {
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
                delay(16) // allot changes for 60fps
                delay(17) // Update the time once per second
            }
        }.collectLatest { time ->
            // Update the UI with the new time
            now = time
        }
    }

    // todo set in its own composable for font scaling
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .onSizeChanged {
                if (!fitSet) {
                    val columnWidthDp = with(localDensity) { it.width.toDp() }
                    val text = now.format(formatter)
                    var fontSize = TextUnit(1f * fontScaleFactor, TextUnitType.Sp)
                    var result = Paint()
                        .apply {
                            this.textSize = fontSize.value
                        }
                        .measureText(text).dp

                    while (result < columnWidthDp) {
                        fontSize.value
                            .inc()
                            .also { value ->
                                fontSize = value.sp
                            }
                        result = Paint()
                            .apply {
                                this.textSize = (fontSize * fontScaleFactor).value
                            }
                            .measureText(text).dp
                    }

                    while (result >= columnWidthDp) {
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
        style = TextStyle(
            fontSize = textSize,
            textAlign = TextAlign.Center
        ),
        text = "$warm\n${now.format(formatter)}\n$dark",
        color = color,
    )
}
