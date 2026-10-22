package com.alertnews.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.alertnews.app.adapters.NewsAdapter
import com.alertnews.app.viewmodels.NewsViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import com.alertnews.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notificationHelper = NotificationHelper(this)
        notificationHelper.createNotificationChannel()

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        adapter = NewsAdapter().apply {
            setOnItemClick { news ->
                Toast.makeText(this@MainActivity, news.title, Toast.LENGTH_SHORT).show()
            }
            setOnFavoriteToggle { news ->
                viewModel.toggleFavorite(news.id!!)
            }
        }

        binding.newsRecycler.layoutManager = LinearLayoutManager(this)
        binding.newsRecycler.adapter = adapter

        viewModel.latestNews.observe(this) { items ->
            adapter.setItems(items)
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.highImpactNews.observe(this) { items ->
            if (items.isNotEmpty()) {
                val latest = items.first()
                NotificationHelper(this).showNotification(
                    "High Impact Alert",
                    latest.title,
                    latest.impactLevel
                )
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refreshNews()
        }

        binding.fabAdd.setOnClickListener {
            Toast.makeText(this, "Manual news source added", Toast.LENGTH_SHORT).show()
        }
    }
}