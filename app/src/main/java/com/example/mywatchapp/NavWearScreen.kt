package com.example.mywatchapp

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.mywatchapp.screen.WearGridScreen
import com.example.mywatchapp.screen.WearHomeScreen
import com.example.mywatchapp.screen.WearMusicScreen
import com.example.mywatchapp.screen.WearListApp

@Composable
fun NavWearScreen(){
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            WearListApp(navController)
        }
        composable("music"){ WearMusicScreen() }
        composable("grid"){
//            backStackEntry ->
//            val startPage = backStackEntry.arguments?.getString("startPage")?.toIntOrNull() ?: 1
            WearGridScreen()
        }
    }
}