package com.android.skinchekai.adapter

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.skinchekai.databinding.ItemProductBinding
import com.android.skinchekai.response.DataItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

class ProductAdapter(private val listProductItem: List<DataItem>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listProductItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listProductItem[position]
        holder.binding.tvProductTitle.text = list.type
        holder.binding.tvProductIngridient.text = list.name
        Glide.with(holder.itemView.context)
            .asBitmap()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(50)))
            .load(list.image)
            .into(object : SimpleTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val scaledBitmap = Bitmap.createScaledBitmap(resource, 72, 75, true)
                    holder.binding.imgProduct.setImageBitmap(scaledBitmap)
                }
            })

    }
}
