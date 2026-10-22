package com.alertnews.app.data.model

data class ForecastEvent(
    val id: String,
    val title: String,
    val actual: String,
    val forecast: String,
    val previous: String,
    val date: String,
    val status: String,
    val description: String
) {
    val isBetterThanExpected: Boolean
        get() = try {
            val actualVal = actual.toDoubleOrNull()
            val forecastVal = forecast.toDoubleOrNull()
            actualVal != null && forecastVal != null && actualVal > forecastVal
        } catch (e: Exception) { false }

    val isWorseThanExpected: Boolean
        get() = try {
            val actualVal = actual.toDoubleOrNull()
            val forecastVal = forecast.toDoubleOrNull()
            actualVal != null && forecastVal != null && actualVal < forecastVal
        } catch (e: Exception) { false }
}