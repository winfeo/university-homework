package com.homework.hw2_10

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.homework.hw2_10.ui.LoginScreen
import com.homework.hw2_10.ui.MainScreen
import com.homework.hw2_10.ui.SignUpScreen

@Composable
fun NavigationService() {
    var currentScreen by remember { mutableStateOf("login") }
    var currentUserEmail by remember { mutableStateOf("") }

    when (currentScreen) {
        "login" -> LoginScreen(
            onLoginSuccess = { email ->
                currentUserEmail = email
                currentScreen = "main" },
            onNavigateToSignUp = { currentScreen = "signup" }
        )
        "signup" -> SignUpScreen(
            onSignUpSuccess = { email ->
                currentUserEmail = email
                currentScreen = "main" },
            onNavigateToLogin = { currentScreen = "login" }

        )
        "main" -> MainScreen(
            email = currentUserEmail,
            onLogout = {
                currentUserEmail = ""
                currentScreen = "login" }
        )
    }
}