package com.android.skinchekai.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.skinchekai.network.ApiConfig
import com.android.skinchekai.network.AuthPreference
import com.android.skinchekai.response.LogDataItem
import com.android.skinchekai.response.LogResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MySkinViewModel(context: Context): ViewModel() {
    private val _log = MutableLiveData<List<LogDataItem>>()
    val log: LiveData<List<LogDataItem>> = _log

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val authPreference = AuthPreference(context)
    val key = authPreference.getValue("key")

    init {
        getLog()
    }

    private fun getLog() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getLogs("Bearer $key")
        client.enqueue(object : Callback<LogResponse> {
            override fun onResponse(call: Call<LogResponse>, response: Response<LogResponse>){
                if (response.isSuccessful){
                    _isLoading.value = false
                    _log.value = response.body()?.data
                    Log.d("status","succes")
                }
            }

            override fun onFailure(call: Call<LogResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("status"," failed")
            }
        })
    }
}