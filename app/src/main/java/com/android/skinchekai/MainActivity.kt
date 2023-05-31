package com.android.skinchekai

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.skinchekai.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.slide_up, 0)
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_myskin, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            // Mengubah ikon pada saat fragment aktif
            when (destination.id) {
                R.id.navigation_home -> {
                    navView.menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_active)
                    navView.menu.findItem(R.id.navigation_myskin).setIcon(R.drawable.ic_myskin_nonactive)
                    navView.menu.findItem(R.id.navigation_profile).setIcon(R.drawable.ic_profile_nonactive)
                }
                R.id.navigation_myskin -> {
                    navView.menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_nonactive)
                    navView.menu.findItem(R.id.navigation_myskin).setIcon(R.drawable.ic_myskin_active)
                    navView.menu.findItem(R.id.navigation_profile).setIcon(R.drawable.ic_profile_nonactive)
                }
                R.id.navigation_profile -> {
                    navView.menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_nonactive)
                    navView.menu.findItem(R.id.navigation_myskin).setIcon(R.drawable.ic_myskin_nonactive)
                    navView.menu.findItem(R.id.navigation_profile).setIcon(R.drawable.ic_profile_active)
                }
            }
        }
    }
}