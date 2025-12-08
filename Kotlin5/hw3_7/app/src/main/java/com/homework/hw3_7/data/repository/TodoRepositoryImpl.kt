package com.homework.hw3_7.data.repository

import com.homework.hw3_7.data.local.TodoJsonDataSource
import com.homework.hw3_7.domain.model.TodoItem
import com.homework.hw3_7.domain.repository.TodoRepository
import kotlinx.coroutines.delay

class TodoRepositoryImpl(
    private val dataSource: TodoJsonDataSource
): TodoRepository {
    private val todos = mutableListOf<TodoItem>()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        val dtos = dataSource.getTodos()
        todos.clear()
        todos.addAll(dtos.map { dto ->
            TodoItem(
                id = dto.id,
                title = dto.title,
                description = dto.description,
                isCompleted = dto.isCompleted
            )
        })
    }

    override suspend fun getTodos(): List<TodoItem> {
        return todos.toList()
    }

    override suspend fun toggleTodo(id: Int) {
        val index = todos.indexOfFirst { it.id == id }
        if (index != -1) {
            val todo = todos[index]
            todos[index] = todo.copy(isCompleted = !todo.isCompleted)
        }
    }
}