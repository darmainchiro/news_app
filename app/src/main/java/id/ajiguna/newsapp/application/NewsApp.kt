package id.ajiguna.newsapp.application

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import id.ajiguna.beritaindo.network.networkModule
import id.ajiguna.newsapp.source.news.repositoryModule
import id.ajiguna.newsapp.ui.home.homeModule
import id.ajiguna.newsapp.ui.home.homeViewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        startKoin {
            androidLogger()
            androidContext(this@NewsApp)
            modules(
                networkModule,
                repositoryModule,
                homeModule,
                homeViewModule
            )
        }
    }
}