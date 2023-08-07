package id.ajiguna.newsapp.source.network

import id.ajiguna.newsapp.source.news.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("everything")
    suspend fun fetchNews(
        @Query("apiKey") apikey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") q: String,
        @Query("page") page: Int
    ) : NewsModel
}