package com.android.skinchekai.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(

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

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("skinType")
	val skinType: String,

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

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("brand")
	val brand: String,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)
