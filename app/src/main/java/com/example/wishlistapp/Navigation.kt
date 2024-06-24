package com.example.wishlistapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.wishlistapp.screen.AddEditDetailView
import com.example.wishlistapp.screen.HomeView
import com.example.wishlistapp.screen.Screen

@Composable
fun Navigation(
    viewModel: WishViewModel,
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route ) {

        composable(route = Screen.HomeScreen.route) {
            HomeView(
                viewModel,
                navController = navController,
                title = "WishList",
                onBackNavClicked = {
                if(navController.previousBackStackEntry != null) {
                    navController.navigateUp()
                }
            })
        }
        composable(
            route = Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                    nullable = false
                    defaultValue = 0L
                }
            )
        ) {entry ->
            val id = if(entry.arguments != null) entry.arguments!!.getLong("id") else 0L
            AddEditDetailView(
                id = id,
                viewModel = viewModel,
                navController = navController,
                onBackNavClicked = {
                    if(navController.previousBackStackEntry != null) {
                        navController.navigateUp()
                    }
                }
            )
        }
    }
}