package com.san.archapp.notification

import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.san.archapp.BuildConfig

@RequiresApi(Build.VERSION_CODES.N)
data class AppNotificationData(
    val channelId: String = "Notification",
    val channelName: String = BuildConfig.APPLICATION_ID.substringAfterLast("."),
    val description: String = "Description",
    val importance: Int = NotificationManager.IMPORTANCE_DEFAULT, //
)