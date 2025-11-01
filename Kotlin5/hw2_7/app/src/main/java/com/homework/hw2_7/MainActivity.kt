package com.homework.hw2_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.homework.hw2_7.ui.theme.Hw2_7Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw2_7Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    PersonCard(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun PersonCard(
    modifier: Modifier = Modifier,
    name: String,
    surname: String,
    lastname: String? = "-",
    phone: String? = "-",
    address: String? = "-"
) {
     Row (
         verticalAlignment = Alignment.CenterVertically,
         modifier = modifier
             .background(Color.LightGray)
             .fillMaxWidth()
             .padding(12.dp)
     ) {
         Column (
             modifier = modifier.weight(1f)
         ) {
             Text(text = "Имя: $name", fontSize = 12.sp)
             Text(text = "Отчество: $lastname", fontSize = 12.sp)
             Text(text = "Фамилия: $surname", fontSize = 12.sp)
             Text(text = "Мобильный телефон: $phone", fontSize = 12.sp)
             Text(text = "Адрес: $address", fontSize = 12.sp)
         }

         Image(
             painter = painterResource(R.drawable.person_icon),
             contentDescription = "Простая картинка",
             modifier = Modifier
                 .size(64.dp)
                 .clip(CircleShape)
         )
     }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PersonCardPreview() {
    Hw2_7Theme {
        Column {
            PersonCard(
                modifier = Modifier.padding(12.dp),
                name = "Евгений", surname = "Андреевич",
                lastname = "Лукашин",
                phone = "+ 7 495 495 95 95",
                address = "г. Москва, 3-я Улица Строителей, д. 25, кв. 12"
            )

            PersonCard(
                modifier = Modifier.padding(12.dp),
                name = "Василий", surname = "Егорович",
                lastname = "Кузякин",
                phone = null,
                address = "Ивановская область, дер. Крутово, д. 4"
            )

            PersonCard(
                modifier = Modifier.padding(12.dp),
                name = "Людмила", surname = "Прокофьевна",
                lastname = "Калутина",
                phone = "+ 7 495 788 78 78",
                address = "г. Москва, Большая Никитинская, д. 43, кв. 290"
            )
        }

    }
}