package com.dscreate_app.vkclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateTo(route: String) {
        navHostController.navigate(route) {
            //Оставляет лишь стартовый экран при переходах по табам и возврате на кнопку <-
            popUpTo(navHostController.graph.startDestinationId) { // Получает у graph id стартового экрана
                saveState = true // сохраняет state экрана при возврате на этот экран
            }
            launchSingleTop = true //Запускает только 1 composable функцию на клик.
            restoreState = true // Восстанавливает сохран state после переходов по табам.
        }

    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState {
    return remember {
        NavigationState(navHostController)
    }
}