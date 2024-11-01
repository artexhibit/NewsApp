package ru.igorcodes.newsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.igorcodes.newsapp.data.local.NewsDao
import ru.igorcodes.newsapp.data.local.NewsDatabase
import ru.igorcodes.newsapp.data.local.NewsTypeConverter
import ru.igorcodes.newsapp.data.manager.LocalUserManagerImpl
import ru.igorcodes.newsapp.data.remote.NewsApi
import ru.igorcodes.newsapp.data.repository.NewsRepositoryImpl
import ru.igorcodes.newsapp.domain.manager.LocalUserManager
import ru.igorcodes.newsapp.domain.repository.NewsRepository
import ru.igorcodes.newsapp.domain.usecases.app_entry.AppEntryUseCases
import ru.igorcodes.newsapp.domain.usecases.app_entry.ReadAppEntry
import ru.igorcodes.newsapp.domain.usecases.app_entry.SaveAppEntry
import ru.igorcodes.newsapp.domain.usecases.news.DeleteArticle
import ru.igorcodes.newsapp.domain.usecases.news.GetNews
import ru.igorcodes.newsapp.domain.usecases.news.NewsUseCases
import ru.igorcodes.newsapp.domain.usecases.news.SearchNews
import ru.igorcodes.newsapp.domain.usecases.news.SelectArticle
import ru.igorcodes.newsapp.domain.usecases.news.SelectArticles
import ru.igorcodes.newsapp.domain.usecases.news.UpsertArticle
import ru.igorcodes.newsapp.util.Constants.BASE_URL
import ru.igorcodes.newsapp.util.Constants.NEWS_DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application,
        newsTypeConverter: NewsTypeConverter
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        )
            .addTypeConverter(newsTypeConverter)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

    @Provides
    @Singleton
    fun provideNewsTypeConverter(): NewsTypeConverter {
        return NewsTypeConverter()
    }
}