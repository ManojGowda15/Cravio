package com.example.cravio.navigation

sealed class Routes(val routes: String) {
    object Splash: Routes("splash")
    object OnBoarding: Routes("onboarding-screen")
    object Register: Routes("register")
}