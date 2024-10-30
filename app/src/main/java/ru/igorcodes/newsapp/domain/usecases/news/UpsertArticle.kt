package ru.igorcodes.newsapp.domain.usecases.news

import ru.igorcodes.newsapp.data.local.NewsDao
import ru.igorcodes.newsapp.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}