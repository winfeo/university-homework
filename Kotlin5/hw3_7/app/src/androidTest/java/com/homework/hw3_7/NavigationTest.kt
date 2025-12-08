package com.homework.hw3_7

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigationTest() {
        composeTestRule.onNodeWithText("Купить молоко").assertExists()
        composeTestRule.onNodeWithText("Купить молоко").performClick()
        composeTestRule.onNodeWithText("Купить молоко").assertExists()
        composeTestRule.onNodeWithText("2 литра, обезжиренное").assertExists()
        composeTestRule.onNodeWithContentDescription("Назад").performClick()
        composeTestRule.onNodeWithText("Купить молоко").assertExists()
    }
}