package id.ajiguna.newsapp.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.ajiguna.newsapp.source.news.ArticleModel
import id.ajiguna.newsapp.utils.SourceConverter

@Database(
    entities = [ArticleModel::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(SourceConverter::class)
abstract class DatabaseClient: RoomDatabase() {
    abstract val newsDao: NewsDao
}