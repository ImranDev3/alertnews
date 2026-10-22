package com.alertnews.app.data.source.remote

import com.alertnews.app.data.model.NewsItem
import com.alertnews.app.data.model.EconomicCalendar
import com.alertnews.app.data.source.NewsDataSource
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * RSS news aggregator for Forex Factory, Investing.com, Bloomberg, Reuters,
 * Trading Economics, and custom RSS feeds.
 *
 * Uses RSS2JSON / direct RSS parsing (no API keys required for common feeds).
 */
class RssNewsSource : NewsDataSource {

    private val rssFeeds = listOf(
        "https://www.investing.com/rss/news_5000011",
        "https://www.investing.com/rss/news_5000012",
        "https://www.investing.com/rss/news_4768",
        "https://www.investing.com/rss/news_4767",
        "https://www.investing.com/rss/news_5000010",
        "https://www.dailyfx.com/feeds/front_page.php",
        "https://feeds.reuters.com/reuters/markets",
        "https://feeds.reuters.com/reuters/commodities",
        "https://www.marketwatch.com/rss.asp?link=btp_classic"
    )

    // Using RSS2JSON free proxy to convert RSS to JSON
    private val rssProxy = "https://api.rss2json.com/v1/api.json"

    override val sourceName: String = "RSS_AGGREGATOR"
    override val sourceUrl: String = "multiple_rss_feeds"

    override suspend fun fetchLatestNews(): List<NewsItem> {
        val allNews = mutableListOf<NewsItem>()
        val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US)

        for (feed in rssFeeds) {
            try {
                val jsonStr = fetchRssAsJson(feed)
                val json = JSONObject(jsonStr)
                if (json.optString("status") != "ok") continue

                val items = json.getJSONArray("items")
                for (i in 0 until items.length()) {
                    val item = items.getJSONObject(i)
                    val title = item.optString("title", "")
                    val pubDate = item.optString("pubDate", "")
                    val url = item.optString("link", item.optString("url", ""))
                    val description = item.optString("description", "")

                    if (title.isBlank()) continue

                    val publishedAt = try {
                        dateFormat.parse(pubDate)?.time ?: System.currentTimeMillis()
                    } catch (e: Exception) {
                        System.currentTimeMillis()
                    }

                    val impact = detectImpact(title, description)
                    val symbol = detectSymbol(title)

                    allNews.add(
                        NewsItem(
                            title = title,
                            summary = stripHtml(description),
                            source = extractSourceName(feed),
                            url = url,
                            publishedAt = publishedAt,
                            impactLevel = impact,
                            symbol = symbol,
                            category = categorizeNews(title),
                            eventType = "news"
                        )
                    )
                }
            } catch (e: Exception) {
                // Skip failed feed
            }
        }

        return allNews.sortedByDescending { it.publishedAt }.distinctBy { it.title.hashCode() }
    }

    override suspend fun fetchCalendar(): List<EconomicCalendar> = emptyList()

    override suspend fun fetchIpoEvents(): List<com.alertnews.app.data.model.IpoEvent> =
        emptyList()

    private suspend fun fetchRssAsJson(feedUrl: String): String {
        val fullUrl = "$rssProxy?rss_url=$feedUrl"
        return makeHttpRequest(fullUrl)
    }

    private suspend fun makeHttpRequest(url: String): String {
        try {
            val client = okhttp3.OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()

            val request = okhttp3.Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            return response.body?.string() ?: "{}"
        } catch (e: Exception) {
            return "{}"
        }
    }

    private fun detectImpact(title: String, description: String): String {
        val combined = (title + " " + description).lowercase()
        return when {
            combined.contains("fed") || combined.contains("fomc") ||
                    combined.contains("nfp") || combined.contains("non-farm") ||
                    combined.contains("cpi") || combined.contains("inflation") ->
                NewsItem.IMPACT_CRITICAL
            combined.contains("gdp") || combined.contains("employment") ||
                    combined.contains("interest rate") || combined.contains("ecb") ||
                    combined.contains("boe") || combined.contains("boj") ->
                NewsItem.IMPACT_HIGH
            combined.contains("trump") || combined.contains("war") ||
                    combined.contains("conflict") || combined.contains("crisis") ->
                NewsItem.IMPACT_HIGH
            else -> NewsItem.IMPACT_MEDIUM
        }
    }

    private fun detectSymbol(title: String): String {
        val combined = title.uppercase()
        val pairs = listOf(
            "EUR/USD", "GBP/USD", "USD/JPY", "AUD/USD", "USD/CHF",
            "USDCAD", "NZD/USD", "EUR/JPY", "GBP/JPY", "XAU/USD",
            "WTI", "BRENT", "NASDAQ", "SPX", "DOW"
        )
        for (pair in pairs) {
            if (combined.contains(pair)) return pair
        }
        return ""
    }

    private fun categorizeNews(title: String): String {
        val combined = title.lowercase()
        return when {
            combined.contains("fed") || combined.contains("fomc") -> "monetary_policy"
            combined.contains("cpi") || combined.contains("inflation") -> "inflation"
            combined.contains("gdp") || combined.contains("economic") -> "economic"
            combined.contains("ip") || combined.contains("manufacturing") -> "industrial"
            combined.contains("employment") || combined.contains("jobs") -> "employment"
            combined.contains("trump") -> "politics"
            combined.contains("war") || combined.contains("conflict") -> "geopolitical"
            combined.contains("gold") -> "commodities"
            else -> "general"
        }
    }

    private fun extractSourceName(feedUrl: String): String {
        return when {
            feedUrl.contains("investing.com") -> "Investing.com"
            feedUrl.contains("dailyfx.com") -> "DailyFX"
            feedUrl.contains("reuters") -> "Reuters"
            feedUrl.contains("marketwatch") -> "MarketWatch"
            feedUrl.contains("tradingview") -> "TradingView"
            else -> "RSS Feed"
        }
    }

    private fun stripHtml(html: String): String {
        return android.text.Html.fromHtml(html, android.text.Html.FROM_HTML_MODE_LEGACY)
            .toString().trim()
    }
}