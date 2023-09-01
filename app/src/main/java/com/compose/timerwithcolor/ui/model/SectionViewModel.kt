package com.compose.timerwithcolor.ui.model

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuPink

class SectionViewModel : ViewModel() {

    // save colors for bg and text for each section and is flashing
    var sections = mutableListOf<SectionState>()
}

data class SectionState(val isFlashing : Boolean  = false, val bgColor : Color = MikuDarkGreen, val textColor : Color = MikuPink)