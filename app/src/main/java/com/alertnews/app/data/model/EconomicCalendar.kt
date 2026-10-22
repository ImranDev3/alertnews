package com.alertnews.app.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * A Forex-Factory style economic calendar entry (NFP, CPI, FOMC, ...).
 */
@Entity(
    tableName = "calendar_events",
    indices = [
        Index(value = ["scheduledDate"], name = "idx_scheduled_date"),
        Index(value = ["impactLevel"], name = "idx_calendar_impact")
    ]
)
data class EconomicCalendar(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("eventName") val eventName: String,
    @SerializedName("impactLevel") val impactLevel: String = "low",
    @SerializedName("actual") val actual: String = "",
    @SerializedName("forecast") val forecast: String = "",
    @SerializedName("previous") val previous: String = "",
    @SerializedName("symbol") val symbol: String = ""
) {

    val displayDate: String get() = if (date.isBlank()) "—" else date
    val displayTime: String get() = if (time.isBlank()) "—" else time

    /**
     * Minutes until the event, negative once it has passed. Used to drive the
     * "upcoming / live / passed" badge in the calendar tab.
     */
    fun minutesUntil(now: Long = System.currentTimeMillis()): Long {
        val parsed = parseTimestamp(date, time)
        if (parsed == 0L) return 0L
        return (parsed - now) / 60_000L
    }

    fun isPassed(now: Long = System.currentTimeMillis()): Boolean = minutesUntil(now) < 0

    fun isUpcoming(now: Long = System.currentTimeMillis()): Boolean {
        val minutes = minutesUntil(now)
        return minutes >= 0 && minutes <= 60
    }

    private fun parseTimestamp(dateStr: String, timeStr: String): Long {
        if (dateStr.isBlank()) return 0L
        val normalisedTime = when {
            timeStr.contains("T") -> timeStr.substringAfter("T").take(5)
            timeStr.contains(":") -> timeStr.take(5)
            else -> "00:00"
        }
        val patterns = listOf(
            "MMM dd, yyyy HH:mm",
            "MMM d, yyyy HH:mm",
            "yyyy-MM-dd HH:mm",
            "dd MMM yyyy HH:mm"
        )
        for (pattern in patterns) {
            try {
                val parser = SimpleDateFormat(pattern, Locale.US)
                parser.isLenient = false
                return parser.parse("$dateStr $normalisedTime")?.time ?: 0L
            } catch (_: Exception) {
                // Try the next pattern.
            }
        }
        return 0L
    }
}