package id.ajiguna.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ajiguna.newsapp.source.news.CategoryModel

import id.ajiguna.newsapp.source.news.NewsModel
import id.ajiguna.newsapp.source.news.NewsRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module
import kotlin.math.ceil

val homeViewModule = module {
    factory { HomeViewModel(get()) }
}
class HomeViewModel(val newsRepository: NewsRepository) : ViewModel() {
    val title = "Berita"
    val category by lazy { MutableLiveData<String>() }
    val message by lazy { MutableLiveData<String>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadMore by lazy { MutableLiveData<Boolean>() }
    val news by lazy { MutableLiveData<NewsModel>() }

    init {
        category.value = ""
        message.value = null
    }

    var  query = ""
    var page = 1
    var total = 1

    fun fetch(){
        if (page > 1) loadMore.value = true else
            loading.value = true
        viewModelScope.launch {
            try {
                val response = newsRepository.fetchNews(
                    category.value!!,
                    query,
                    page
                )
                news.value = response
                val totalResults : Double = response.totalResults / 20.0
                total = ceil(totalResults).toInt()
                page ++
                loading.value = false
                loadMore.value = false
            } catch (e: Exception){
                message.value = "Terjadi kesalahan"
            }
        }
    }

    val categories = listOf(
        CategoryModel("","Berita Utama"),
        CategoryModel("business","Bisnis"),
        CategoryModel("entertainment","Hiburan"),
        CategoryModel("general","Umum"),
        CategoryModel("health","Kesehatan"),
        CategoryModel("science","Sains"),
        CategoryModel("sport","Olahraga"),
        CategoryModel("technology","Teknologi")
    )
}