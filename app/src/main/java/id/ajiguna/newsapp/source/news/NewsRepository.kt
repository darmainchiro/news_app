package id.ajiguna.newsapp.source.news

import id.ajiguna.beritaindo.network.ApiClient
import org.koin.android.BuildConfig
import org.koin.dsl.module


val repositoryModule = module {
    factory { NewsRepository(get()) }
}

class NewsRepository(
    private val api: ApiClient
) {
    suspend fun fetchHeadline(): NewsModel{
        return api.fetchHeadline(
            BuildConfig.API_KEY,
            "us",
            1

        )
    }
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
}