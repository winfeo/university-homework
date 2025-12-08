package com.homework.hw3_7

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun allTasks() {
        composeTestRule.onNodeWithText("Купить молоко").assertExists()
        composeTestRule.onNodeWithText("Позвонить маме").assertExists()
        composeTestRule.onNodeWithText("Сделать ДЗ по Android").assertExists()

        composeTestRule.onNodeWithText("2 литра, обезжиренное").assertExists()
        composeTestRule.onNodeWithText("Спросить про выходные").assertExists()
        composeTestRule.onNodeWithText("Clean Architecture + Compose").assertExists()
    }
}