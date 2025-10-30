package com.homework.hw2_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.tooling.preview.devices.WearDevices
import com.homework.hw2_2.ui.theme.Hw2_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw2_2Theme {
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
            .fillMaxSize()
            .wrapContentSize(),
        textAlign = TextAlign.Center,
        fontSize = 16.sp
    )
}


//@Preview(
//    showBackground = true,
//    device = Devices.PIXEL_2,
//    name = "Портретная"
//)
//@Composable
//fun HelloPreview() {
//    Hw2_2Theme {
//        Hello("Кирилл")
//    }
//}

//@Preview(
//    showBackground = true,
//    widthDp = 640,
//    heightDp = 360,
//    name = "Альбомная"
//)
//@Composable
//fun HelloPreview() {
//    Hw2_2Theme {
//        Hello("Кирилл")
//    }
//}

@Preview(
    showBackground = true,
    name = "Круглая",
    widthDp = 200,
    heightDp = 200
)
@Composable
fun HelloPreview() {
    Hw2_2Theme {
        Surface(
            modifier = Modifier.size(200.dp),
            color = Color.Yellow,
            shape = CircleShape
        ) {
            Hello("Кирилл")
        }
    }
}