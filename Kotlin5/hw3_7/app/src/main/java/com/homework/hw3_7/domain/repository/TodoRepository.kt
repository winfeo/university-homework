package com.homework.hw3_7.domain.repository

import com.homework.hw3_7.domain.model.TodoItem
interface TodoRepository {
    suspend fun getTodos(): List<TodoItem>
    suspend fun toggleTodo(id: Int)
}