package com.android.skinchekai.ui.myskin

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.skinchekai.R
import com.android.skinchekai.adapter.ProductAdapter
import com.android.skinchekai.adapter.ProductRecomendationAdapter
import com.android.skinchekai.adapter.SliderAdapter
import com.android.skinchekai.data.imageSlider
import com.android.skinchekai.databinding.ActivityDetailSkinBinding
import com.android.skinchekai.databinding.BottomSheetDialogLayoutBinding
import com.android.skinchekai.response.DataItemRecomendation
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
    private var showSnackbar = true
    private var skinType = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSkinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        showSnackbarIfRequired()
        val idLog = intent.getIntExtra("id",0).toString()
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        Handler().postDelayed({
            getAnalisysResult(idLog)
            detailViewModel.isLoading.observe(this){
                if (it){
                    progressDialog.cancel()
                }
            }
        }, 7000)

        binding.backButton.setOnClickListener {
            onBackPressed()
            finish()
        }

    }

    private fun getAnalisysResult(idLod:String) {
        detailViewModel.getDetailLog(idLod)
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
            showSnackbarIfRequired()
        }
        bottomSheetDialogLayoutBinding.btnGetRekomendasi.setOnClickListener {
            detailViewModel.getProductRecomendation(skinType)
            detailViewModel.productRecomendation.observe(this){
                showProductRecomendation(it)
            }
            detailViewModel.isSuccess.observe(this){isSucces ->
                if (isSucces){
                    bottomSheetDialog.dismiss()
                    showSnackbar = false
                }else{
                    bottomSheetDialog.dismiss()
                    Toast.makeText(this, "Gagal mendapatkan product", Toast.LENGTH_SHORT).show()
                    showSnackbar = true
                }
            }
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

    private fun showProductRecomendation(productRecomendation: List<DataItemRecomendation>?) {
        binding.rvProductRecomendation.layoutManager = LinearLayoutManager(this)
        val adapter = productRecomendation?.let { ProductRecomendationAdapter(it) }
        binding.rvProductRecomendation.adapter = adapter

    }



    private fun getDataForItem(selectedItem: String): Any {
        return when (selectedItem) {
            "Kering" -> {
                bottomSheetDialogLayoutBinding.tvSkinDesc.text = getString(R.string.skin_dry)
                skinType = "dry"
            }
            "Berminyak" -> {
                bottomSheetDialogLayoutBinding.tvSkinDesc.text = getString(R.string.skin_greasy)
                skinType = "oily"
            }
            "Sensitif" -> {
                bottomSheetDialogLayoutBinding.tvSkinDesc.text = getString(R.string.skin_sensitive)
                skinType = "sensitive"
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
        }
    }

}

