package com.compose.timerwithcolor.ui.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SectionViewModel : ViewModel() {
    val sections = mutableStateOf((mapOf<Int, SectionState>()))
}