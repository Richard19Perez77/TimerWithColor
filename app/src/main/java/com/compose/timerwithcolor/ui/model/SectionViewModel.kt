package com.compose.timerwithcolor.ui.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import java.time.LocalDateTime

class SectionViewModel : ViewModel() {
    val sections = mutableStateOf((mapOf<Int, SectionState>()))
    enum class ColorMode {
        RGB, HSV, RGB_COOL, RGB_WARM, HSV_WARM, HSV_COOL
    }
}

data class SectionState(val id: Int = 0) {
    val colorMode = mutableStateOf(SectionViewModel.ColorMode.HSV)
    val time = mutableStateOf(LocalDateTime.now())
    val isFlashing = mutableStateOf(false)
    val bgColor = mutableStateOf(MikuDarkGreen)
    val textColor = mutableStateOf(MikuPink)
    val addBlacks = mutableStateOf(false)
}