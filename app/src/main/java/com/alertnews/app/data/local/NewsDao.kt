package com.alertnews.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.model.NewsItem

@Dao
interface NewsDao {
    // News
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<NewsItem>)

    @Query("SELECT * FROM news_items ORDER BY publishedAt DESC LIMIT :limit")
    suspend fun getLatestNews(limit: Int): List<NewsItem>

    @Query("SELECT * FROM news_items WHERE publishedAt > :since ORDER BY publishedAt DESC")
    suspend fun getNewsSince(since: Long): List<NewsItem>

    @Query("SELECT * FROM news_items WHERE symbol = :symbol ORDER BY publishedAt DESC")
    suspend fun getNewsBySymbol(symbol: String): List<NewsItem>

    @Query("SELECT * FROM news_items WHERE impactLevel IN ('critical', 'high') ORDER BY publishedAt DESC")
    suspend fun getHighImpactNews(): List<NewsItem>

    @Query("UPDATE news_items SET isRead = 1 WHERE id = :id")
    suspend fun markAsRead(id: Long)

    @Query("UPDATE news_items SET isFavorite = :favorite WHERE id = :id")
    suspend fun setFavorite(id: Long, favorite: Boolean)

    @Query("SELECT * FROM news_items WHERE isFavorite = 1 ORDER BY publishedAt DESC")
    suspend fun getFavorites(): List<NewsItem>

    @Query("SELECT COUNT(*) FROM news_items WHERE isRead = 0 AND impactLevel IN ('critical', 'high')")
    suspend fun getUnreadHighImpactCount(): Int

    @Query("DELETE FROM news_items WHERE publishedAt < :before")
    suspend fun deleteOldNews(before: Long)

    // Calendar
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalendar(events: List<EconomicCalendar>)

    @Query("SELECT * FROM calendar_events ORDER BY scheduledDate ASC LIMIT :limit")
    suspend fun getUpcomingCalendar(limit: Int): List<EconomicCalendar>

    @Query("SELECT * FROM calendar_events WHERE impactLevel IN ('critical', 'high') ORDER BY scheduledDate ASC")
    suspend fun getHighImpactCalendar(): List<EconomicCalendar>

    @Query("DELETE FROM calendar_events")
    suspend fun clearCalendar()

    @Query("SELECT COUNT(*) FROM news_items")
    suspend fun getNewsCount(): Int
}