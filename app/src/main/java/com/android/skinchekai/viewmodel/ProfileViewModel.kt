package com.android.skinchekai.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.skinchekai.network.ApiConfig
import com.android.skinchekai.network.AuthPreference
import com.android.skinchekai.response.DataProfile
import com.android.skinchekai.response.ProfileResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(context: Context): ViewModel() {
    private val _profileResponse = MutableLiveData<DataProfile>()
    val profileResponse: LiveData<DataProfile> = _profileResponse

    private val authPreference = AuthPreference(context)
    val key = authPreference.getValue("key")

    init {
        getProfile()
    }

    private fun getProfile(){
        val client = ApiConfig.getApiService().getProfile("Bearer $key")
        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                _profileResponse.value = response.body()?.dataProfile
                Log.d("Status"," Success")
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.d("Status"," Failed")
            }

        })
    }
}