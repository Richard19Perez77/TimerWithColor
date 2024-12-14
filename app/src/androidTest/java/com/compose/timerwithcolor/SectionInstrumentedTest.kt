package com.compose.timerwithcolor

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.action.ViewActions.doubleClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SectionInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testSection1BoxExists() {
        val id = 0
        val interaction = composeTestRule.onNodeWithTag("Section{$id}Box")
        composeTestRule.waitUntil { interaction.isDisplayed() }
    }

    @Test
    fun testSection1BoxLongClick() {
        val id = 0
        var interaction = composeTestRule.onNodeWithTag("Section{$id}Box")
        composeTestRule.waitUntil { interaction.isDisplayed() }
        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { longClick() }
        interaction = composeTestRule.onNodeWithText("With Darkness", true)
        composeTestRule.waitUntil { interaction.isDisplayed() }
        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { longClick() }
        interaction = composeTestRule.onNodeWithText("No Darkness", true)
        composeTestRule.waitUntil { interaction.isDisplayed() }
    }

    @Test
    fun testSection1BoxDoubleClick() {
        val id = 0
        var interaction = composeTestRule.onNodeWithTag("Section{$id}Box")
        composeTestRule.waitUntil { interaction.isDisplayed() }

        interaction = composeTestRule.onNodeWithText("HSV")
        composeTestRule.waitUntil { interaction.isDisplayed() }

        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { doubleClick() }
        composeTestRule.onNodeWithText("HSV Cool").isDisplayed()

        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { doubleClick() }
        composeTestRule.onNodeWithText("HSV Warm").isDisplayed()

        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { doubleClick() }
        composeTestRule.onNodeWithText("RGB Cool").isDisplayed()

        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { doubleClick() }
        composeTestRule.onNodeWithText("RGB Warm").isDisplayed()

        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { doubleClick() }
        composeTestRule.onNodeWithText("RGB").isDisplayed()

        composeTestRule.onNodeWithTag("Section{$id}Box").performTouchInput { doubleClick() }
        composeTestRule.onNodeWithText("HSV").isDisplayed()
    }

    @Test
    fun testTemp1TextExists() {
        val id = 0
        val interaction = composeTestRule.onNodeWithTag("Temp{$id}Text", true)
        composeTestRule.waitUntil { interaction.isDisplayed() }
    }

    @Test
    fun testTimer1TextExists() {
        val id = 0
        val interaction = composeTestRule.onNodeWithTag("Timer{$id}Text", true)
        composeTestRule.waitUntil { interaction.isDisplayed() }
    }

    @Test
    fun testDarkness1TextExists() {
        val id = 0
        val interaction = composeTestRule.onNodeWithTag("Darkness{$id}Text", true)
        composeTestRule.waitUntil { interaction.isDisplayed() }
    }

    @Test
    fun testTemp1TextHSVExists() {
        val interaction = composeTestRule.onNodeWithText("HSV")
        composeTestRule.waitUntil { interaction.isDisplayed() }
    }

    @Test
    fun testDarkness1TextHasDarknessExists() {
        val interaction = composeTestRule.onNodeWithText("No Darkness")
        composeTestRule.waitUntil { interaction.isDisplayed() }
    }
}