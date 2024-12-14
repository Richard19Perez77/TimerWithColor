package com.compose.timerwithcolor

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SectionInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testSection1BoxExists() {
        var id = 1
        composeTestRule.onNodeWithTag("Section{$id}Box").isDisplayed()
    }

    @Test
    fun testTemp1TextExists() {
        var id = 1
        composeTestRule.onNodeWithTag("Temp{$id}Text").isDisplayed()
    }

    @Test
    fun testTimer1TextExists() {
        var id = 1
        composeTestRule.onNodeWithTag("Timer{$id}Text").isDisplayed()
    }

    @Test
    fun testDarkness1TextExists() {
        var id = 1
        composeTestRule.onNodeWithTag("Darkness{$id}Text").isDisplayed()
    }
}