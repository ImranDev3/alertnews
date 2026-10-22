package com.alertnews.app.data.source.remote

import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.source.NewsDataSource
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Forex Factory style economic calendar.
 *
 * Parses the Forex Factory weekly calendar JSON endpoint and normalises it into
 * EconomicCalendar entities with impact levels (high / medium / low).
 */
class FactoryCalendarSource : NewsDataSource {

    override val sourceName: String = "FOREX_FACTORY"
    override val sourceUrl: String = "https://www.forexfactory.com/calendar?week=this"

    private val baseUrl = "https://nfs.faireconomy.media/ff_calendar_thisweek.json"

    override suspend fun fetchLatestNews(): List<com.alertnews.app.data.model.NewsItem> = emptyList()

    override suspend fun fetchCalendar(): List<EconomicCalendar> {
        return try {
            val jsonStr = makeHttpRequest(baseUrl)
            parseCalendar(jsonStr)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun fetchIpoEvents(): List<com.alertnews.app.data.model.IpoEvent> = emptyList()

    private fun parseCalendar(jsonStr: String): List<EconomicCalendar> {
        val events = mutableListOf<EconomicCalendar>()
        try {
            val root = JSONObject(jsonStr)
            val arr: JSONArray = root.getJSONArray("d") ?: return emptyList()

            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val title = obj.optString("t", "")
                if (title.isBlank()) continue

                val date = obj.optString("d", "")
                val time = obj.optString("time", "")
                val country = obj.optString("c", "")
                val impact = obj.optString("i", "").lowercase()

                events.add(
                    EconomicCalendar(
                        date = date,
                        time = time,
                        currency = country,
                        eventName = title,
                        impactLevel = mapImpact(impact),
                        actual = obj.optString("a", ""),
                        forecast = obj.optString("f", ""),
                        previous = obj.optString("p", ""),
                        symbol = countryToSymbol(country)
                    )
                )
            }
        } catch (e: Exception) {
            return emptyList()
        }
        return events
    }

    private fun mapImpact(impact: String): String = when (impact) {
        "h", "high" -> EconomicCalendar::class.java.let { "high" }
        "m", "medium" -> "medium"
        "l", "low" -> "low"
        else -> "low"
    }

    private fun countryToSymbol(country: String): String = when (country) {
        "USD" -> "DXY"
        "EUR" -> "EURUSD"
        "GBP" -> "GBPUSD"
        "JPY" -> "USDJPY"
        "CHF" -> "USDCHF"
        "AUD" -> "AUDUSD"
        "CAD" -> "USDCAD"
        "NZD" -> "NZDUSD"
        "CNY" -> "USDCNH"
        else -> country
    }

    private suspend fun makeHttpRequest(url: String): String {
        try {
            val client = okhttp3.OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()
            val request = okhttp3.Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            return response.body?.string() ?: "[]"
        } catch (e: Exception) {
            return "[]"
        }
    }
}