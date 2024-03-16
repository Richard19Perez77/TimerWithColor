package com.compose.timerwithcolor.ui

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
    val delayTime = 5L
    val sectionViewModel: SectionViewModel = viewModel()
    val sectionState = remember {
        sectionViewModel.sections.value.getOrDefault(id, SectionState(id))
    }

    LaunchedEffect(Unit) {
        while (true) {
            sectionViewModel.time.value = LocalDateTime.now()

            if (sectionState.isFlashing.value) {
                if (sectionState.isWarm.value) {
                    if (sectionState.isHSV.value) {
                        hsvWarm(sectionState)
                    } else {
                        rgbWarm(sectionState)
                    }
                } else {
                    if (sectionState.isHSV.value) {
                        coolHSV(sectionState)
                    } else {
                        coolRGB(sectionState)
                    }
                }
            }

            delay(delayTime)
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
        CurrentTime(sectionState, sectionViewModel.time)
    }
}

private fun coolRGB(sectionState: SectionState) {
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

private fun coolHSV(sectionState: SectionState) {
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
}

private fun rgbWarm(sectionState: SectionState) {
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

private fun hsvWarm(sectionState: SectionState) {
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
}

@Composable
fun CurrentTime(sectionState: SectionState, timeState: MutableState<LocalDateTime>) {
    val colorText = if (sectionState.isWarm.value) {
        "Warm Colors"
    } else {
        "Cool Colors"
    }
    val darkText = if (sectionState.addBlacks.value) {
        "With Darkness"
    } else {
        "No Darkness"
    }

    Column(modifier = Modifier.fillMaxHeight(1f),
        verticalArrangement = Arrangement.SpaceEvenly) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            style = TextStyle(
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            ),
            text = colorText,
            color = sectionState.textColor.value,
        )

        FitText(Modifier, sectionState, timeState)

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            style = TextStyle(
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            ),
            text = darkText,
            color = sectionState.textColor.value,
        )
    }
}

@Composable
fun FitText(
    modifier: Modifier = Modifier,
    sectionState: SectionState,
    timeState: MutableState<LocalDateTime>
) {
    var textSize by remember { mutableStateOf(0.sp) }
    var fitSet by remember { mutableStateOf(false) }
    val localDensity = LocalDensity.current
    val fontScaleFactor = LocalDensity.current.fontScale
    val test =    "0000-00-00 00:00:00.000"
    val pattern = "yyyy-MM-dd HH:mm:ss.SSS"
    val formatter = DateTimeFormatter.ofPattern(pattern)

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
                        .measureText(timeState.value.format(formatter)).dp

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
                            .measureText(timeState.value.format(formatter)).dp
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
                            .measureText(timeState.value.format(formatter)).dp
                    }
                    textSize = fontSize
                    Log.d("rick", "textSize$textSize")
                    fitSet = true
                }
            },
        style = TextStyle(
            fontSize = textSize,
            textAlign = TextAlign.Center
        ),
        text = adjustNowString(timeState.value.format(formatter), test),
        color = sectionState.textColor.value,
    )
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
