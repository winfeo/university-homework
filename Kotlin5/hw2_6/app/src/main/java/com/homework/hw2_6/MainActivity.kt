package com.homework.hw2_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.homework.hw2_6.ui.theme.Hw2_6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw2_6Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Circle(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Circle(modifier: Modifier = Modifier) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(240.dp, 120.dp)
            .background(color = Color.Blue)
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = "Oval",
            colorFilter = ColorFilter.tint(Color.Magenta),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CirclePreview() {
    Hw2_6Theme {
        Circle()
    }
}