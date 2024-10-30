package ru.igorcodes.newsapp.domain.usecases.news

import kotlinx.coroutines.flow.Flow
import ru.igorcodes.newsapp.data.local.NewsDao
import ru.igorcodes.newsapp.domain.model.Article

class SelectArticles(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> {
       return newsDao.getArticles()
    }
}