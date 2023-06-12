package com.android.skinchekai.data

import com.google.gson.annotations.SerializedName

data class UserUpdate(
    @SerializedName("name")
    val name: String,
    @SerializedName("birthDate")
    val birthDate: String
)
