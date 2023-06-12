package com.android.skinchekai.network

import com.android.skinchekai.data.UserUpdate
import com.android.skinchekai.response.LogResponse
import com.android.skinchekai.response.ProductResponse
import com.android.skinchekai.response.ProfileResponse
import com.android.skinchekai.response.UploadImageResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @GET("products?productType=all")
    fun getProduct(
        @Header("Authorization") authorization: String
    ): Call<ProductResponse>
    @Multipart
    @POST("/machine-learning/request-analyses")
    fun uploadImage(
        @Header("Authorization") authorization: String,
        @Part file: MultipartBody.Part
    ): Call<UploadImageResponse>

    @GET("profile")
    fun getProfile(
        @Header("Authorization") authorization: String
    ): Call<ProfileResponse>

    @GET("logs")
    fun getLogs(
        @Header("Authorization") authorization: String
    ):Call<LogResponse>

    @PATCH("profile")
    fun updateProfile(
        @Header("Authorization") authorization: String,
        @Body userUpdate: UserUpdate
    ): Call<ProfileResponse>
}