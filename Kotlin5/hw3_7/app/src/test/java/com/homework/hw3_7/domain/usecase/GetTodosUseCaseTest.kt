package com.homework.hw3_7.domain.usecase

import com.homework.hw3_7.domain.model.TodoItem
import com.homework.hw3_7.domain.repository.TodoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetTodosUseCaseTest {

    @Test
    fun `getTodosUseCase returns 3 tasks`() = runBlocking {
        val mockRepository = mock<TodoRepository>()
        val expectedTasks = listOf(
            TodoItem(1, "Таска 1", "Описание 1", false),
            TodoItem(2, "Таска 2", "Описание 2", true),
            TodoItem(3, "Таска 3", "Описание 3", false)
        )

        whenever(mockRepository.getTodos()).thenReturn(expectedTasks)

        val useCase = GetTodosUseCase(mockRepository)

        val result = useCase()

        assertEquals("Возврщает 3 таски", 3, result.size)
        assertEquals("Таски должны совпадать", expectedTasks, result)
    }
}