package id.ajiguna.newsapp.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ajiguna.newsapp.source.news.NewsRepository
import org.koin.dsl.module

val bookmarkViewModule = module {
    factory { BookmarkViewModel(get()) }
}
class BookmarkViewModel(newsRepository: NewsRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}