package com.homework.hw3_7.domain.usecase

import com.homework.hw3_7.domain.model.TodoItem
import com.homework.hw3_7.domain.repository.TodoRepository

class GetTodosUseCase(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(): List<TodoItem> {
        return repository.getTodos()
    }
}