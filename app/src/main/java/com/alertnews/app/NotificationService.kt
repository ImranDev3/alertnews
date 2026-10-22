package com.alertnews.app

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class NotificationService : Service() {

    private var viewModel: NewsViewModel? = null

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        checkNewsAlerts()
        return START_STICKY
    }

    private fun checkNewsAlerts() {
        // Trigger notification for high impact news
        val helper = NotificationHelper(this)
        helper.createNotificationChannel()
        helper.showNotification(
            "Market Alert",
            "New high-impact news available",
            "high"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}