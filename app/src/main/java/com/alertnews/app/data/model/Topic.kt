package com.alertnews.app.data.model

data class Topic(
    val name: String,
    val keywords: List<String>,
    val isActive: Boolean = true,
    val color: String = "#1565C0"
) {
    companion object {
        val TOPICS = listOf(
            Topic("Trump", listOf("trump", "donald trump", "white house", "president"), color = "#FF6B6B"),
            Topic("Fed", listOf("fed", "federal reserve", "fomc", "powell", "federal open market"), color = "#4ECDC4"),
            Topic("War", listOf("war", "conflict", "invasion", "ukraine", "russia", "israel", "gaza"), color = "#FF3B30"),
            Topic("Inflation/CPI", listOf("cpi", "inflation rate", "prices", "pce deflator"), color = "#9C27B0"),
            Topic("NFP/Jobs", listOf("nfp", "non-farm payrolls", "jobs report", "unemployment", "employment"), color = "#F44336"),
            Topic("GDP", listOf("gdp", "gross domestic product", "economic growth"), color = "#2196F3"),
            Topic("Gold/XAU", listOf("gold", "xauusd", "precious metals", "bullion"), color = "#FFD700"),
            Topic("Oil/Crude", listOf("oil", "crude", "brent", "wti", "energy"), color = "#4CAF50"),
            Topic("DXY/Dollar", listOf("dxy", "dollar index", "usd", "greenback"), color = "#FF9800"),
            Topic("NASDAQ", listOf("nasdaq", "nq futures", "tech stocks", "technology"), color = "#00BCD4"),
            Topic("SPX/S&P500", listOf("spx", "s&p 500", "sp500", "es futures"), color = "#FFEB3B"),
            Topic("Dow Jones", listOf("dow jones", "dji", "dj", "industrial average"), color = "#9C27B0"),
            Topic("IPO", listOf("ipo", "initial public offering", "listing", "goes public", "market debut"), color = "#E91E63"),
            Topic("Central Banks", listOf("ecb", "boe", "boj", "rbnz", "snb", "rba", "central bank", "rate decision"), color = "#673AB7"),
            Topic("Economy", listOf("economy", "economic data", "macro", "recession"), color = "#795548"),
            Topic("Politics", listOf("politics", "election", "vote", "senate", "congress"), color = "#607D8B"),
            Topic("Crypto", listOf("crypto", "bitcoin", "btc", "ethereum", "eth"), color = "#00BCD4"),
            Topic("Market Crash", listOf("crash", "correction", "bear market", "panic", "sell-off"), color = "#F44336"),
            Topic("Bailout/Stimulus", listOf("bailout", "stimulus", "aid package", "relief"), color = "#FF5722"),
            Topic("China", listOf("china", "chinex", "beijing", "shanghai", "csi 300"), color = "#8BC34A")
        )
    }
}