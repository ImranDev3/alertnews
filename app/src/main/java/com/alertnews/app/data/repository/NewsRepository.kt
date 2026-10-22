package com.alertnews.app.data.repository

import com.alertnews.app.data.local.NewsDatabase
import com.alertnews.app.data.model.NewsItem
import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.model.IpoEvent
import com.alertnews.app.data.source.NewsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsRepository(
    private val database: NewsDatabase,
    private val dataSources: List<NewsDataSource>
) {

    // State flows for UI
    private val _latestNews = MutableStateFlow<List<NewsItem>>(emptyList())
    val latestNews: Flow<List<NewsItem>> = _latestNews.stateIn(
        scope = kotlinx.coroutines.coroutineContext + Dispatchers.IO,
        started = kotlinx.coroutines.flow.Latest
    )

    private val _highImpact = MutableStateFlow<List<NewsItem>>(emptyList())
    val highImpact: Flow<List<NewsItem>> = _highImpact.stateIn(
        scope = kotlinx.coroutines.coroutineContext + Dispatchers.IO,
        started = kotlinx.coroutines.flow.Latest
    )

    private val _calendarEvents = MutableStateFlow<List<EconomicCalendar>>(emptyList())
    val calendarEvents: Flow<List<EconomicCalendar>> = _calendarEvents.stateIn(
        scope = kotlinx.coroutines.coroutineContext + Dispatchers.IO,
        started = kotlinx.coroutines.flow.Latest
    )

    private val _ipoEvents = MutableStateFlow<List<IpoEvent>>(emptyList())
    val ipoEvents: Flow<List<IpoEvent>> = _ipoEvents.stateIn(
        scope = kotlinx.coroutines.coroutineContext + Dispatchers.IO,
        started = kotlinx.coroutines.flow.Latest
    )

    init {
        // Initial load on repository creation
        CoroutineScope(Dispatchers.IO).launch {
            fetchAndStoreNews()
            fetchAndStoreCalendar()
            fetchAndStoreIpo()
        }
    }

    // -----------------------------------------------------------------------
    // Data loading
    // -----------------------------------------------------------------------

    private fun fetchAndStoreNews() {
        val fresh = dataSources.mapNotNull { it.fetchLatestNews() }
            .flatten()
            .sortedByDescending { it.publishedAt }
            .distinctBy { it.id }

        _latestNews.value = fresh
        _highImpact.value = fresh.filter { it.impactLevel in setOf("critical", "high") }
        database.newsDao().insertNews(fresh)
    }

    private fun fetchAndStoreCalendar() {
        CoroutineScope(Dispatchers.IO).launch {
            val sources = dataSources
            val allCal = mutableListOf<EconomicCalendar>()

            for (source in sources) {
                try {
                    val cal = source.fetchCalendar()
                    allCal.addAll(cal)
                } catch (e: Exception) {
                    // Continue on error
                }
            }

            _calendarEvents.value = allCal.sortedByDescending { it.scheduledDate }
            database.newsDao().insertCalendar(allCal)
        }
    }

    private fun fetchAndStoreIpo() {
        CoroutineScope(Dispatchers.IO).launch {
            val ipoSource = dataSources.find { it is IpoNewsSource } as? IpoNewsSource
            val ipoEvents = ipoSource?.fetchIpoEvents() ?: emptyList()
            _ipoEvents.value = ipoEvents
        }
    }

    // -----------------------------------------------------------------------
    // UI Actions
    // -----------------------------------------------------------------------

    fun refreshNews() {
        CoroutineScope(Dispatchers.IO).launch {
            fetchAndStoreNews()
        }
    }

    fun refreshCalendar() {
        CoroutineScope(Dispatchers.IO).launch {
            fetchAndStoreCalendar()
        }
    }

    fun refreshIpo() {
        CoroutineScope(Dispatchers.IO).launch {
            fetchAndStoreIpo()
        }
    }

    fun toggleFavorite(newsId: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            database.newsDao().setFavorite(newsId, !isFavorite(newsId))
        }
    }

    fun isFavorite(newsId: Long): Boolean {
        return getFavoriteFromDb(newsId) != null
    }

    fun getFavoriteFromDb(newsId: Long): NewsItem? = database.newsDao().getFavorites().firstOrNull { it.id == newsId }

    // -----------------------------------------------------------------------
    // Helper queries
    // -----------------------------------------------------------------------

    fun getUnreadHighImpactCount(): Int = database.newsDao().getUnreadHighImpactCount()
    fun getLatestFromDb(limit: Int): List<NewsItem> = database.newsDao().getLatestNews(limit)
    fun getNewsSince(since: Long): List<NewsItem> = database.newsDao().getNewsSince(since)
    fun getHighImpactNews(): List<NewsItem> = database.newsDao().getHighImpactNews()
    fun getHighImpactNewsSince(since: Long): List<NewsItem> = database.newsDao().getHighImpactNewsSince(since)
    fun getFavorites(): List<NewsItem> = database.newsDao().getFavorites()
    fun getNewsCount(): Int = database.newsDao().getNewsCount()
}