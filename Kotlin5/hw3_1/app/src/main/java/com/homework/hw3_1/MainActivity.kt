package com.homework.hw3_1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.homework.hw3_1.ui.theme.Hw3_1Theme
import kotlin.contracts.contract

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw3_1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ListSample()
                }
            }
        }
    }
}

@Composable
fun ListSample(modifier: Modifier = Modifier.padding(top = 25.dp)){
    val cardsList = listOf(
        Card(imageResource = R.drawable.person_ic, title = "Иванов И.И.", description = "Учитель химии"),
        Card(imageResource = R.drawable.person_ic, title = "Николавев С.Т.", description = "Учитель математики"),
        Card(imageResource = R.drawable.person_ic, title = "Соколова А.Е.", description = "Учитель литературы"),
        Card(imageResource = R.drawable.person_ic, title = "Колесников К.Н.", description = "Учитель физкультуры"),
        Card(imageResource = R.drawable.person_ic, title = "Петров Э.Г.", description = "Учитель ОБЖ"),
        Card(imageResource = R.drawable.person_ic, title = "Серебрякова Т.В.", description = "Учитель музыки"),
        Card(imageResource = R.drawable.person_ic, title = "Агафонова И.С.", description = "Учитель рисования"),
        Card(imageResource = R.drawable.person_ic, title = "Жданова Е.В.", description = "Учитель физики"),
        Card(imageResource = R.drawable.person_ic, title = "Васильев Д.А.", description = "Учитель информатики"),
    )

    LazyColumn(modifier = modifier) {
        items(items = cardsList) { item ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp, horizontal = 12.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = item.imageResource),
                        contentDescription = item.title,
                        modifier
                            .height(128.dp)
                            .width(128.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = item.description,
                            modifier = Modifier.fillMaxWidth(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListSamplePreview() {
    Hw3_1Theme {
        ListSample()
    }
}