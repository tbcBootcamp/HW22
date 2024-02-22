package com.example.hw22.presentation.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.hw22.R
import com.example.hw22.presentation.screen.activity.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        showNotification(
            message.data["title"] ?: "",
            message.data["desc"] ?: "",
            message.data["id"] ?: "0"
        )
    }

    private fun showNotification(title: String, body: String, id: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("id", id)
        }
        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val channelId = resources.getString(R.string.firebase_messaging)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setSmallIcon(R.drawable.ic_heart)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
//            .build()

//        if (ContextCompat.checkSelfPermission(
//
//                applicationContext,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//
//            NotificationManagerCompat.from(applicationContext)
//                .notify(R.string.firebase_messaging_id, notificationBuilder)
//        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }



    override fun onNewToken(token: String) {
    }
}