package ru.igorcodes.newsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.igorcodes.newsapp.data.manager.LocalUserManagerImpl
import ru.igorcodes.newsapp.data.remote.NewsApi
import ru.igorcodes.newsapp.data.repository.NewsRepositoryImpl
import ru.igorcodes.newsapp.domain.manager.LocalUserManager
import ru.igorcodes.newsapp.domain.repository.NewsRepository
import ru.igorcodes.newsapp.domain.usecases.app_entry.AppEntryUseCases
import ru.igorcodes.newsapp.domain.usecases.app_entry.ReadAppEntry
import ru.igorcodes.newsapp.domain.usecases.app_entry.SaveAppEntry
import ru.igorcodes.newsapp.domain.usecases.news.GetNews
import ru.igorcodes.newsapp.domain.usecases.news.NewsUseCases
import ru.igorcodes.newsapp.domain.usecases.news.SearchNews
import ru.igorcodes.newsapp.util.Constants.BASE_URL
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
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository)
        )
    }
}