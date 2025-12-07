package com.homework.hw3_1

import androidx.annotation.DrawableRes

data class Card(
    val title: String,
    val description: String,
    @field:DrawableRes val imageResource: Int
)
