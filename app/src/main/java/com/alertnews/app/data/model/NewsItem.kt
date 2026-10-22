package com.alertnews.app.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * A single news headline/alert. One row per article.
 */
@Entity(
    tableName = "news_items",
    indices = [
        Index(value = ["publishedAt"], name = "idx_published_at"),
        Index(value = ["source"], name = "idx_source"),
        Index(value = ["impactLevel"], name = "idx_impact_level")
    ]
)
data class NewsItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String = "",
    @SerializedName("source") val source: String,
    @SerializedName("url") val url: String = "",
    @SerializedName("imageUrl") val imageUrl: String = "",
    @SerializedName("publishedAt") val publishedAt: Long,
    @SerializedName("impactLevel") val impactLevel: String = IMPACT_MEDIUM,
    @SerializedName("symbol") val symbol: String = "",
    @SerializedName("currency") val currency: String = "",
    @SerializedName("category") val category: String = "general",
    @SerializedName("eventType") val eventType: String = TYPE_NEWS,
    @SerializedName("topics") val topics: String = "",
    @SerializedName("isRead") val isRead: Boolean = false,
    @SerializedName("isFavorite") val isFavorite: Boolean = false
) : Serializable {

    val topicList: List<String>
        get() = if (topics.isBlank()) emptyList() else topics.split(",").map { it.trim() }

    companion object {
        const val IMPACT_CRITICAL = "critical"
        const val IMPACT_HIGH = "high"
        const val IMPACT_MEDIUM = "medium"
        const val IMPACT_LOW = "low"

        const val TYPE_NEWS = "news"
        const val TYPE_IPO = "ipo"
        const val TYPE_CALENDAR = "calendar"
    }
}