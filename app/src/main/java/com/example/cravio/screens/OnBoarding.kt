package com.example.cravio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cravio.model.OnBoardingPage
import com.example.cravio.model.onboardingPages
import kotlinx.coroutines.launch
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.navigation.NavController

@Composable
fun OnBoarding(
    page: OnBoardingPage,
    showNext: Boolean = false,
    onNext: (() -> Unit)? = null,
    showGetStarted: Boolean = false,
    onGetStarted: (() -> Unit)? = null
) {
    val buttonColor = Color(0xFF1EB980)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = "Onboarding Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.weight(1.25f))
            Column {
                Text(
                    text = page.title,
                    fontSize = 38.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = page.subtitle,
                    fontSize = 15.sp,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(1.dp))
                HorizontalDivider(
                    color = Color.White,
                    thickness = 1.dp,
                    modifier = Modifier.width(300.dp)
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = page.description,
                    fontSize = 15.sp,
                    color = Color.LightGray
                )
            }
            Spacer(modifier = Modifier.weight(0.2f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                if (showNext && onNext != null) {
                    Button(
                        onClick = onNext,
                        modifier = Modifier
                            .width(180.dp)
                            .height(56.dp)
                            .padding(end = 16.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        )
                    ) {
                        Text(text = "Next", fontSize = 20.sp)
                    }
                }
                if (showGetStarted && onGetStarted != null) {
                    Button(
                        onClick = onGetStarted,
                        modifier = Modifier
                            .width(180.dp)
                            .height(56.dp)
                            .padding(end = 16.dp),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        )
                    ) {
                        Text(text = "Get Started", fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun OnBoardingScreen(
    navController: NavController
) {
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    val isLastPage by remember {
        derivedStateOf {
            pagerState.currentPage == onboardingPages.lastIndex
        }
    }
    val isFirstPage by remember {
        derivedStateOf {
            pagerState.currentPage == 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnBoarding(
                page = onboardingPages[page]
            )
        }

        // Bottom navigation bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 70.dp)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Prev button
            if (!isFirstPage) {
                Text(
                    text = "Prev",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                )
            } else {
                Spacer(modifier = Modifier.width(56.dp)) // To align with button width
            }

            // Page indicators
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                repeat(onboardingPages.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) Color(0xFF3B82F6) else Color(0xFFD1D5DB))
                    )
                }
            }

            // Next or Get Started button
            if (!isLastPage) {
                Text(
                    text = "Next",
                    color = Color(0xFF3B82F6),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                )
            } else {
                Text(
                    text = "Get Started",
                    color = Color(0xFF3B82F6),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .clickable {
                            try {
                                navController.navigate(com.example.cravio.navigation.Routes.Register.routes)
                            } catch (e: Exception) {
                                android.util.Log.e("OnBoardingScreen", "Navigation error", e)
                            }
                        }
                )
            }
        }
    }
}
