package com.compose.timerwithcolor.ui.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuLightGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.random.nextInt

data class SectionState(val id: Int = 0) {

    val colorMode = mutableStateOf(ColorMode.HSV)
    val time = mutableStateOf(LocalDateTime.now())
    val isFlashing = mutableStateOf(false)
    val bgColor = mutableStateOf(MikuDarkGreen)

    val textColor = mutableStateOf(MikuPink)
    val addBlack = mutableStateOf(false)
    val displayName: String
        get() = when (colorMode.value) {
            ColorMode.HSV_COOL -> "HSV Cool"
            ColorMode.HSV_WARM -> "HSV Warm"
            ColorMode.RGB_COOL -> "RGB Cool"
            ColorMode.RGB_WARM -> "RGB Warm"
            ColorMode.RGB -> "RGB"
            ColorMode.HSV -> "HSV"
        }

    fun rotateMode() {
        when (this.colorMode.value) {
            ColorMode.HSV_COOL -> this.colorMode.value = ColorMode.HSV_WARM
            ColorMode.HSV_WARM -> this.colorMode.value = ColorMode.RGB_COOL
            ColorMode.RGB_COOL -> this.colorMode.value = ColorMode.RGB_WARM
            ColorMode.RGB_WARM -> this.colorMode.value = ColorMode.RGB
            ColorMode.RGB -> this.colorMode.value = ColorMode.HSV
            ColorMode.HSV -> this.colorMode.value = ColorMode.HSV_COOL
        }
    }

    fun colorUpdate() {
        when (this.colorMode.value) {
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

                this.bgColor.value = warmColorRGBa
                this.textColor.value = warmColorRGBb
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

                this.bgColor.value = warmColorRGBa
                this.textColor.value = warmColorRGBb
            }

            ColorMode.HSV_WARM -> {
                var hue = Random.nextFloat() * 360
                var sat = 1f // Random.nextFloat()
                var value = if (this.addBlack.value) {
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
                value = if (this.addBlack.value) {
                    Random.nextFloat() * (1f - .10f) + .10f
                } else {
                    1f
                }

                while (hue > 90 && hue < 270) {
                    hue = Random.nextFloat() * 360
                }
                val warmColorHSVb = Color.hsv(hue, sat, value)

                this.bgColor.value = warmColorHSVa
                this.textColor.value = warmColorHSVb
            }

            ColorMode.HSV_COOL -> {
                var hue = Random.nextFloat() * 360
                var sat = 1f
                var value = if (this.addBlack.value) {
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
                value = if (this.addBlack.value) {
                    Random.nextFloat() * (1f - .10f) + .10f
                } else {
                    1f
                }

                while (hue < 90 || hue > 270) {
                    hue = Random.nextFloat() * 360
                }

                val coolHSVb = Color.hsv(hue, sat, value)

                this.bgColor.value = coolHSVa
                this.textColor.value = coolHSVb
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

                this.bgColor.value = warmColorRGBa
                this.textColor.value = warmColorRGBb
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

                this.bgColor.value = coolHSVa
                this.textColor.value = coolHSVb
            }
        }
    }

    fun toggleColorFlash() {
        isFlashing.value = !isFlashing.value
        if (!isFlashing.value) {
            bgColor.value = MikuLightGreen
            textColor.value = MikuPink
        }
    }

    fun getDarknessString(): String {
        return if (addBlack.value) {
            "With Darkness"
        } else {
            "No Darkness"
        }
    }

    fun toggleAddBlack() {
        addBlack.value = !addBlack.value
    }
}