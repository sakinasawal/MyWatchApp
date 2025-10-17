package com.example.mywatchapp

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.mywatchapp.screen.PlaySong
import com.example.mywatchapp.screen.WearListApp

@Composable
fun NavWearScreen(){
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            WearListApp(onClick = {
                navController.navigate("details")
            })
        }

        composable("details"){
            PlaySong()
        }
    }
}