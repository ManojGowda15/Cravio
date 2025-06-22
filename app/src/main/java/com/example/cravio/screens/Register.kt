package com.example.cravio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil3.compose.AsyncImage
import com.example.cravio.R

@Composable
fun Register() {
    Box(modifier = Modifier) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = R.drawable.otpbgless,
                contentDescription = "Otp Sent",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}