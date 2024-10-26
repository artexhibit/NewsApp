package ru.igorcodes.newsapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.igorcodes.newsapp.domain.model.Article

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}