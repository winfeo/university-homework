package com.homework.hw2_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.homework.hw2_1.ui.theme.Hw2_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw2_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Hello(
                        name = "Кирилл",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Hello(name: String?, modifier: Modifier = Modifier) {
    Text(
        text = "Хай, ${name?: "Имя не задано"}",
        modifier = modifier
            .fillMaxSize(),
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
}

@Preview(showBackground = true)
@Composable
fun HelloPreview() {
    Hw2_1Theme {
        Hello("Кирилл")
    }
}