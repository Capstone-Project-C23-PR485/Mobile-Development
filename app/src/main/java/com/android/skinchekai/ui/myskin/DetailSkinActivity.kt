package com.android.skinchekai.ui.myskin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager2.widget.ViewPager2
import com.android.skinchekai.R
import com.android.skinchekai.adapter.ImageResultAdapter
import com.android.skinchekai.databinding.ActivityDetailSkinBinding
import com.android.skinchekai.databinding.BottomSheetDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class DetailSkinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSkinBinding
    private lateinit var bottomSheetDialogLayoutBinding: BottomSheetDialogLayoutBinding
    private lateinit var imageResultAdapter: ImageResultAdapter
    private lateinit var spinnerKulit: Spinner
    private lateinit var spinnerJerawat: Spinner
    private lateinit var btnGetRecomendation: AppCompatButton
    private val imagesList = listOf(
        R.drawable.ic_place_holder,
        R.drawable.ic_place_holder,
        R.drawable.ic_place_holder
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSkinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        //initializing the adapter
        imageResultAdapter = ImageResultAdapter(imagesList)
//        binding.viewPager.adapter = imageResultAdapter
        binding.btnShowForm.setOnClickListener {
            showFormDialog()
        }

    }

    private fun showFormDialog() {
        val inflater = LayoutInflater.from(this)
        bottomSheetDialogLayoutBinding = BottomSheetDialogLayoutBinding.inflate(inflater)
        val bottomSheetDialog = BottomSheetDialog(this, R.style.RoundedBottomSheetDialogStyle)
        bottomSheetDialog.setContentView(bottomSheetDialogLayoutBinding.root)

        bottomSheetDialog.show()
    }

//    private fun setUpViewPager() {
//
//        binding.viewPager.adapter = imageResultAdapter
//
//        //set the orientation of the viewpager using ViewPager2.orientation
//        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//        //select any page you want as your starting page
//        val currentPageIndex = 1
//        binding.viewPager.currentItem = currentPageIndex
//
//        // registering for page change callback
//        binding.viewPager.registerOnPageChangeCallback(
//            object : ViewPager2.OnPageChangeCallback() {
//
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//
//                    //update the image number textview
//                   // binding.imageNumberTV.text = "${position + 1} / 4"
//                }
//            }
//        )
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//        // unregistering the onPageChangedCallback
//        binding.viewPager.unregisterOnPageChangeCallback(
//            object : ViewPager2.OnPageChangeCallback() {}
//        )
//    }
}