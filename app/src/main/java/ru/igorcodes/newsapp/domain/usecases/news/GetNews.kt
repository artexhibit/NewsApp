package ru.igorcodes.newsapp.domain.usecases.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.igorcodes.newsapp.domain.model.Article
import ru.igorcodes.newsapp.domain.repository.NewsRepository

class GetNews(private val newsRepository: NewsRepository) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources)
    }
}