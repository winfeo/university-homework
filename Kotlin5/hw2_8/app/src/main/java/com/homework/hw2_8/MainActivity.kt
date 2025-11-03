package com.homework.hw2_8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homework.hw2_8.ui.theme.Hw2_8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw2_8Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        WaterTracker()
                    }
                }
            }
        }
    }
}


@Composable
fun WaterTracker() {

    var waterCount by remember { mutableIntStateOf(0) }
    var daysCount by remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xD7094492)
        )
        Text(
            text = "Норма выпита дней подряд: $daysCount"
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            text = waterCount.toString(),
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xD7094492)),
            onClick = { waterCount += 250 }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        FilledTonalButton (
            onClick = {
                if (waterCount >= 1500) daysCount++ else daysCount = 0
                waterCount = 0
            }
        ) {
            Text(
                text = "Завершить день"
            )
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true)
@Composable
fun WaterTrackerPreview() {
    Hw2_8Theme {
        WaterTracker()
    }
}