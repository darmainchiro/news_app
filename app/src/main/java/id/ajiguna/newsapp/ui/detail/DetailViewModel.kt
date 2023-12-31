package id.ajiguna.newsapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.ajiguna.newsapp.source.news.ArticleModel
import id.ajiguna.newsapp.source.news.NewsRepository
import kotlinx.coroutines.launch
import org.koin.dsl.module

val detailViewModule = module {
    factory { DetailViewModel(get()) }
}
class DetailViewModel(
    private val repository: NewsRepository
): ViewModel() {
    val isBookmark by lazy { MutableLiveData(0) }

    fun find(articleModel: ArticleModel) {
        viewModelScope.launch {
            isBookmark.value = repository.find(articleModel)
        }
    }

    fun bookmark(articleModel: ArticleModel){
        viewModelScope.launch {
            if (isBookmark.value == 0) repository.save((articleModel))
            else repository.remove(articleModel)
            find(articleModel)

        }
    }
}