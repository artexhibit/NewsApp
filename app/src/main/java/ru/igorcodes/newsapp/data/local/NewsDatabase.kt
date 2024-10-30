package ru.igorcodes.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.igorcodes.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}