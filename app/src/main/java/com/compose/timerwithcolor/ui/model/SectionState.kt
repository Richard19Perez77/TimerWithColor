package com.compose.timerwithcolor.ui.model

import androidx.compose.runtime.mutableStateOf
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import java.time.LocalDateTime

data class SectionState(val id: Int = 0) {
    val colorMode = mutableStateOf(ColorMode.HSV)
    val time = mutableStateOf(LocalDateTime.now())
    val isFlashing = mutableStateOf(false)
    val bgColor = mutableStateOf(MikuDarkGreen)
    val textColor = mutableStateOf(MikuPink)
    val addBlacks = mutableStateOf(false)
}