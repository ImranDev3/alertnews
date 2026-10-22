package com.alertnews.app.data.source

import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.model.IpoEvent
import com.alertnews.app.data.model.NewsItem

/**
 * Every remote feed the app can pull from. Implementations must never throw —
 * they return an empty list on failure so one dead feed cannot break a refresh.
 */
interface NewsDataSource {

    val sourceName: String

    suspend fun fetchLatestNews(): List<NewsItem>

    suspend fun fetchCalendar(): List<EconomicCalendar>

    suspend fun fetchIpoEvents(): List<IpoEvent>
}
