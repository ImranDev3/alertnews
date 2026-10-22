package com.alertnews.app.data.model

data class MarketSession(
    val name: String,
    val start: String,
    val end: String,
    val isActive: Boolean
) {
    companion object {
        val SESSIONS = listOf(
            MarketSession("Sydney", "22:00", "07:00", false),
            MarketSession("Tokyo", "00:00", "09:00", false),
            MarketSession("London", "07:00", "16:00", false),
            MarketSession("New York", "12:00", "21:00", false),
            MarketSession("Los Angeles", "16:00", "01:00", false)
        )
    }
}