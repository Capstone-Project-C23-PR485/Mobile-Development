package com.android.skinchekai

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.android.skinchekai.network.AuthPreference
import com.android.skinchekai.ui.sigin.SigInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.slide_up, 0)
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = firebaseAuth.currentUser
        val token: String? = user?.getIdToken(false)?.result?.token
        val authPreference = AuthPreference(this)
        token?.let { authPreference.setValue("key", it) }
        Log.d("Token", token.toString())

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
    override fun onStart() {
        super.onStart()
        // Initialize firebase user
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        // Check condition
        if (firebaseUser == null) {
            // When user already sign in redirect to profile activity
            startActivity(
                Intent(
                    this@MainActivity,
                    SigInActivity::class.java
                ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            )
            finish()
        }
    }
}