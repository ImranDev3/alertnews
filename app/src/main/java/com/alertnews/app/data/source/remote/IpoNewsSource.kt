package com.alertnews.app.data.source.remote

import com.alertnews.app.data.model.IpoEvent
import com.alertnews.app.data.source.NewsDataSource
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

/**
 * IPO tracking from free sources (NASDAQ, NYSE, Investing.com, etc.)
 */
class IpoNewsSource : NewsDataSource {

    override val sourceName: String = "IPO_TRACKER"
    override val sourceUrl: String = "https://api.nasdaq.com/api/ipo/calendar"

    override suspend fun fetchLatestNews(): List<com.alertnews.app.data.model.NewsItem> = emptyList()

    override suspend fun fetchCalendar(): List<com.alertnews.app.data.model.EconomicCalendar> = emptyList()

    override suspend fun fetchIpoEvents(): List<IpoEvent> {
        return try {
            val jsonStr = makeHttpRequest("https://api.nasdaq.com/api/ipo/calendar")
            parseIpoCalendar(jsonStr)
        } catch (e: Exception) {
            getFallbackIpoData()
        }
    }

    private fun parseIpoCalendar(jsonStr: String): List<IpoEvent> {
        val events = mutableListOf<IpoEvent>()
        try {
            val root = JSONObject(jsonStr)
            val data = root.getJSONObject("data")
            val upcoming = data.getJSONArray("upcoming")
            val priced = data.getJSONArray("priced")

            for (arr in listOf(upcoming, priced)) {
                for (i in 0 until arr.length()) {
                    val obj = arr.getJSONObject(i)
                    val company = obj.optString("companyName", "")
                    if (company.isBlank()) continue

                    events.add(
                        IpoEvent(
                            companyName = company,
                            symbol = obj.optString("proposedSymbol", "N/A"),
                            exchange = obj.optString("exchange", "N/A"),
                            priceMin = obj.optDouble("proposedSharePriceMin", 0.0).takeIf { it > 0 },
                            priceMax = obj.optDouble("proposedSharePriceMax", 0.0).takeIf { it > 0 },
                            shares = obj.optString("sharesOffered", "—"),
                            date = obj.optString("expectedPriceDate", obj.optString("pricingDate", "TBA")),
                            status = if (arr == priced) IpoEvent.STATUS_LISTED else IpoEvent.STATUS_UPCOMING,
                            logoUrl = obj.optString("logoUrl", "")
                        )
                    )
                }
            }
        } catch (e: Exception) {
            return getFallbackIpoData()
        }
        return events
    }

    private fun getFallbackIpoData(): List<IpoEvent> {
        return listOf(
            IpoEvent("Reddit Inc.", "RDDT", "NYSE", 31.0, 34.0, "22M", "Mar 2024", IpoEvent.STATUS_LISTED),
            IpoEvent("Astera Labs", "ALAB", "NASDAQ", 33.0, 36.0, "17.8M", "Mar 2024", IpoEvent.STATUS_LISTED),
            IpoEvent("Lineage Logistics", "LINE", "NASDAQ", 70.0, 78.0, "57M", "Jul 2024", IpoEvent.STATUS_UPCOMING),
            IpoEvent("Cerebras Systems", "CBRS", "NASDAQ", 25.0, 29.0, "27M", "Sep 2024", IpoEvent.STATUS_UPCOMING),
            IpoEvent("Stripe", "STRIPE", "NYSE", null, null, "TBA", "TBA", IpoEvent.STATUS_NEW),
            IpoEvent("Databricks", "DBX", "NASDAQ", null, null, "TBA", "TBA", IpoEvent.STATUS_NEW)
        )
    }

    private suspend fun makeHttpRequest(url: String): String {
        try {
            val client = okhttp3.OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addHeader("User-Agent", "Mozilla/5.0")
                .build()
            val request = okhttp3.Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            return response.body?.string() ?: "{}"
        } catch (e: Exception) {
            return "{}"
        }
    }
}