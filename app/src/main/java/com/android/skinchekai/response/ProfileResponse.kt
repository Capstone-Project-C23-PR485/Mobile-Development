package com.android.skinchekai.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(

	@field:SerializedName("data")
	val dataProfile: DataProfile,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class DataProfile(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("skinType")
	val skinType: String,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("birthDate")
	val birthDate: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("picture")
	val picture: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
