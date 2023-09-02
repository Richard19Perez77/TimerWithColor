package com.compose.timerwithcolor.ui.model

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compose.timerwithcolor.ui.theme.MikuDarkGreen
import com.compose.timerwithcolor.ui.theme.MikuPink

class SectionViewModel : ViewModel() {

    // save colors for bg and text for each section and is flashing
    // MutableLiveData for your sections property
    private val _sections = MutableLiveData<MutableMap<Int, MutableLiveData<SectionState>>>()

    // Expose sections as LiveData
    private val sections: LiveData<MutableMap<Int, MutableLiveData<SectionState>>> = _sections

    // Initialize _sections with an initial value
    init {
        _sections.value = mutableMapOf()
    }

    fun getSectionsCount(): Int {
        return _sections.value?.size ?: 0
    }

    fun addSectionState(id: Int, sectionState: MutableLiveData<SectionState>) {
        _sections.value?.set(id, sectionState)
    }

    // get a specific state by id, if it doesn't exist make one
    fun getSectionState(id: Int): MutableLiveData<SectionState> {
        val sectionState = _sections.value?.getOrPut(id) {
            MutableLiveData<SectionState>(SectionState(id))
        }
        return sectionState!!
    }
}

data class SectionState(
    val id: Int,
    val isFlashing: Boolean = false,
    val bgColor: Color = MikuDarkGreen,
    val textColor: Color = MikuPink
)