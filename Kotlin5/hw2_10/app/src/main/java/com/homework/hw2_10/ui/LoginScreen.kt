package com.homework.hw2_10.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homework.hw2_10.data.UserService
import com.homework.hw2_10.ui.theme.Hw2_10Theme


@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onNavigateToSignUp: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var loginError by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 64.dp)
    ) {
        HeaderText()

        Spacer(modifier = Modifier.height(24.dp))
        EmailField(
            email = email,
            onEmailChange = { email = it }
        )

        Spacer(modifier = Modifier.height(24.dp))
        PasswordField(
            password = password,
            onPasswordChange = { password = it }
        )

        Spacer(modifier = Modifier.height(24.dp))
        ForgotPasswordButton(onClick = {})

        Spacer(modifier = Modifier.height(24.dp))
        LogInButton(
            onClick = {
                if (UserService.authUser(email, password)) {
                    onLoginSuccess(email)
                } else {
                    Toast.makeText(context, "Неверный логин или пароль" , Toast.LENGTH_SHORT).show()
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
        SingUpTextButton(onClick = onNavigateToSignUp)
    }
}

@Composable
fun HeaderText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello Again!",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "Welcome Back you've been missed",
            color = Color.Gray
        )
    }
}

@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        label = { Text(text = "Enter Email") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun PasswordField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        label = { Text(text = "Password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ForgotPasswordButton(onClick: () -> Unit) {
    Text(
        text = "Forgot Password?",
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    )
}

@Composable
fun LogInButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Log in")
    }
}

@Composable
fun SingUpTextButton(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Don't have an account?",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Sign up",
            color = Color.Blue,
            fontSize = 14.sp,
            modifier = Modifier.clickable { onClick() }
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    Hw2_10Theme {
        LoginScreen(
            onLoginSuccess = {},
            onNavigateToSignUp = {}
        )
    }
}