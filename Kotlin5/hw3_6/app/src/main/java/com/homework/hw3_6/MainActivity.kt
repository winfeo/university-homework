package com.homework.hw3_6

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.homework.hw3_6.ui.theme.Hw3_6Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw3_6Theme {
                val windowSize = calculateWindowSizeClass(this)
                AdaptiveApp(windowSize.widthSizeClass)
            }
        }
    }
}

@Composable
fun AdaptiveApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    when (windowSize) {
        WindowWidthSizeClass.Compact -> { OneColumnScreen() }
        WindowWidthSizeClass.Medium -> { TwoColumnsScreen() }
        WindowWidthSizeClass.Expanded -> { TwoColumnsScreen() }
    }
}

@Composable
fun OneColumnScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        Text(text = "Группа Дня", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(25.dp))
        Card(
            modifier = Modifier.padding(horizontal = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Black Sabbath",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp))

            Text(
                text = "Black Sabbath — британская рок-группа, образованная в " +
                        "Бирмингеме, Англия, в 1968 году и оказавшая значительное " +
                        "влияние на развитие рок-музыки, прежде всего, хеви-метала. " +
                        "Дебютный альбом Black Sabbath считается одним из первых " +
                        "хеви-метал-альбомов, заложившим, кроме того, фундамент и " +
                        "для последующего развития дум-метала. Десять альбомов " +
                        "группы входили в первую десятку UK Albums Chart. К 2012 " +
                        "году общий тираж альбомов Black Sabbath приблизился к 70 миллионам.",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun TwoColumnsScreen(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp)
    ) {
        Text(
            text = "Группа Дня",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp))

        Spacer(modifier = Modifier.height(25.dp))

        Row() {
            Text(
                text = "Black Sabbath",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp))

            Card(
                modifier = Modifier.padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium
            ) {

                Text(
                    text = "Black Sabbath — британская рок-группа, образованная в " +
                            "Бирмингеме, Англия, в 1968 году и оказавшая значительное " +
                            "влияние на развитие рок-музыки, прежде всего, хеви-метала. " +
                            "Дебютный альбом Black Sabbath считается одним из первых " +
                            "хеви-метал-альбомов, заложившим, кроме того, фундамент и " +
                            "для последующего развития дум-метала. Десять альбомов " +
                            "группы входили в первую десятку UK Albums Chart. К 2012 " +
                            "году общий тираж альбомов Black Sabbath приблизился к 70 миллионам.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun OneColumnPreview() {
//    Hw3_6Theme {
//        OneColumnScreen()
//    }
//}

@Preview(showBackground = true, showSystemUi = true, device = TABLET)
@Composable
fun TwoColumnsPreview(){
    Hw3_6Theme {
        TwoColumnsScreen()
    }
}