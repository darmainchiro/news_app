package id.ajiguna.beritaindo.network

import id.ajiguna.newsapp.source.news.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("top-headlines")
    suspend fun fetchHeadline(
        @Query("apiKey") apikey: String,
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
    ) : NewsModel

    @GET("everything")
    suspend fun fetchNews(
        @Query("apiKey") apikey: String,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("q") q: String,
        @Query("page") page: Int
    ) : NewsModel
}