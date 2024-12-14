package com.compose.timerwithcolor

import com.compose.timerwithcolor.ui.model.SectionState
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SectionStateTest {

    @Test
    fun testColorUpdate_rgbCoolMode() {
        val sectionState = SectionState(id = 1)
        var prev = sectionState.textColor.value
        sectionState.colorUpdate()
        var curr = sectionState.textColor.value
        assertTrue(prev != curr)
    }

    @Test
    fun testBgUpdate_rgbCoolMode() {
        val sectionState = SectionState(id = 1)
        var prev = sectionState.bgColor.value
        sectionState.colorUpdate()
        var curr = sectionState.bgColor.value
        assertTrue(prev != curr)
    }

    @Test
    fun testColorModeUpdate_rgbCoolMode() {
        val sectionState = SectionState(id = 1)
        var prev = sectionState.colorMode.value
        sectionState.rotateMode()
        var curr = sectionState.colorMode.value
        assertTrue(prev != curr)
    }

    @Test
    fun testIsFlashingUpdate_rgbCoolMode() {
        val sectionState = SectionState(id = 1)
        var prev = sectionState.isFlashing.value
        sectionState.toggleColorFlash()
        var curr = sectionState.isFlashing.value
        assertTrue(prev != curr)
    }

    @Test
    fun testDarknessUpdate_rgbCoolMode() {
        val sectionState = SectionState(id = 1)
        var prev = sectionState.addBlack.value
        assertFalse(prev)
        sectionState.toggleAddBlack()
        var curr = sectionState.addBlack.value
        assertTrue(curr)
    }

    @Test
    fun testDarknessUpdateString_rgbCoolMode() {
        val sectionState = SectionState(id = 1)
        var prev = sectionState.getDarknessString()
        sectionState.toggleAddBlack()
        var curr = sectionState.getDarknessString()
        assertNotEquals(prev, curr)
    }
}