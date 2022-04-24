package com.san.archapp.notification


import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.san.archapp.App
import com.san.archapp.R
import com.san.archapp.ui.MainActivity


@RequiresApi(Build.VERSION_CODES.O)
object AppNotification {

    private val notificationManager =
        App.app.getSystemService(NOTIFICATION_SERVICE) as NotificationManager


    fun buildChannel(notification: AppNotificationData): NotificationChannel {
        val mChannel =
            NotificationChannel(
                notification.channelId,
                notification.channelName,
                notification.importance
            )
        mChannel.description = notification.description
        return mChannel
    }

    fun buildChannelGroup(groupId: String, groupName: String): NotificationChannelGroup {
        return NotificationChannelGroup(groupId, groupName)
    }

    fun createChannel(channel: NotificationChannel, group: NotificationChannelGroup? = null) {
        notificationManager.createNotificationChannel(channel.apply { setGroup(group?.id) })
    }

    fun createChannelGroup(group: NotificationChannelGroup) {
        notificationManager.createNotificationChannelGroup(group)
    }

    fun getChannel(): MutableList<NotificationChannel>? {
        return notificationManager.notificationChannels
    }

    fun deleteChannel(context: Context, channelId: String) {
        notificationManager.deleteNotificationChannel(channelId)
    }

    fun deleteGroup(context: Context, groupId: String) {
        notificationManager.deleteNotificationChannel(groupId)
    }

    fun getNotification(context: Context): Notification {

        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0, notificationIntent, 0
        )
        return NotificationCompat.Builder(context, "1")
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }

    fun getCustomNotification(context: Context): Notification {

        val notificationLayout = RemoteViews(context.packageName, R.layout.custom_notification_view)

        val notificationIntent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0, notificationIntent, 0
        )
        return NotificationCompat.Builder(context, "1")
            .setContentTitle("Foreground Service")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setCustomContentView(notificationLayout)
            .build()
    }


}


