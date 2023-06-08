package com.android.skinchekai.ui.camera

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.android.skinchekai.MainActivity
import com.android.skinchekai.databinding.ActivityCameraResultBinding
import com.android.skinchekai.viewmodel.UploadImageViewModel
import com.android.skinchekai.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CameraResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCameraResultBinding
    private var getFile: File? = null
    private val uploadViewModel by viewModels<UploadImageViewModel> {
        ViewModelFactory.getInstance(application)
    }
    companion object {
        const val CAMERA_X_RESULT = 200
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.imgBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("E dd MMMM yyyy", Locale.getDefault())
        val currentDate = dateFormat.format(calendar.time)
        binding.tvDate.text = currentDate

        //val photoFile = intent.getSerializableExtra("photoFile") as File
        val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("photoFile", File::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("photoFile")
        } as? File

        getFile = myFile
        Glide.with(this)
            .load(myFile)
            .centerCrop()
            .skipMemoryCache(true) // Skip memory caching
            .diskCacheStrategy(DiskCacheStrategy.NONE) // Disable disk caching
            .into(binding.previewImageView)

        binding.btnSendImageSelfie.setOnClickListener {
            uploadImage(getFile)
        }

        uploadViewModel.isSuccess.observe(this) {
            uploadCheck(it)
        }

        uploadViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showLoading(isLoading: Boolean?) {
        val progressDialog = ProgressDialog(this)
        if (isLoading == true){
            progressDialog.setMessage("Loading...")
            progressDialog.setCancelable(false)
            progressDialog.show()
        }else{
            progressDialog.dismiss()
        }
    }

    private fun uploadImage(filePhoto: File?) {
        //val getFile = this.getFile
        if (filePhoto != null){
            uploadViewModel.uploadImage(filePhoto)
        }else {
            Toast.makeText(this, "Insert image", Toast.LENGTH_SHORT).show()
        }
    }
    private fun uploadCheck(isSuccess: Boolean) {
        if (isSuccess) {
            Toast.makeText(this, "Success upload image", Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this, "Failed upload image", Toast.LENGTH_SHORT).show()
        }
    }
}