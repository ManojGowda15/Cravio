package com.example.cravio.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cravio.R
import com.example.cravio.navigation.Routes
import kotlinx.coroutines.delay

private const val TAG = "SplashScreen"

@Composable
fun Splash(navController: NavController) {
    var isshowSplash by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        try {
            delay(2000) // Shows Splash Screen for 2 seconds
            navController.navigate(Routes.OnBoarding.routes) {
                popUpTo(Routes.Splash.routes) {
                    inclusive = true
                }
            }
        } catch (e: Exception) {
            Log.d(TAG, "Unknown Error Occured")
        } finally {
            isshowSplash = true
        }
    }
    SplashContent()
}

@Composable
private fun SplashContent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Splash Screen",
            modifier = Modifier.size(300.dp)
        )
    }
}