package com.homework.hw3_7.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.hw3_7.domain.model.TodoItem
import com.homework.hw3_7.domain.usecase.GetTodosUseCase
import com.homework.hw3_7.domain.usecase.ToggleTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val toggleTodoUseCase: ToggleTodoUseCase
) : ViewModel() {

    private val _todos = MutableStateFlow<List<TodoItem>>(emptyList())
    val todos: StateFlow<List<TodoItem>> = _todos.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val todosList = getTodosUseCase()
                _todos.value = todosList
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun toggleTodo(id: Int) {
        viewModelScope.launch {
            toggleTodoUseCase(id)
            _todos.update { currentTodos ->
                currentTodos.map { todo ->
                    if (todo.id == id) {
                        todo.copy(isCompleted = !todo.isCompleted)
                    } else {
                        todo
                    }
                }
            }
        }
    }

    fun getTodoById(id: Int): TodoItem? {
        return _todos.value.find { it.id == id }
    }
}