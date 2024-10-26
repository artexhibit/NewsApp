package ru.igorcodes.newsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.igorcodes.newsapp.data.manager.LocalUserManagerImpl
import ru.igorcodes.newsapp.domain.manager.LocalUserManager
import ru.igorcodes.newsapp.domain.usecases.app_entry.AppEntryUseCases
import ru.igorcodes.newsapp.domain.usecases.app_entry.ReadAppEntry
import ru.igorcodes.newsapp.domain.usecases.app_entry.SaveAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application) : LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) : AppEntryUseCases {
        return AppEntryUseCases(readAppEntry = ReadAppEntry(localUserManager), saveAppEntry = SaveAppEntry(localUserManager))
    }

}