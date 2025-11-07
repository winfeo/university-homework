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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homework.hw2_10.data.User
import com.homework.hw2_10.data.UserService
import com.homework.hw2_10.ui.theme.Hw2_10Theme


@Composable
fun SignUpScreen(
    onSignUpSuccess: (String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 64.dp)
    ) {
        SingUpText()

        Spacer(modifier = Modifier.height(24.dp))
        FirstNameField(
            firstName = firstName,
            onFirstNameChange = { firstName = it }
        )

        Spacer(modifier = Modifier.height(12.dp))
        LastNameField(
            lastName = lastName,
            onLastNameChange = { lastName = it }
        )

        Spacer(modifier = Modifier.height(12.dp))
        EmailFieldSignUp(
            email = email,
            onEmailChange = { email = it }
        )

        Spacer(modifier = Modifier.height(12.dp))
        PasswordFieldSignUp(
            password = password,
            onPasswordChange = { password = it }
        )

        Spacer(modifier = Modifier.height(24.dp))
        RegisterButton(
            onClick = {
                when {
                    firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                        Toast.makeText(context, "Заполните все поля" ,Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        val newUser = User(firstName, lastName, email, password)
                        if (UserService.addUser(newUser)) {
                            onSignUpSuccess(email)
                        } else {
                            Toast.makeText(context, "Пользователь уже существует" ,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(24.dp))
        LogInTextButton(onClick = onNavigateToLogin)
    }
}


@Composable
fun SingUpText() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome!",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text(
            text = "Register an account with Us",
            color = Color.Gray
        )
    }
}


@Composable
fun FirstNameField(
    firstName: String,
    onFirstNameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = firstName,
        onValueChange = onFirstNameChange,
        label = { Text(text = "First Name") },
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun LastNameField(
    lastName: String,
    onLastNameChange: (String) -> Unit
) {
    OutlinedTextField(
        value = lastName,
        onValueChange = onLastNameChange,
        label = { Text(text = "Last Name") },
        modifier = Modifier.fillMaxWidth()
    )
}


@Composable
fun EmailFieldSignUp(
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
fun PasswordFieldSignUp(
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
fun RegisterButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Register")
    }
}


@Composable
fun LogInTextButton(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Already have an account?",
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Log in",
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
fun SignUpScreenPreview() {
    Hw2_10Theme {
        SignUpScreen(
            onSignUpSuccess = {},
            onNavigateToLogin = {}
        )
    }
}

