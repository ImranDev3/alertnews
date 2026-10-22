package com.alertnews.app.util

import java.text.SimpleDateFormat
import java.util.TimeZone
import java.util.Date
import java.util.Locale

object TimezoneUtil {

    fun formatDateInTimezone(timestamp: Long, timezoneId: String = "UTC"): String {
        val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone(timezoneId)
        return sdf.format(Date(timestamp))
    }

    fun getCurrentTimeByTimezone(timezoneId: String = "UTC"): String {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone(timezoneId)
        return sdf.format(Date(System.currentTimeMillis()))
    }

    fun convertTimestampToTimezone(timestamp: Long, from: String, to: String): Long {
        val utcTime = TimeZone.getTimeZone(from)
        val targetTime = TimeZone.getTimeZone(to)
        val offset = targetTime.rawOffset - utcTime.rawOffset
        return timestamp + offset
    }

    fun formatMarketTime(session: String, baseTime: String = "00:00"): String {
        return "$session @ $baseTime UTC"
    }
}