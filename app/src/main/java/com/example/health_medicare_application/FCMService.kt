package com.example.health_medicare_application

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId = "notif_channel"
const val channelName="com.example.health_medicare_application"
class FCMService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.getNotification() != null) {
            generateNotification(
                remoteMessage.notification?.title.toString(),
                remoteMessage.notification?.body.toString()
            )
        }
    }

    fun generateNotification(title: String, msg: String) {

        var builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.health_medicare_app)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentTitle(title)
            .setContentText(msg)
            .setPriority(NotificationCompat.PRIORITY_HIGH).build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(101, builder)
    }
}