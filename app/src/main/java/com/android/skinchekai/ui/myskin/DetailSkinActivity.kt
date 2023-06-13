package com.android.skinchekai.ui.myskin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.android.skinchekai.R
import com.android.skinchekai.adapter.SliderAdapter
import com.android.skinchekai.data.imageSlider
import com.android.skinchekai.databinding.ActivityDetailSkinBinding
import com.android.skinchekai.databinding.BottomSheetDialogLayoutBinding
import com.android.skinchekai.viewmodel.DetailLogViewModel
import com.android.skinchekai.viewmodel.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.smarteist.autoimageslider.SliderView


class DetailSkinActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailSkinBinding
    private lateinit var bottomSheetDialogLayoutBinding: BottomSheetDialogLayoutBinding
    private val detailViewModel: DetailLogViewModel by viewModels {
        ViewModelFactory.getInstance(this.application)
    }


    lateinit var sliderAdapter: SliderAdapter
    private val list= ArrayList<imageSlider>()
    private var showSnackbar = true
    lateinit var imageUrl: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSkinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()


        list.add(
            imageSlider(
                "https://practice.geeksforgeeks.org/_next/image?url=https%3A%2F%2Fmedia.geeksforgeeks.org%2Fimg-practice%2Fbanner%2Fdata-science-live-thumbnail.png&w=1920&q=75"
            )
        )
        list.add(
            imageSlider(
                "https://practice.geeksforgeeks.org/_next/image?url=https%3A%2F%2Fmedia.geeksforgeeks.org%2Fimg-practice%2Fbanner%2Ffull-stack-node-thumbnail.png&w=1920&q=75"
            )
        )

        imageUrl = ArrayList()

        // on below line we are adding data to our image url array list.
//        imageUrl =
//            (imageUrl + "https://storage.googleapis.com/skincheckai-assets/facial-images/Leonardo-Dicaprio-Face-Closeup.jpg") as ArrayList<String>
        imageUrl =
            (imageUrl + "https://practice.geeksforgeeks.org/_next/image?url=https%3A%2F%2Fmedia.geeksforgeeks.org%2Fimg-practice%2Fbanner%2Fdata-science-live-thumbnail.png&w=1920&q=75") as ArrayList<String>
        imageUrl =
            (imageUrl + "https://practice.geeksforgeeks.org/_next/image?url=https%3A%2F%2Fmedia.geeksforgeeks.org%2Fimg-practice%2Fbanner%2Ffull-stack-node-thumbnail.png&w=1920&q=75") as ArrayList<String>


        getAnalisysResult()

        showSnackbarIfRequired()

    }

    private fun getAnalisysResult() {
        detailViewModel.getDetailLog("7")
        detailViewModel.analisysResult.observe(this){
            sliderAdapter = SliderAdapter(it)
            binding.viewPagerImage.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            binding.viewPagerImage.setSliderAdapter(sliderAdapter)
            binding.viewPagerImage.scrollTimeInSec = 3
            binding.viewPagerImage.isAutoCycle = true
            binding.viewPagerImage.startAutoCycle()
        }
    }


    private fun showFormDialog() {
        val inflater = LayoutInflater.from(this)
        bottomSheetDialogLayoutBinding = BottomSheetDialogLayoutBinding.inflate(inflater)
        val bottomSheetDialog = BottomSheetDialog(this, R.style.RoundedBottomSheetDialogStyle)
        bottomSheetDialog.setContentView(bottomSheetDialogLayoutBinding.root)
        bottomSheetDialog.setOnDismissListener {
            showSnackbar = true
            showSnackbarIfRequired()
        }
        bottomSheetDialogLayoutBinding.btnGetRekomendasi.setOnClickListener {

        }
        bottomSheetDialog.show()

        bottomSheetDialogLayoutBinding.dropdownTipeKulit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                getDataForItem(selectedItem)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Tidak ada item yang dipilih
            }
        }

    }

    private fun getDataForItem(selectedItem: String): Any {
        return when (selectedItem) {
            "dry" -> {
                bottomSheetDialogLayoutBinding.tvSkinDesc.text = getString(R.string.skin_dry)
            }
            "greasy" -> {
                bottomSheetDialogLayoutBinding.tvSkinDesc.text = getString(R.string.skin_greasy)
            }
            else -> {
                // Item tidak dikenali, kembalikan data default
                // Contoh: return dataDefault
            }
        }
    }

    private fun showSnackbar(){
        Snackbar.make(binding.parentLayout,"Get product recomendation", Snackbar.LENGTH_INDEFINITE)
            .setAction("Get"){
                showFormDialog()
            }
            .setActionTextColor(resources.getColor(R.color.orange))
            .show()
    }
    private fun showSnackbarIfRequired(){
        if (showSnackbar){
            showSnackbar()
            showSnackbar = false
        }
    }

}

