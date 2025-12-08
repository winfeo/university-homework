package com.homework.hw3_7.domain.usecase

import com.homework.hw3_7.domain.repository.TodoRepository

class ToggleTodoUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.toggleTodo(id)
    }
}