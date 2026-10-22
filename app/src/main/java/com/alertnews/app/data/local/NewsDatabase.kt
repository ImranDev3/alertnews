package com.alertnews.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.model.NewsItem

@Database(
    entities = [NewsItem::class, EconomicCalendar::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        const val DB_NAME = "alertnews_db"
    }
}
