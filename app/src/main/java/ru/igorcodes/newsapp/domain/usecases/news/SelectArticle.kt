package ru.igorcodes.newsapp.domain.usecases.news

import ru.igorcodes.newsapp.data.local.NewsDao
import ru.igorcodes.newsapp.domain.model.Article

class SelectArticle(
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url: String): Article? {
       return newsDao.getArticle(url)
    }
}