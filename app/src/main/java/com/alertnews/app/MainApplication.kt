package com.alertnews.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alertnews.app.data.model.NewsItem
import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.model.IpoEvent
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainApplication : Application() {

    private val _newsRepo = MutableStateFlow<NewsRepository?>(null)

    val newsRepo: Flow<NewsRepository> = _newsRepo.stateIn(
        scope = kotlinx.coroutines.coroutineContext + kotlinx.coroutines.Dispatchers.IO,
        started = kotlinx.coroutines.flow.Latest
    )

    override fun onCreate() {
        super.onCreate()
        initRepository()
    }

    private fun initRepository() {
        val dataSources = listOf(
            RssNewsSource(),
            FactoryCalendarSource(),
            IpoNewsSource()
        )
        _newsRepo.value = NewsRepository(NewsDatabase.create(this), dataSources)
    }

    fun getNewsRepository(): NewsRepository {
        return _newsRepo.value ?: NewsRepository(NewsDatabase.create(this), emptyList())
    }
}