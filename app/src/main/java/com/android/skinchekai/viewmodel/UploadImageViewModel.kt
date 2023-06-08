package com.android.skinchekai.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.skinchekai.network.ApiConfig
import com.android.skinchekai.network.AuthPreference
import com.android.skinchekai.response.Data
import com.android.skinchekai.response.UploadImageResponse
import com.android.skinchekai.ui.camera.reduceFileImage
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadImageViewModel(context: Context): ViewModel() {
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _uploadResponse = MutableLiveData<UploadImageResponse>()
    val uploadResponse: LiveData<UploadImageResponse> = _uploadResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    private val _idLog = MutableLiveData<Data>()
//    val idLog: LiveData<Data> = _idLog

    private val authPreference = AuthPreference(context)
    val key = authPreference.getValue("key")
    fun uploadImage(getFile: File) {
        val file = reduceFileImage(getFile)
        val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
        val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "image",
            file.name,
            requestImageFile
        )
        _isLoading.value = true
        val uploadImageRequest = ApiConfig.getApiService().uploadImage("Bearer $key",imageMultipart)
        uploadImageRequest.enqueue(object : Callback<UploadImageResponse> {
            override fun onResponse(
                call: Call<UploadImageResponse>,
                response: Response<UploadImageResponse>
            ) {
                if (response.isSuccessful) {
                    _uploadResponse.value = response.body()
                    val responseBody = response.body()
//                    _idLog.value = responseBody.data.logId
                    _isSuccess.value = true
                    _isLoading.value = false
                } else {
                    val errorBody = response.errorBody()?.string()
                    //Log.e(TAG, "$errorBody")
                    _isLoading.value = false
                }
            }
            override fun onFailure(call: Call<UploadImageResponse>, t: Throwable) {
                _isSuccess.value = false
                _isLoading.value = false
                //Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

}