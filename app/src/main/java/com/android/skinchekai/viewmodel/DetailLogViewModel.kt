package com.android.skinchekai.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.skinchekai.network.ApiConfig
import com.android.skinchekai.network.AuthPreference
import com.android.skinchekai.response.AnalysisResultItemDetail
import com.android.skinchekai.response.DataItemRecomendation
import com.android.skinchekai.response.DetailLogResponse
import com.android.skinchekai.response.LogResponse
import com.android.skinchekai.response.ProductRecomendationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLogViewModel(context: Context): ViewModel() {
    private val _analisysResult = MutableLiveData<List<AnalysisResultItemDetail>>()
    val analisysResult: LiveData<List<AnalysisResultItemDetail>> = _analisysResult

    private val _productRecomendation = MutableLiveData<List<DataItemRecomendation>>()
    val productRecomendation: LiveData<List<DataItemRecomendation>> = _productRecomendation

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val authPreference = AuthPreference(context)
    private val key = authPreference.getValue("key")


    fun getDetailLog(id:String) {
        val client = ApiConfig.getApiService().getDetailsLogs("Bearer $key",id)
        client.enqueue(object : Callback<DetailLogResponse> {
            override fun onResponse(call: Call<DetailLogResponse>, response: Response<DetailLogResponse>){
                if (response.isSuccessful){
                    val responseBody =response.body()?.data
                    _analisysResult.value = responseBody?.analysisResult
                    Log.d("statusLog",_analisysResult.toString())
                }
                Log.d("statusLog"," failed response")
            }

            override fun onFailure(call: Call<DetailLogResponse>, t: Throwable) {
                Log.d("statusLog"," failed")
            }
        })
    }
    fun getProductRecomendation(skinType:String) {
        val client = ApiConfig.getApiService().getProductRecomendation("Bearer $key",skinType)
        client.enqueue(object : Callback<ProductRecomendationResponse> {
            override fun onResponse(call: Call<ProductRecomendationResponse>, response: Response<ProductRecomendationResponse>){
                if (response.isSuccessful){
                    _productRecomendation.value = response.body()?.data
                    _isSuccess.value = true
                }
                Log.d("statusLog"," failed response")
            }

            override fun onFailure(call: Call<ProductRecomendationResponse>, t: Throwable) {
                Log.d("statusLog"," failed")
                _isSuccess.value = false
            }
        })
    }
}