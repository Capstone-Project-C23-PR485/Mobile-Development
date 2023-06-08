package com.android.skinchekai.response

import com.google.gson.annotations.SerializedName

data class UploadImageResponse(
	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class Data(
	@field:SerializedName("logId")
	val logId: Int
)
