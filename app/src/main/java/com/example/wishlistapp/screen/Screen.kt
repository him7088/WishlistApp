package com.example.wishlistapp.screen

sealed class Screen (val route : String){
    object HomeScreen : Screen(route = "homeScreen")
    object AddScreen : Screen(route = "addScreen")
}
