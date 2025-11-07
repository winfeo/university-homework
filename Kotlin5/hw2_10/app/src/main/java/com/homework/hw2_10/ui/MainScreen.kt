package com.homework.hw2_10.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homework.hw2_10.data.UserService
import com.homework.hw2_10.ui.theme.Hw2_10Theme

@Composable
fun MainScreen(email: String, onLogout: () -> Unit) {
    val user = UserService.getUser(email)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome, \n${user?.firstName} ${user?.lastName}",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "You have successfully logged in",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLogout
        ) {
            Text("Logout")
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainScreenPreview() {
    Hw2_10Theme {
        MainScreen(
            email = "kirill@gmail.com",
            onLogout = {}
        )
    }
}