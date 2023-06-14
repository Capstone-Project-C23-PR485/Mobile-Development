package com.android.skinchekai.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.android.skinchekai.MainActivity
import com.android.skinchekai.R
import com.android.skinchekai.network.AuthPreference
import com.android.skinchekai.ui.sigin.SigInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance()


        // Initialize firebase user
        val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
        // Check condition
        if (firebaseUser == null) {
            val intent = Intent(this, SigInActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            }, 3000)
        }else {
            val intent = Intent(this, MainActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            }, 3000)
        }


    }
}