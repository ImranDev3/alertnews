package com.alertnews.app.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Locale

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
    @SerializedName("impactLevel") val impactLevel: String,
    @SerializedName("actual") val actual: String = "",
    @SerializedName("forecast") val forecast: String = "",
    @SerializedName("previous") val previous: String = "",
    @SerializedName("symbol") val symbol: String = ""
) {
    val displayDate: String get() = if (date.isBlank()) "—" else date
    val displayTime: String get() = if (time.isBlank()) "—" else time

    fun minutesUntil(now: Long = System.currentTimeMillis()): Long {
        val parsed = parseTimestamp(date, time)
        if (parsed == 0L) return 0L
        return (parsed - now) / 60_000L
    }

    fun parseTimestamp(dateStr: String, timeStr: String): Long {
        val normalisedTime = when {
            timeStr.isBlank() -> "00:00"
            timeStr.length >= 5 -> "${timeStr.take(2)}:${timeStr.substring(3, 5)}"
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
            } catch (e: Exception) { }
        }
        return 0L
    }
}