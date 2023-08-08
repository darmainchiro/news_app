package id.ajiguna.newsapp.source.news

import id.ajiguna.newsapp.BuildConfig
import id.ajiguna.newsapp.source.network.ApiClient
import id.ajiguna.newsapp.source.database.NewsDao
import org.koin.dsl.module


val repositoryModule = module {
    factory { NewsRepository(get(), get()) }
}

class NewsRepository(
    private val api: ApiClient,
    val db: NewsDao
) {
    suspend fun fetchNews(
        category: String,
        query: String,
        page: Int
    ): NewsModel{

        return api.fetchNews(
            BuildConfig.API_KEY,
            "id",
            category,
            query,
            page
        )
    }

    suspend fun find(articleModel: ArticleModel) = db.find(articleModel.publishedAt)

    suspend fun save(articleModel: ArticleModel) {
        db.save(articleModel)
    }

    suspend fun remove(articleModel: ArticleModel){
        db.remove(articleModel)
    }
}