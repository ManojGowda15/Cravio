package com.example.cravio.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cravio.screens.OnBoarding
import com.example.cravio.screens.Splash
import com.example.cravio.screens.OnBoardingScreen
import com.example.cravio.screens.Register

private const val TAG = "NavGraph"

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.routes
    ) {
        composable(Routes.Splash.routes) {
            Splash(navController)
        }

        composable(Routes.OnBoarding.routes) {
            OnBoardingScreen(navController)
        }

        composable(Routes.Register.routes) {
            Register()
        }
    }
}