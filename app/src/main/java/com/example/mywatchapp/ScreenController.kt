package com.example.mywatchapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.material3.AppScaffold
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.mywatchapp.screen.GenerateQrScreen
import com.example.mywatchapp.screen.NfcListCardScreen
import com.example.mywatchapp.screen.NfcScanScreen
import com.example.mywatchapp.screen.VoucherListScreen
import com.example.mywatchapp.screen.WearGridScreen
import com.example.mywatchapp.screen.WearMusicScreen
import com.example.mywatchapp.screen.WearListApp

@Composable
fun ScreenController(){
    val navController = rememberSwipeDismissableNavController()

    val LocalNavController = staticCompositionLocalOf<NavController> {
        error("No MainViewModel provided")
    }

    AppScaffold{
        SwipeDismissableNavHost(
            navController = navController,
            startDestination = "home"
        ){
            composable("home"){ WearListApp(navController) }

            composable("grid"){ WearGridScreen() }

            composable("music"){ WearMusicScreen() }

            composable("nfc_list"){ NfcListCardScreen(LocalNavController.current) }

            composable(
                route = "nfc_scan/{cardId}",
                arguments = listOf(navArgument("cardId"){ type = NavType.StringType })
            ) { backStackEntry ->
                val cardId = backStackEntry.arguments?.getString("cardId") ?: ""
                NfcScanScreen(LocalNavController.current, cardId)
            }

            composable("voucher") { VoucherListScreen(LocalNavController.current) }

            composable(
                route = "qr/{voucherId}",
                arguments = listOf(navArgument("voucherId") { type = NavType.StringType } )
            ) {
                    backStackEntry ->
                val voucherId = backStackEntry.arguments?.getString("voucherId") ?: ""
                GenerateQrScreen(LocalNavController.current, voucherId)
            }
        }
    }
}