package com.android.skinchekai.ui.sigin

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.android.skinchekai.MainActivity
import com.android.skinchekai.R
import com.android.skinchekai.databinding.ActivitySigInBinding

class SigInActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySigInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.orange)

        binding.btnSigin.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_sigin -> {
                val intent = Intent(this, MainActivity::class.java)
                val options = ActivityOptions.makeCustomAnimation(this, R.anim.slide_up,0)
                startActivity(intent, options.toBundle())
            }
        }
    }
}