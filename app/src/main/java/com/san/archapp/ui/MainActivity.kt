package com.san.archapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.san.archapp.R
import com.san.archapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navController by lazy { this.findNavController(R.id.nav_host_fragment) }

    private val appBarConfiguration by lazy { AppBarConfiguration(navController.graph) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)
        binding.navViewBottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.exampleFragment, R.id.settingsFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun showBottomNav() {
        binding.navViewBottomNav.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        binding.navViewBottomNav.visibility = View.GONE
    }
}