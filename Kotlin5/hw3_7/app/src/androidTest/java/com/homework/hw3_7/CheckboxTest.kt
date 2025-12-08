package com.homework.hw3_7

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CheckboxTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkboxTest() {
        val checkbox = composeTestRule.onNodeWithTag("checkbox_1")
        checkbox.assertIsOff()
        checkbox.performClick()
        checkbox.assertIsOn()
        checkbox.performClick()
        Thread.sleep(500)
        checkbox.assertIsOff()
    }
}