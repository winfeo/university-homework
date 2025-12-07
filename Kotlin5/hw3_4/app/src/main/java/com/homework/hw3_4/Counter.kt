package com.homework.hw3_4

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class Counter: ViewModel() {
    private var _count = mutableStateOf(0)
    val count = _count

    fun plusOne() {
        _count.value++
    }
}