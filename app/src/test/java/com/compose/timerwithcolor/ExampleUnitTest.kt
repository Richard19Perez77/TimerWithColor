package com.compose.timerwithcolor

import com.compose.timerwithcolor.ui.model.ColorMode
import com.compose.timerwithcolor.ui.model.SectionState
import com.compose.timerwithcolor.ui.Section

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testColorUpdate_rgbCoolMode() {
        val sectionState = SectionState(id = 1).apply {
            colorMode.value = ColorMode.RGB_COOL
        }

        sectionState.colorUpdate()
        assertNotNull(sectionState.bgColor.value)
        assertNotNull(sectionState.textColor.value)
    }
}