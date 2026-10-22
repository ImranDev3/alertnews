package com.alertnews.app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import com.alertnews.app.data.model.NewsItem
import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.model.IpoEvent
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NewsViewModel(private val application: Application) : AndroidViewModel(application) {

    private val repo = (application as MainApplication).getNewsRepository()

    val latestNews = repo.latestNews
    val highImpactNews = repo.highImpact
    val calendarEvents = repo.calendarEvents
    val ipoEvents = repo.ipoEvents

    val unreadHighImpactCount = repo.getUnreadHighImpact()

    fun refreshNews() {
        viewModelScope.launch {
            repo.refreshNews()
        }
    }

    fun refreshCalendar() {
        viewModelScope.launch {
            repo.refreshCalendar()
        }
    }

    fun refreshIpo() {
        viewModelScope.launch {
            repo.refreshIpo()
        }
    }

    fun toggleFavorite(newsId: Long) {
        viewModelScope.launch {
            repo.toggleFavorite(newsId)
        }
    }

    fun markAsRead(newsId: Long) {
        viewModelScope.launch {
            repo.markAsRead(newsId)
        }
    }

    fun getFavorites(): List<NewsItem> {
        return repo.getFavorites()
    }

    fun getUnreadHighImpactCount(): Int = repo.getUnreadHighImpact()
}