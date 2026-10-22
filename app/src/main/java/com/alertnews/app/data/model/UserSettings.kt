package com.alertnews.app.data.model

data class UserSettings(
    val enableNotifications: Boolean = true,
    val enableMusic: Boolean = false,
    val musicUrl: String = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3",
    val musicVolume: Float = 0.5f,
    val highImpactAlert: Boolean = true,
    val topicAlerts: List<String> = emptyList(),
    val timezone: String = "UTC",
    val selectedSymbols: List<String> = emptyList(),
    val selectedCurrencies: List<String> = emptyList(),
    val showFavoritesOnly: Boolean = false,
    val autoRefreshIntervalMin: Int = 15,
    val enableDarkMode: Boolean = true,
    val showMarketSessions: Boolean = true
)