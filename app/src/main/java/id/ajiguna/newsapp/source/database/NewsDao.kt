package id.ajiguna.newsapp.source.database

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ajiguna.newsapp.source.news.ArticleModel

@Dao
interface NewsDao {
    @Query("SELECT * FROM tableArticle")
    fun findAll(): LiveData<List<ArticleModel>>

    @Query("SELECT COUNT(*) FROM tableArticle WHERE publishedAt=:publish")
    suspend fun find(publish: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(articleModel: ArticleModel)

    @Delete
    suspend fun remove(articleModel: ArticleModel)
}