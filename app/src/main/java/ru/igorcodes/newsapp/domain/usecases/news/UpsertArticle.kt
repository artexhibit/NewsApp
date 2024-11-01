package ru.igorcodes.newsapp.domain.usecases.news

import ru.igorcodes.newsapp.domain.model.Article
import ru.igorcodes.newsapp.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.upsertArticle(article)
    }
}