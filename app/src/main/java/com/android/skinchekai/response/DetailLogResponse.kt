package com.android.skinchekai.response

import com.google.gson.annotations.SerializedName

data class DetailLogResponse(

	@field:SerializedName("data")
	val data: DataLogDetail,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class DataLogDetail(

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("analysisResult")
	val analysisResult: List<AnalysisResultItemDetail>,

	@field:SerializedName("skinScore")
	val skinScore: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("picture")
	val picture: String
)

data class AnalysisResultItemDetail(

	@field:SerializedName("problemCount")
	val problemCount: Int,

	@field:SerializedName("modelResult")
	val modelResult: ModelResultDetail,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("picture")
	val picture: String
)

data class ModelResultDetail(

	@field:SerializedName("detectnedAcne")
	val detectnedAcne: String,

	@field:SerializedName("confidence")
	val confidence: Any
)
