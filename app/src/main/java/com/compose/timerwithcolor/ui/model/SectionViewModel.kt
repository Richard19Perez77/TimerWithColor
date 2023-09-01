package com.compose.timerwithcolor.ui.model

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class SectionViewModel : ViewModel() {

    // save colors for bg and text for each section and is flashing
    var sections = mutableListOf<SectionState>()
}

data class SectionState(var isFlashing : Boolean  = false, var bgColor : Color, var textColor : Color)