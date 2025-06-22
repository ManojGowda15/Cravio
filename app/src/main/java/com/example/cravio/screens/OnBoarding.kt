package com.example.cravio.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
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
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.cravio.utils.SharedPreferencesUtil
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun OnBoarding(
    page: OnBoardingPage,
    showNext: Boolean = false,
    onNext: (() -> Unit)? = null,
    showGetStarted: Boolean = false,
    onGetStarted: (() -> Unit)? = null
) {
    val buttonColor = Color(0xFF1EB980)
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val configuration = LocalConfiguration.current
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val padding = screenWidth * 0.06f
        val buttonWidth = screenWidth * 0.5f
        val buttonHeight = screenHeight * 0.07f
        val titleFont = (screenWidth.value * 0.09).sp
        val subtitleFont = (screenWidth.value * 0.035).sp
        val descFont = (screenWidth.value * 0.035).sp
        val navFont = (screenWidth.value * 0.045).sp
        val indicatorSize = screenWidth * 0.03f
        val dividerWidth = screenWidth * 0.8f
        val navPadding = screenWidth * 0.06f
        val navBottom = screenHeight * 0.06f
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = "Onboarding Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.weight(1.25f))
            Column {
                Text(
                    text = page.title,
                    fontSize = titleFont,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(padding * 0.2f))
                Text(
                    text = page.subtitle,
                    fontSize = subtitleFont,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(padding * 0.04f))
                HorizontalDivider(
                    color = Color.White,
                    thickness = 1.dp,
                    modifier = Modifier.width(dividerWidth)
                )
                Spacer(modifier = Modifier.height(padding * 0.04f))
                Text(
                    text = page.description,
                    fontSize = descFont,
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
                            .width(buttonWidth)
                            .height(buttonHeight)
                            .padding(end = navPadding),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        )
                    ) {
                        Text(text = "Next", fontSize = navFont)
                    }
                }
                if (showGetStarted && onGetStarted != null) {
                    Button(
                        onClick = onGetStarted,
                        modifier = Modifier
                            .width(buttonWidth)
                            .height(buttonHeight)
                            .padding(end = navPadding),
                        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        )
                    ) {
                        Text(text = "Get Started", fontSize = navFont)
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
    val context = LocalContext.current
    val pagerState = rememberPagerState(pageCount = { onboardingPages.size }, initialPage = 0)
    val coroutineScope = rememberCoroutineScope()
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val configuration = LocalConfiguration.current
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val navFont = (screenWidth.value * 0.045).sp
        val navPadding = screenWidth * 0.06f
        val navBottom = screenHeight * 0.06f
        val indicatorSize = screenWidth * 0.03f
        val navButtonWidth = screenWidth * 0.15f
        val navButtonSpacer = screenWidth * 0.15f
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
                .padding(bottom = navBottom)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Prev button
            if (pagerState.currentPage != 0) {
                Text(
                    text = "Prev",
                    color = Color.White,
                    fontSize = navFont,
                    modifier = Modifier
                        .padding(start = navPadding)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                )
            } else {
                Spacer(modifier = Modifier.width(navButtonSpacer))
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
                            .padding(horizontal = indicatorSize * 0.3f)
                            .size(indicatorSize)
                            .clip(CircleShape)
                            .background(if (isSelected) Color(0xFF3B82F6) else Color(0xFFD1D5DB))
                    )
                }
            }
            // Next or Get Started button
            if (pagerState.currentPage != onboardingPages.lastIndex) {
                Text(
                    text = "Next",
                    color = Color(0xFF3B82F6),
                    fontSize = navFont,
                    modifier = Modifier
                        .padding(end = navPadding)
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
                    fontSize = navFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = navPadding)
                        .clickable {
                            SharedPreferencesUtil.setFirstLaunch(context, false)
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
