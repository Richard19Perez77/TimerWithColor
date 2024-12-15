package com.compose.timerwithcolor.ui.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuLightGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import java.time.LocalDateTime
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * SectionState represents the state and behavior of a single Section composable.
 *
 * This class manages dynamic properties like the current color mode, flashing behavior,
 * background and text colors, and time updates. It provides functions to toggle features
 * like color flashing, add a darkness filter, and rotate through color modes.
 *
 * @param id A unique identifier for the Section this state belongs to.
 */
data class SectionState(val id: Int = 0) {

    val colorMode = mutableStateOf(ColorMode.HSV)
    val time = mutableStateOf(LocalDateTime.now())
    val isFlashing = mutableStateOf(false)
    val bgColor = mutableStateOf(MikuDarkGreen)

    val textColor = mutableStateOf(MikuPink)
    val addBlack = mutableStateOf(false)

    /**
     * Get display name based on ColorMode
     */
    val displayName: String
        get() = when (colorMode.value) {
            ColorMode.HSV_COOL -> "HSV Cool"
            ColorMode.HSV_WARM -> "HSV Warm"
            ColorMode.RGB_COOL -> "RGB Cool"
            ColorMode.RGB_WARM -> "RGB Warm"
            ColorMode.RGB -> "RGB"
            ColorMode.HSV -> "HSV"
        }

    /**
     * Sets new color mode based on previous mode, cycles through ColorMode.
     */
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

    /**
     * Based on temperature and color set RGB or HSV create random colors
     */
    fun colorUpdate() {
        when (this.colorMode.value) {
            // RGB with Blue as higher than Red
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
            // RGB with Red higher than Blue
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
            // HSV with Red higher than Blue
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
            // HSV with Blue higher than Red
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
            // RGB color values at random
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
            // HSV color values at random
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

    /**
     * Toggle on or off the flashing lights in the application.
     */
    fun toggleColorFlash() {
        isFlashing.value = !isFlashing.value
        if (!isFlashing.value) {
            bgColor.value = MikuLightGreen
            textColor.value = MikuPink
        }
    }

    /**
     * If color mode supports return if darkness filter is used.
     *
     * @return String for Darkness on or off or empty string
     */
    fun getDarknessString(): String {
        return if (colorMode.value == ColorMode.HSV_WARM ||
            colorMode.value == ColorMode.HSV_COOL
        ) {
            if (addBlack.value) {
                "With Darkness"
            } else {
                "No Darkness"
            }
        } else {
            ""
        }
    }

    /**
     * Set adding darkness to HSV_WARM or HSV_COOL color mode.
     */
    fun toggleAddBlack() {
        if (colorMode.value == ColorMode.HSV_WARM ||
            colorMode.value == ColorMode.HSV_COOL
        ) {
            addBlack.value = !addBlack.value
        }
    }
}