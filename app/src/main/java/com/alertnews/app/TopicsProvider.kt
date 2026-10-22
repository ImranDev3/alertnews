package com.alertnews.app

data class Topic(
    val name: String,
    val keywords: List<String>
)

class TopicsProvider {
    val topics = listOf(
        Topic("Trump", listOf("trump", "donald trump", "white house", "president")),
        Topic("Fed", listOf("fed", "federal reserve", "fomc", "jerome powell", "federal open market")),
        Topic("War", listOf("war", "conflict", "invasion", "ukraine", "russia", "israel", "gaza")),
        Topic("Inflation / CPI", listOf("cpi", "inflation", "cpi m/m", "pce", "price index")),
        Topic("NFP / Jobs", listOf("nfp", "non-farm payrolls", "jobs report", "unemployment", "employment")),
        Topic("GDP", listOf("gdp", "gross domestic product", "economic growth")),
        Topic("Gold / XAU", listOf("gold", "xau", "precious metals", "bullion")),
        Topic("Oil / Crude", listOf("oil", "crude", "brent", "wti", "energy")),
        Topic("DXY / Dollar", listOf("dxy", "dollar index", "usd", "us dollar")),
        Topic("NASDAQ", listOf("nasdaq", "nq", "tech stocks", "technology")),
        Topic("SPX / S\\&P 500", listOf("spx", "s\\&p 500", "s&p500", "sp500")),
        Topic("Dow Jones", listOf("dow jones", "dji", "industrial average")),
        Topic("IPO", listOf("ipo", "initial public offering", "listing", "nasdaq listing", "nyse listing")),
        Topic("Central Banks", listOf("ecb", "boe", "boj", "rbnz", "snb", "rba", "central bank", "rate decision", "interest rate"))
    )

    fun matchTopics(title: String, summary: String): List<String> {
        val text = (title + " " + summary).lowercase()
        return topics.filter { topic ->
            topic.keywords.any { keyword -> text.contains(keyword.lowercase()) }
        }.map { it.name }
    }
}