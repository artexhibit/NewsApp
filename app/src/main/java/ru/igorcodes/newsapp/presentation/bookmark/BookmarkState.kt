package ru.igorcodes.newsapp.presentation.bookmark

import ru.igorcodes.newsapp.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
