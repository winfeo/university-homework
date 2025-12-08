package com.homework.hw3_7.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.homework.hw3_7.Creator
import com.homework.hw3_7.presentation.ui.screen.TodoDetailsScreen
import com.homework.hw3_7.presentation.ui.screen.TodoListScreen
import com.homework.hw3_7.presentation.viewmodel.TodoListViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val viewModel: TodoListViewModel = viewModel(
        factory = Creator.provideViewModelFactory()
    )

    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        composable("todo_list") {
            TodoListScreen(
                viewModel = viewModel,
                onTodoClick = { todoId ->
                    navController.navigate("todo_detail/$todoId")
                }
            )
        }

        composable("todo_detail/{todoId}") { backStackEntry ->
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull() ?: 0
            TodoDetailsScreen(
                viewModel = viewModel,
                todoId = todoId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}