package ru.igorcodes.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import ru.igorcodes.newsapp.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Discover New Horizons",
        description = "Explore the unknown with exciting adventures.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Connect with People",
        description = "Build meaningful relationships and share experiences.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Achieve Your Goals",
        description = "Set your targets and work towards success.",
        image = R.drawable.onboarding3
    )
)