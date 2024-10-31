package ru.igorcodes.newsapp.presentation.details

import ru.igorcodes.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article): DetailsEvent()
    object RemoveSideEffect: DetailsEvent()
}