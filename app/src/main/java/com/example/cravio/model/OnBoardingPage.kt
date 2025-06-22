package com.example.cravio.model

import com.example.cravio.R

data class OnBoardingPage(
    val imageRes: Int,
    val title: String,
    val subtitle: String,
    val description: String
)

val onboardingPages = listOf(
    OnBoardingPage(
        R.drawable.onboarding1,
        "CRAVIO",
        "Bites  • Sips  • Daily Needs",
        "Tap, order, eat – repeat."
    ),
    OnBoardingPage(
        R.drawable.onboarding2,
        "Fast Delivery",
        "Everything You Need",
        "Right to your doorstep."
    ),
    OnBoardingPage(
        R.drawable.onboarding3,
        "Start Your Journey",
        "Freshness Every Tap",
        "Let’s get started!"
    )
)

