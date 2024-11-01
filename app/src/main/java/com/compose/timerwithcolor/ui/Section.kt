package com.compose.timerwithcolor.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.compose.timerwithcolor.ui.model.ColorMode
import com.compose.timerwithcolor.ui.model.SectionState
import com.compose.timerwithcolor.ui.theme.MikuLightGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import kotlin.random.nextInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Section(id: Int) {
    val delayTime = 16L
    val pattern = "MM-dd-yyyy HH:mm:ss.SSS"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val sectionState = remember {
        SectionState(id)
    }

    LaunchedEffect(Unit) {
        while (true) {
            sectionState.time.value = LocalDateTime.now()

            if (sectionState.isFlashing.value) {
                colorUpdate(sectionState)
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
                    if (!sectionState.isFlashing.value) {
                        sectionState.bgColor.value = MikuLightGreen
                        sectionState.textColor.value = MikuPink
                    }
                },
                onLongClick = {
                    sectionState.addBlacks.value = !sectionState.addBlacks.value
                },
                onDoubleClick = {
                    rotateMode(sectionState)
                },
            ),
        contentAlignment = Alignment.Center
    ) {
        CurrentTime(
            sectionState.textColor.value,
            when (sectionState.colorMode.value) {
                ColorMode.HSV_COOL -> {
                    "HSV Cool"
                }

                ColorMode.HSV_WARM -> {
                    "HSV Warm"
                }

                ColorMode.RGB_COOL -> {
                    "RGB Cool"
                }

                ColorMode.RGB_WARM -> {
                    "RBG Warm"
                }

                ColorMode.RGB -> {
                    "RGB"
                }

                ColorMode.HSV -> {
                    "HSV"
                }
            },
            if (sectionState.addBlacks.value) {
                "With Darkness"
            } else {
                "No Darkness"
            },
            sectionState.time.value.format(formatter)
        )
    }
}

private fun colorUpdate(sectionState: SectionState) {
    when (sectionState.colorMode.value) {
        ColorMode.RGB_COOL -> {
            val max = 255
            var red = Random.nextInt(0..max)
            var blue = Random.nextInt(0..max)
            var green = Random.nextInt(0..max)

            while (red >= blue) {
                red = Random.nextInt(0..max)
                blue = Random.nextInt(0..max)
            }

            val warmColorRGBa = Color(
                red = red,
                blue = blue,
                green = green
            )

            red = Random.nextInt(0..max)
            blue = Random.nextInt(0..max)
            green = Random.nextInt(0..max)

            while (red < blue) {
                red = Random.nextInt(0..max)
                blue = Random.nextInt(0..max)
            }

            val warmColorRGBb = Color(
                red = red,
                blue = blue,
                green = green
            )

            sectionState.bgColor.value = warmColorRGBa
            sectionState.textColor.value = warmColorRGBb
        }

        ColorMode.RGB_WARM -> {
            val max = 255
            var red = Random.nextInt(0..max)
            var blue = Random.nextInt(0..max)
            var green = Random.nextInt(0..max)

            while (red < blue) {
                red = Random.nextInt(0..max)
                blue = Random.nextInt(0..max)
            }

            val warmColorRGBa = Color(
                red = red,
                blue = blue,
                green = green
            )

            red = Random.nextInt(0..max)
            blue = Random.nextInt(0..max)
            green = Random.nextInt(0..max)

            while (red < blue) {
                red = Random.nextInt(0..max)
                blue = Random.nextInt(0..max)
            }

            val warmColorRGBb = Color(
                red = red,
                blue = blue,
                green = green
            )

            sectionState.bgColor.value = warmColorRGBa
            sectionState.textColor.value = warmColorRGBb
        }

        ColorMode.HSV_WARM -> {
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

        ColorMode.HSV_COOL -> {
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

        ColorMode.RGB -> {
            val max = 255
            var red = Random.nextInt(0..max)
            var blue = Random.nextInt(0..max)
            var green = Random.nextInt(0..max)

            val warmColorRGBa = Color(
                red = red,
                blue = blue,
                green = green
            )

            red = Random.nextInt(0..max)
            blue = Random.nextInt(0..max)
            green = Random.nextInt(0..max)

            val warmColorRGBb = Color(
                red = red,
                blue = blue,
                green = green
            )

            sectionState.bgColor.value = warmColorRGBa
            sectionState.textColor.value = warmColorRGBb
        }

        ColorMode.HSV -> {
            var hue = Random.nextFloat() * 360
            var sat = 1f
            var value = 1f

            val coolHSVa = Color.hsv(hue, sat, value)

            hue = Random.nextFloat() * 360
            sat = 1f
            value = 1f

            val coolHSVb = Color.hsv(hue, sat, value)

            sectionState.bgColor.value = coolHSVa
            sectionState.textColor.value = coolHSVb
        }
    }
}

private fun rotateMode(sectionState: SectionState) {
    when (sectionState.colorMode.value) {
        ColorMode.HSV_COOL -> {
            sectionState.colorMode.value = ColorMode.HSV_WARM
        }

        ColorMode.HSV_WARM -> {
            sectionState.colorMode.value = ColorMode.RGB_COOL
        }

        ColorMode.RGB_COOL -> {
            sectionState.colorMode.value = ColorMode.RGB_WARM
        }

        ColorMode.RGB_WARM -> {
            sectionState.colorMode.value = ColorMode.RGB
        }

        ColorMode.RGB -> {
            sectionState.colorMode.value = ColorMode.HSV
        }

        ColorMode.HSV -> {
            sectionState.colorMode.value = ColorMode.HSV_COOL
        }
    }
}