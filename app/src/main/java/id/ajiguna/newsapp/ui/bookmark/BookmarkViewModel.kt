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
    val title = "Bookmark"
    val articles = newsRepository.db.findAll()

}