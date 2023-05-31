package com.android.skinchekai.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.skinchekai.R
import com.android.skinchekai.databinding.ItemImageResultBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageResultAdapter(private val imageUrlList: List<Int>) :
RecyclerView.Adapter<ImageResultAdapter.ViewPagerViewHolder>() {

    class ViewPagerViewHolder(private val binding: ItemImageResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(imageUrl: Int) {
            binding.imgResult.setImageResource(imageUrl)
//            Glide.with(binding.root.context)
//                .load(imageUrl)
//                .error(R.drawable.ic_place_holder)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(binding.imgResult)
        }

    }

    override fun getItemCount(): Int = imageUrlList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ItemImageResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.setData(imageUrlList[position])
    }

}