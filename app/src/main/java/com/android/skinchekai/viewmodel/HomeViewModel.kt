package com.android.skinchekai.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.skinchekai.network.ApiConfig
import com.android.skinchekai.network.AuthPreference
import com.android.skinchekai.response.DataItem
import com.android.skinchekai.response.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(context: Context): ViewModel() {
    private val _product = MutableLiveData<List<DataItem>>()
    val product: LiveData<List<DataItem>> = _product
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    private val authPreference = AuthPreference(context)
    val key = authPreference.getValue("key")
    init {
        getProduct()
    }

    private fun getProduct() {
        _isLoading.value= true
        val client = ApiConfig.getApiService().getProduct("Bearer $key")
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>){
                if (response.isSuccessful){
                    _isLoading.value = false
                    _product.value = response.body()?.data
                    Log.d("statu","succes")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("statu"," succea")
            }
        })
    }
}