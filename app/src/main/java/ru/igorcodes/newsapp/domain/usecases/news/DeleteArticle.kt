package ru.igorcodes.newsapp.domain.usecases.news

import ru.igorcodes.newsapp.domain.model.Article
import ru.igorcodes.newsapp.domain.repository.NewsRepository

class DeleteArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) {
        newsRepository.deleteArticle(article)
    }
}