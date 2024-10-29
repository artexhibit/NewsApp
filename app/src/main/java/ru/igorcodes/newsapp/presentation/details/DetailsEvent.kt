package ru.igorcodes.newsapp.presentation.details

sealed class DetailsEvent {
    object SaveArticle: DetailsEvent()
}