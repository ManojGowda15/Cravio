package com.example.cravio.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cravio.R
import com.example.cravio.navigation.Routes
import com.example.cravio.utils.SharedPreferencesUtil
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.layout.BoxWithConstraints

@Composable
fun Register(navController: NavController) {
    val context = LocalContext.current
    var phoneNumber by remember { mutableStateOf("") }
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val configuration = LocalConfiguration.current
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val bannerHeight = screenHeight * 0.25f
        val padding = screenWidth * 0.05f
        val skipFont = (screenWidth.value * 0.035).sp
        val titleFont = (screenWidth.value * 0.09).sp
        val subtitleFont = (screenWidth.value * 0.03).sp
        val welcomeFont = (screenWidth.value * 0.06).sp
        val createFont = (screenWidth.value * 0.04).sp
        val textFieldTop = screenHeight * 0.02f
        val columnStart = screenWidth * 0.45f
        val columnTop = screenHeight * 0.04f
        val mainColumnTop = screenHeight * 0.18f
        // Banner and skip
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(bannerHeight)
        ) {
            Image(
                painter = painterResource(id = R.drawable.registerbanner),
                contentDescription = "Register Banner",
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "Skip >",
                color = Color.White,
                fontSize = skipFont,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(padding)
                    .clickable {
                        SharedPreferencesUtil.setRegistered(context, true)
                        navController.navigate(Routes.Home.routes) {
                            popUpTo(0) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = columnStart, top = columnTop)
            ) {
                Text(
                    text = "CRAVIO",
                    fontSize = titleFont,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Login to unlock awesome benefit",
                    fontSize = subtitleFont,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = mainColumnTop, start = padding, end = padding)
        ) {
            Text(
                text = "Welcome to Cravio!",
                fontSize = welcomeFont,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Create your account below.",
                fontSize = createFont,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
            )
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                        phoneNumber = it
                    }
                },
                label = { Text("Phone Number") },
                placeholder = { "Enter your phone number" },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = textFieldTop)
            )
        }
    }
}
