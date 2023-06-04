package com.android.skinchekai.network

import com.android.skinchekai.response.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("products")
    fun getProduct(
        @Header("Authorization") authorization: String
    ): Call<ProductResponse>
}