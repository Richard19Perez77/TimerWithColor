package com.compose.timerwithcolor.ui.model

/**
 * ColorMode is an enum class that represents different modes for color schemes.
 *
 * These modes are used to define how colors are generated and displayed within the UI.
 * The modes include warm or cool variations, as well as options based on the
 * RGB (Red, Green, Blue) or HSV (Hue, Saturation, Value) color models.
 */
enum class ColorMode {
    RGB, HSV, RGB_COOL, RGB_WARM, HSV_WARM, HSV_COOL
}