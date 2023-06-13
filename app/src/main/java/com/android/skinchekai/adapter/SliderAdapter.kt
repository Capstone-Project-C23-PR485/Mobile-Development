package com.android.skinchekai.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.skinchekai.R
import com.android.skinchekai.response.AnalysisResultItemDetail
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val imageList: List<AnalysisResultItemDetail>) : SliderViewAdapter<SliderAdapter.SliderViewHolder>() {
    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
        val item = imageList[position]
        Glide.with(holder.itemView)
            .asBitmap()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
            .load(item.picture)
            .into(holder.imageView)
        holder.tv_desAnal.text = item.category
    }

    class SliderViewHolder(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {
        var imageView: ImageView = itemView!!.findViewById(R.id.img_slide_analisys)
        var tv_desAnal: TextView = itemView!!.findViewById(R.id.tv_desc_analysys)
    }
    override fun getCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        val inflate: View = LayoutInflater.from(parent!!.context).inflate(R.layout.item_image_result, null)
        return SliderViewHolder(inflate)
    }
}