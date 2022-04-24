package com.san.archapp.ui.examples.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.san.archapp.utils.getRawUri

class BackgroundService : Service() {

    private val player: MediaPlayer? by lazy {
        MediaPlayer.create(
            this,
            Settings.System.DEFAULT_RINGTONE_URI ?: getRawUri(this, "sample_audio.mp3")
        )
    }
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate() {
        super.onCreate()
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, AppNotification.getNotification(this))
        }*/
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // ringtone in android device

        // providing the boolean
        // value as true to play
        // the audio on loop
        player?.isLooping = true

        // starting the process
        player?.start()
        // returns the status
        // of the program
        return START_STICKY
       // return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show()
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}