package com.alertnews.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(private val context: Context) {

    private const val CHANNEL_ID = "alertnews_high_importance"
    private const val CHANNEL_NAME = "High Impact News"
    private const val CHANNEL_DESCRIPTION = "Real-time alerts for high-impact forex & index events"

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                android.app.NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = CHANNEL_DESCRIPTION
                enableLights(true)
                lightColor = android.graphics.Color.RED
                enableVibration(true)
            }
            val manager = context.getSystemService(NotificationManager::class.java)!!
            manager.createNotificationChannel(channel)
        }
    }

    fun showNotification(
        title: String,
        message: String,
        importLevel: String = "normal"
    ) {
        val intent = android.content.Intent(context, MainActivity::class.java).apply {
            setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK or android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        val pending = android.content.PendingIntent.getActivity(
            context,
            0,
            intent,
            android.content.PendingIntent.FLAG_UPDATE_CURRENT or android.content.PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_notification)
            setContentTitle(title)
            setContentText(message)
            setStyle(androidx.core.app.NotificationCompat.BigTextStyle().bigText(message))
            setPriority(getNotificationPriority(importLevel))
            setContentIntent(pending)
            setAutoCancel(true)
        }

        val manager = context.getSystemService(NotificationManager::class.java)!!
        manager.notify(hashCode(), builder.build())
    }

    private fun getNotificationPriority(importLevel: String): Int = when (importLevel) {
        "high" -> android.app.NotificationManager.IMPORTANCE_HIGH
        "critical" -> android.app.NotificationManager.IMPORTANCE_MAX
        else -> android.app.NotificationManager.IMPORTANCE_DEFAULT
    }