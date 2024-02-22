package com.example

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.hw22.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }
    private fun createNotificationChannels() {
        val channel = NotificationChannel(
            resources.getString(R.string.firebase_messaging),
            "FirebaseMessaging",
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }

}