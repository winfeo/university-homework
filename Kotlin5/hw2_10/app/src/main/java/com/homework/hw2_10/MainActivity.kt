package com.homework.hw2_10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import com.homework.hw2_10.ui.theme.Hw2_10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Hw2_10Theme {
                Surface() {
                    NavigationService()
                }
            }
        }
    }
}