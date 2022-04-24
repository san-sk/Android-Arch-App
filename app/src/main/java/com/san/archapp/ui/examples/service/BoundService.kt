package com.san.archapp.ui.examples.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.san.archapp.notification.AppNotification
import com.san.archapp.utils.getRawUri


class BoundService : Service() {

    private val player: MediaPlayer? by lazy {
        MediaPlayer.create(
            this,
            Settings.System.DEFAULT_RINGTONE_URI ?: getRawUri(this, "sample_audio.mp3")
        )
    }

    private val mBinder: IBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        val service: BoundService
            get() = this@BoundService
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, AppNotification.getNotification(this))
        }
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // ringtone in android device
        try {
            Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
            Log.i(this::class.simpleName, "Foreground Service: Started")

            player?.isLooping = true
            player?.start()
        } catch (e: Exception) {
            Log.i(this::class.simpleName, e.message.toString())
        }

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show()
    }


    override fun onBind(p0: Intent?): IBinder {
        return mBinder
    }
}