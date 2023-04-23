package com.san.archapp.ui.examples.service

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.san.archapp.App
import com.san.archapp.R
import com.san.archapp.databinding.FragmentServiceBinding
import com.san.archapp.utils.showToast


class ServiceFragment() : Fragment() {

    private lateinit var binding: FragmentServiceBinding

    private var bServiceConnection: BindServiceConnection? = null

    private lateinit var a: String

    constructor(a:Int) : this() {
        this.a = a.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.startBgService.setOnClickListener {
            requireContext().startService(Intent(context, BackgroundService::class.java))
        }

        binding.stopBgService.setOnClickListener {
            requireContext().stopService(Intent(context, BackgroundService::class.java))
        }


        binding.startFgService.setOnClickListener {
            requireContext().startService(Intent(context, ForegroundService::class.java))
        }

        binding.stopFgService.setOnClickListener {
            requireContext().stopService(Intent(context, ForegroundService::class.java))
        }


        binding.startBdService.setOnClickListener {
            val intent = Intent().apply {
                setClass(requireContext(), BoundService::class.java)
            }
            bServiceConnection = BindServiceConnection(BoundService())
            bServiceConnection?.let { it1 ->
                requireContext().bindService(
                    intent,
                    it1,
                    AppCompatActivity.BIND_AUTO_CREATE
                )
            }
        }

        binding.stopBdService.setOnClickListener {
            bServiceConnection?.let { it1 ->
                requireContext().unbindService(it1)
            }
        }
    }


    class BindServiceConnection(
        private var bService: BoundService?
    ) : ServiceConnection {

        override fun onServiceConnected(
            className: ComponentName,
            iBinder: IBinder
        ) {
            // cast the IBinder and get MyService instance
            val binder = iBinder as BoundService.LocalBinder
            bService = binder.service
            App.app.showToast("Service Connected")

        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            bService = null
            App.app.showToast("Service DisConnected")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bServiceConnection?.let { it1 ->
            requireContext().unbindService(it1)
        }
    }
}