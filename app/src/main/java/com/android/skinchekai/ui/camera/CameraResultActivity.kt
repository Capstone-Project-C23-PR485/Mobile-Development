package com.android.skinchekai.ui.camera

import android.Manifest
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.skinchekai.MainActivity
import com.android.skinchekai.R
import com.android.skinchekai.databinding.ActivityCameraResultBinding
import java.io.File

class CameraResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    companion object {
        const val CAMERA_X_RESULT = 200
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val photoFile = intent.getSerializableExtra("picture") as File
        val isBackCamera = intent.getBooleanExtra("isBackCamera", false)
        binding.previewImageView.setImageBitmap(BitmapFactory.decodeFile(photoFile.path))

        binding.imgBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}