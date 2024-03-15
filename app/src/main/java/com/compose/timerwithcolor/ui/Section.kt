package com.compose.timerwithcolor.ui

import android.graphics.Paint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.compose.timerwithcolor.ui.model.SectionState
import com.compose.timerwithcolor.ui.model.SectionViewModel
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.random.nextInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Section(id: Int) {

    val sectionViewModel: SectionViewModel = viewModel()

    val sectionState = remember {
        sectionViewModel.sections.value.getOrDefault(id, SectionState(id))
    }

    val tempColor = if (sectionState.isWarm.value) {
        "Warm Colors"
    } else {
        "Cool Colors"
    }

    val hasDarkness = if (sectionState.addBlacks.value) {
        "With Darkness"
    } else {
        "No Darkness"
    }

    LaunchedEffect(sectionState.isFlashing.value) {
        while (sectionState.isFlashing.value) {
            if (sectionState.isWarm.value) {
                if (sectionState.isHSV.value) {
                    var hue = Random.nextFloat() * 360
                    var sat = 1f // Random.nextFloat()
                    var value = if (sectionState.addBlacks.value) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue > 90 && hue < 270) {
                        hue = Random.nextFloat() * 360
                    }

                    val warmColorHSVa = Color.hsv(hue, sat, value)

                    hue = Random.nextFloat() * 360
                    sat = 1f
                    value = if (sectionState.addBlacks.value) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue > 90 && hue < 270) {
                        hue = Random.nextFloat() * 360
                    }
                    val warmColorHSVb = Color.hsv(hue, sat, value)

                    sectionState.bgColor.value = warmColorHSVa
                    sectionState.textColor.value = warmColorHSVb
                } else {
                    var red = Random.nextInt(0..255)
                    var blue = Random.nextInt(0..255)
                    var green = Random.nextInt(0..255)

                    while (red < blue) {
                        red = Random.nextInt(0..255)
                        blue = Random.nextInt(0..255)
                        green = Random.nextInt(0..255)
                    }

                    val warmColorRGBa = Color(
                        red = red,
                        blue = blue,
                        green = green
                    )

                    red = Random.nextInt(0..255)
                    blue = Random.nextInt(0..255)
                    green = Random.nextInt(0..255)

                    while (red < blue) {
                        red = Random.nextInt(0..255)
                        blue = Random.nextInt(0..255)
                        green = Random.nextInt(0..255)
                    }

                    val warmColorRGBb = Color(
                        red = red,
                        blue = blue,
                        green = green
                    )

                    sectionState.bgColor.value = warmColorRGBa
                    sectionState.textColor.value = warmColorRGBb
                }
            } else {
                if (sectionState.isHSV.value) {
                    var hue = Random.nextFloat() * 360
                    var sat = 1f
                    var value = if (sectionState.addBlacks.value) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue < 90 || hue > 270) {
                        hue = Random.nextFloat() * 360
                    }

                    val coolHSVa = Color.hsv(hue, sat, value)

                    hue = Random.nextFloat() * 360
                    sat = 1f
                    value = if (sectionState.addBlacks.value) {
                        Random.nextFloat() * (1f - .10f) + .10f
                    } else {
                        1f
                    }

                    while (hue < 90 || hue > 270) {
                        hue = Random.nextFloat() * 360
                    }

                    val coolHSVb = Color.hsv(hue, sat, value)

                    sectionState.bgColor.value = coolHSVa
                    sectionState.textColor.value = coolHSVb
                } else {

                    var red = Random.nextInt(0..255)
                    var blue = Random.nextInt(0..255)
                    var green = Random.nextInt(0..255)

                    while (blue < red) {
                        red = Random.nextInt(0..255)
                        blue = Random.nextInt(0..255)
                        green = Random.nextInt(0..255)
                    }

                    val coolRGBa = Color(
                        red = red,
                        blue = blue,
                        green = green
                    )

                    red = Random.nextInt(0..255)
                    blue = Random.nextInt(0..255)
                    green = Random.nextInt(0..255)

                    while (blue < red) {
                        red = Random.nextInt(0..255)
                        blue = Random.nextInt(0..255)
                        green = Random.nextInt(0..255)
                    }

                    val coolRGBb = Color(
                        red = red,
                        blue = blue,
                        green = green
                    )

                    sectionState.bgColor.value = coolRGBa
                    sectionState.textColor.value = coolRGBb
                }

            }
            delay(17)
        }
    }

    Box(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
            .background(
                sectionState.bgColor.value
            )
            .combinedClickable(
                enabled = true,
                onClick = {
                    sectionState.isFlashing.value = !sectionState.isFlashing.value
                },
                onLongClick = {
                    sectionState.addBlacks.value = !sectionState.addBlacks.value
                },
                onDoubleClick = {
                    sectionState.isWarm.value = !sectionState.isWarm.value
                },
            ),
        contentAlignment = Alignment.Center
    ) {
        CurrentTime(sectionState.textColor.value, tempColor, hasDarkness)
    }
}

@Composable
fun CurrentTime(color: Color, tempColor: String, hasDarkness: String) {
    var now by remember { mutableStateOf(LocalDateTime.now()) }
    var textSize by remember { mutableStateOf(0.sp) }
    var fitSet by remember { mutableStateOf(false) }

    val pattern = "yyyy-MM-dd HH:mm:ss.SSS"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val localDensity = LocalDensity.current
    val fontScaleFactor = LocalDensity.current.fontScale

    val test = "0000-00-00 00:00:00.000"

    LaunchedEffect(Unit) {
        while (true) {
            now = LocalDateTime.now()
            delay(17)
        }
    }

    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            style = TextStyle(
                fontSize = textSize,
                textAlign = TextAlign.Center
            ),
            text = tempColor,
            color = color,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .onSizeChanged {
                    if (!fitSet) {
                        val columnWidthDp = with(localDensity) { it.width.toDp() }
                        var fontSize = TextUnit(1f * fontScaleFactor, TextUnitType.Sp)
                        var result = Paint()
                            .apply {
                                this.textSize = fontSize.value
                            }
                            .measureText(test).dp

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
                                .measureText(test).dp
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
                                .measureText(test).dp
                        }
                        textSize = fontSize
                        fitSet = true
                    }
                },
            style = TextStyle(
                fontSize = textSize,
                textAlign = TextAlign.Center
            ),
            text = adjustNowString(now.format(formatter), test),
            color = color,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            style = TextStyle(
                fontSize = textSize,
                textAlign = TextAlign.Center
            ),
            text = hasDarkness,
            color = color,
        )
    }
}

fun adjustNowString(format: String?, test : String): String {
    val sb : StringBuilder = java.lang.StringBuilder()
    sb.append(format)
    format.let {
        if (it?.length!! < test.length){
            sb.append("0")
        }
    }
    return sb.toString()
}
