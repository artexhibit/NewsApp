package ru.igorcodes.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.igorcodes.newsapp.data.local.NewsDao
import ru.igorcodes.newsapp.data.remote.NewsApi
import ru.igorcodes.newsapp.data.remote.NewsPagingSource
import ru.igorcodes.newsapp.data.remote.SearchNewsPagingSource
import ru.igorcodes.newsapp.domain.model.Article
import ru.igorcodes.newsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi, sources.joinToString(separator = ","))
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(newsApi, searchQuery, sources.joinToString(separator = ","))
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }
}