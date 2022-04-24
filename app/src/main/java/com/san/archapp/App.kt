package com.san.archapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.san.archapp.notification.AppNotification
import com.san.archapp.notification.AppNotificationData
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
open class App : Application() {


    companion object {
        lateinit var app: App
    }

    override fun onCreate() {
        super.onCreate()

        app = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            AppNotification.getChannel()?.contains(
                NotificationChannel(
                    "1",
                    "Foreground Service", NotificationManager.IMPORTANCE_DEFAULT
                )
            )?.let {
                if (!it) {
                    AppNotification.createChannel(
                        AppNotification.buildChannel(
                            AppNotificationData(
                                "1",
                                "Foreground Service",
                                "Foreground Service test",
                            )
                        )
                    )
                }
            }

        }
    }
}