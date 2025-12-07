package com.homework.hw3_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.homework.hw3_5.ui.theme.Hw3_5Theme
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Details
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw3_5Theme {
                MinecraftApp()
            }
        }
    }
}

@Composable
fun MinecraftApp(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = Home){
        composable<Home> { HomeScreen(navController) }
        composable<Details> { DetailsScreen() }
    }

}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Картина Minecraft",
                modifier = Modifier
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Картина Minecraft",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                navController.navigate(Details)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Читать подробнее")
        }
    }
}

@Composable
fun DetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Небольшое описание:",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Перссон написал Minecraft на языке программирования Java с" +
                    "использованием библиотеки графического вывода LWJGL," +
                    "черпая идеи из таких игр, как Dwarf Fortress, Dungeon Keeper " +
                    "и Infiniminer. Minecraft даёт в распоряжение игрока процедурно" +
                    " генерируемый и изменяемый трёхмерный мир, полностью состоящий" +
                    " из кубов — его можно свободно перестраивать, создавая из этих" +
                    " кубов сложные сооружения — эта особенность делает игру схожей" +
                    " с различными конструкторами, такими как Lego. Minecraft не" +
                    " ставит перед игроком каких-либо конкретных целей, но предлагает" +
                    " ему свободу действий: например, игрок может исследовать мир," +
                    " добывать полезные ископаемые, сражаться с противниками и многое другое",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MinecraftAppPreview() {
    Hw3_5Theme {
//        DetailsScreen()
        HomeScreen(rememberNavController())
    }
}