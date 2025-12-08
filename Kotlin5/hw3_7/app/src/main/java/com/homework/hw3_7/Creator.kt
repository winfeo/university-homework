package com.homework.hw3_7

import android.content.Context
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.homework.hw3_7.data.local.TodoJsonDataSource
import com.homework.hw3_7.data.repository.TodoRepositoryImpl
import com.homework.hw3_7.domain.repository.TodoRepository
import com.homework.hw3_7.domain.usecase.GetTodosUseCase
import com.homework.hw3_7.domain.usecase.ToggleTodoUseCase
import com.homework.hw3_7.presentation.viewmodel.TodoListViewModel

object Creator {

    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
    }

    fun provideViewModelFactory() = viewModelFactory {
        initializer {
            val dataSource = TodoJsonDataSource(context)
            val repository: TodoRepository = TodoRepositoryImpl(dataSource)
            val getTodosUseCase = GetTodosUseCase(repository)
            val toggleTodoUseCase = ToggleTodoUseCase(repository)

            TodoListViewModel(getTodosUseCase, toggleTodoUseCase)
        }
    }
}