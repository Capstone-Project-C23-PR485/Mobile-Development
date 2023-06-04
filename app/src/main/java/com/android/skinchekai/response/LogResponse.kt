package com.android.skinchekai.response

import com.google.gson.annotations.SerializedName

data class LogResponse(

	@field:SerializedName("data")
	val data: List<LogDataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class ModelResult(

	@field:SerializedName("detectnedAcne")
	val detectnedAcne: String,

	@field:SerializedName("confidence")
	val confidence: Any
)

data class LogDataItem(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("analysisResult")
	val analysisResult: List<AnalysisResultItem>,

	@field:SerializedName("skinScore")
	val skinScore: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("picture")
	val picture: String
)

data class AnalysisResultItem(

	@field:SerializedName("problemCount")
	val problemCount: Int,

	@field:SerializedName("modelResult")
	val modelResult: ModelResult,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("picture")
	val picture: String
)
