package com.homework.hw3_3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.homework.hw3_3.ui.theme.Hw3_3Theme

class MainActivity : ComponentActivity() {
    val TAG = "Тег"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Работа метода onCreate")
        enableEdgeToEdge()
        setContent {
            Hw3_3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Работа метода onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "Работа метода onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "Работа метода onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Работа метода onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Работа метода onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "Работа метода onRestart()")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}