package com.alertnews.app.data.model

import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Forex market session times in UTC.
 * Sessions: Sydney, Tokyo, London, New York, Los Angeles
 */
data class Session(
    val name: String,
    val start: String,
    val end: String,
    val color: String
) {
    companion object {
        val SESSIONS = listOf(
            Session("Sydney", "22:00", "07:00", "#1abc9c"),
            Session("Tokyo", "00:00", "09:00", "#3498db"),
            Session("London", "07:00", "16:00", "#9b59b6"),
            Session("New York", "12:00", "21:00", "#e74c3c"),
            Session("Los Angeles", "16:00", "01:00", "#f39c12")
        )

        fun getCurrentSessions(now: Long = System.currentTimeMillis()): List<Session> {
            val sdf = java.text.SimpleDateFormat("HH:mm", Locale.US)
            sdf.timeZone = java.util.TimeZone.getTimeZone("UTC")
            val currentTime = sdf.format(java.util.Date(now))
            return SESSIONS.filter { session ->
                val nowMinutes = currentTime.substring(0, 2).toInt() * 60 + currentTime.substring(3, 5).toInt()
                val startMinutes = session.start.substring(0, 2).toInt() * 60 + session.start.substring(3, 5).toInt()
                val endMinutes = session.end.substring(0, 2).toInt() * 60 + session.end.substring(3, 5).toInt()

                if (startMinutes <= endMinutes) {
                    nowMinutes in (startMinutes..endMinutes)
                } else {
                    nowMinutes >= startMinutes || nowMinutes <= endMinutes
                }
            }
        }
    }
}