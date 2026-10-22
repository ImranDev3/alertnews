package com.alertnews.app.data.model

import java.io.Serializable

data class IpoEvent(
    val companyName: String,
    val symbol: String,
    val exchange: String,
    val priceMin: Double? = null,
    val priceMax: Double? = null,
    val shares: String = "—",
    val date: String = "TBA",
    val status: String = STATUS_UPCOMING,
    val logoUrl: String = ""
) : Serializable {

    val priceRange: String get() = when {
        priceMin != null && priceMax != null -> "$${"%.2f".format(priceMin)} - $${"".format(priceMax)}"
        priceMin != null -> "$${"%.2f".format(priceMin)}"
        else -> "TBA"
    }

    val statusLabel: String
        get() = when (status) {
            STATUS_LISTED -> "LISTED"
            STATUS_NEW -> "NEW FILING"
            else -> "UPCOMING"
        }

    companion object {
        const val STATUS_UPCOMING = "upcoming"
        const val STATUS_LISTED = "listed"
        const val STATUS_NEW = "new"
    }
}