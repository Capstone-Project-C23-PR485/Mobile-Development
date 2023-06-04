package com.android.skinchekai.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.skinchekai.network.ApiConfig
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
    val token ="eyJhbGciOiJSUzI1NiIsImtpZCI6IjJkM2E0YTllYjY0OTk0YzUxM2YyYzhlMGMwMTY1MzEzN2U5NTg3Y2EiLCJ0eXAiOiJKV1QifQ.eyJuYW1lIjoiQW5uYXMiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFjSFR0ZHdWQ2hCZldTdUJjdTJIR1Z4cDhCbTV3a25wckVKYVUwZkNHV3p6QT1zOTYtYyIsImlzcyI6Imh0dHBzOi8vc2VjdXJldG9rZW4uZ29vZ2xlLmNvbS9jYXBzdG9uZS1za2luY2hlY2thaSIsImF1ZCI6ImNhcHN0b25lLXNraW5jaGVja2FpIiwiYXV0aF90aW1lIjoxNjg1ODYyNjk2LCJ1c2VyX2lkIjoiQVpKUWhLM1lXbmYwR1NidGpnR1BCYkl0OWpLMiIsInN1YiI6IkFaSlFoSzNZV25mMEdTYnRqZ0dQQmJJdDlqSzIiLCJpYXQiOjE2ODU4NjI2OTYsImV4cCI6MTY4NTg2NjI5NiwiZW1haWwiOiJhbm5hc2FiZHVsbGFoMTFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZ29vZ2xlLmNvbSI6WyIxMDQwOTc4MjU4MjQwMjg1ODI2NDkiXSwiZW1haWwiOlsiYW5uYXNhYmR1bGxhaDExQGdtYWlsLmNvbSJdfSwic2lnbl9pbl9wcm92aWRlciI6Imdvb2dsZS5jb20ifX0.aqRmRGsNd2-9SxFqPHpfZQdLZOcWqSBu5Icu-bwazaTrGG7MbrlVNJn5nEv9GYEO2NcFP4SNGTshOQew0R1Ke8LCk5CE348v0AbViLmBaCjmM4oopGqsY9-8GyVOUxV-OjbMbJqYfLLbz1N1eFZYghCwzWkNcfyzLqdALerUtRwCPrTT3VfyWpwZ8N4oQr80ghq5a3Q7nZrn8gnF0CHmgzc3k-ZEZThV_LD9g0T7l6qYbFcOZGroKdF7T4rC6VGA3cFeNH0kWknbh6s0UnwlkK-5VSF3AUvzT6cOYTY60WJPrCV1PABk6yValsiXJwx-Nz6cE93y94KK1ENzsY2pmA"

    init {
        getProduct()
    }

    private fun getProduct() {
        _isLoading.value= true
        val client = ApiConfig.getApiService().getProduct("Bearer $token")
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