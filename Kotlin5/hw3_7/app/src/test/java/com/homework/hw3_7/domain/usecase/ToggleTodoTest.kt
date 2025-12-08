package com.homework.hw3_7.domain.usecase

import com.homework.hw3_7.domain.repository.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class ToggleTodoTest {

    @Test
    fun `toggleTodoUseCase changes completion status`() = runBlocking {
        val mockRepository = mock<TodoRepository>()
        val taskId = 1
        val useCase = ToggleTodoUseCase(mockRepository)

        useCase(taskId)

        verify(mockRepository).toggleTodo(taskId)
    }
}