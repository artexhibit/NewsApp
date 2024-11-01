package ru.igorcodes.newsapp.domain.usecases.news

import kotlinx.coroutines.flow.Flow
import ru.igorcodes.newsapp.domain.model.Article
import ru.igorcodes.newsapp.domain.repository.NewsRepository

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}