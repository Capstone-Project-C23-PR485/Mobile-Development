package com.android.skinchekai.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.skinchekai.R
import com.android.skinchekai.databinding.ItemWawasanHarianBinding
import com.android.skinchekai.response.WawasanResponse
import kotlin.random.Random

class WawasanAdapter(private val context: Context,private val data: ArrayList<WawasanResponse>) :
    RecyclerView.Adapter<WawasanAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWawasanHarianBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val colors = holder.itemView.context.resources.getIntArray(R.array.random_colors)
        val colorsText = holder.itemView.context.resources.getIntArray(R.array.random_colors_text)
        val currentIndex = position % colors.size

        holder.binding.tvTitleWawasan.setTextColor(colorsText[currentIndex])
        val backgroundDrawable = ContextCompat.getDrawable(context, R.drawable.custom_card)
        backgroundDrawable?.let {
            it.mutate()
            it.setTint(colors[currentIndex])
            holder.itemView.background = it
        }

        val backgroundDrawableInfo = ContextCompat.getDrawable(context, R.drawable.custom_card_info)
        backgroundDrawableInfo?.let {
            it.mutate()
            it.setTint(colorsText[currentIndex])
            holder.binding.cvInfo.background= it
        }

        holder.binding.tvTitleWawasan.text = item.title
        holder.binding.tvDescWawasan.text = item.description
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(var binding: ItemWawasanHarianBinding) : RecyclerView.ViewHolder(binding.root)
}