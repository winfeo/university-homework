package com.homework.hw3_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.homework.hw3_4.ui.theme.Hw3_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw3_4Theme {
                SimpleCounter()
            }
        }
    }
}

@Composable
fun SimpleCounter(modifier: Modifier = Modifier) {
    val counterViewModel: Counter = viewModel()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(25.dp)
            .fillMaxSize()
    ) {
        Text(
            text = counterViewModel.count.value.toString(),
            fontSize = 50.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = { counterViewModel.plusOne() },
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp)
        ) {
            Text(text = "Прибавить 1")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimpleCounterPreview() {
    Hw3_4Theme {
        SimpleCounter()
    }
}