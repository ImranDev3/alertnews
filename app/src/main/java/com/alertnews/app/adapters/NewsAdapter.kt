package com.alertnews.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.alertnews.app.R
import com.alertnews.app.data.model.NewsItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var items = emptyList<NewsItem>()
    private var onItemClick: ((NewsItem) -> Unit)? = null
    private var onFavoriteToggle: ((NewsItem) -> Unit)? = null

    fun setItems(newItems: List<NewsItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    fun setOnItemClick(listener: (NewsItem) -> Unit) {
        onItemClick = listener
    }

    fun setOnFavoriteToggle(listener: (NewsItem) -> Unit) {
        onFavoriteToggle = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.news_title)
        private val summary: TextView = itemView.findViewById(R.id.news_summary)
        private val source: TextView = itemView.findViewById(R.id.news_source)
        private val time: TextView = itemView.findViewById(R.id.news_time)
        private val impactBadge: TextView = itemView.findViewById(R.id.news_impact)
        private val symbolTag: TextView = itemView.findViewById(R.id.news_symbol)
        private val favIcon: ImageView = itemView.findViewById(R.id.news_fav_icon)

        fun bind(news: NewsItem) {
            title.text = news.title
            summary.text = news.summary
            source.text = news.source
            time.text = formatTime(news.publishedAt)

            val impactColor = when (news.impactLevel) {
                "critical" -> android.graphics.Color.parseColor("#FF3B30")
                "high" -> android.graphics.Color.parseColor("#FF9500")
                "medium" -> android.graphics.Color.parseColor("#FFCC00")
                else -> android.graphics.Color.parseColor("#34C759")
            }
            impactBadge.setTextColor(impactColor)
            impactBadge.text = news.impactLevel.uppercase()

            if (news.symbol.isNotBlank()) {
                symbolTag.visibility = android.view.View.VISIBLE
                symbolTag.text = news.symbol
            } else {
                symbolTag.visibility = android.view.View.GONE
            }

            favIcon.setImageResource(
                if (news.isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outline
            )

            favIcon.setOnClickListener {
                news.isFavorite = !news.isFavorite
                favIcon.setImageResource(
                    if (news.isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_outline
                )
                onFavoriteToggle?.invoke(news)
            }

            itemView.setOnClickListener { onItemClick?.invoke(news) }
        }
    }

    private fun formatTime(millis: Long): String {
        val date = Date(millis)
        val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.US)
        return sdf.format(date)
    }
}