package com.android.skinchekai.response

import com.google.gson.annotations.SerializedName

data class ProductRecomendationResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class DataItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("dry")
	val dry: Boolean,

	@field:SerializedName("sensitive")
	val sensitive: Boolean,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("oily")
	val oily: Boolean,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rank")
	val rank: String,

	@field:SerializedName("ingredients")
	val ingredients: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("brand")
	val brand: String,

	@field:SerializedName("combination")
	val combination: Boolean,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
