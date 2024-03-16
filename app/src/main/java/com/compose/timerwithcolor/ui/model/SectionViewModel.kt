package com.compose.timerwithcolor.ui.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuPink
import java.time.LocalDateTime

class SectionViewModel : ViewModel() {
    val time = mutableStateOf(LocalDateTime.now())
    var sections = mutableStateOf((mapOf<Int, SectionState>()))
}

class SectionState(var id: Int = 0) {
    val isFlashing = mutableStateOf(false)
    val bgColor = mutableStateOf(MikuDarkGreen)
    val textColor = mutableStateOf(MikuPink)
    val isWarm = mutableStateOf(false)
    val addBlacks = mutableStateOf(false)
    val isHSV = mutableStateOf(true)
}