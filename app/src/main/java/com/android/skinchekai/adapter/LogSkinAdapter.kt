package com.android.skinchekai.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.skinchekai.databinding.ItemLogSkinBinding
import com.android.skinchekai.response.LogDataItem
import com.android.skinchekai.ui.myskin.DetailSkinActivity
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale

class LogSkinAdapter(private val context: Context, private val listLogItem: List<LogDataItem>) :
    RecyclerView.Adapter<LogSkinAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLogSkinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listLogItem[position]
        if (item.skinScore >= 90){
            holder.binding.tvTitleResult.text = "Luar biasa"
        }
        holder.binding.tvScore.text = item.skinScore.toString()
        Glide.with(context)
            .load(item.picture)
            .into(holder.binding.imgLog)

        val inputDate = item.createdAt
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val dayFormat = SimpleDateFormat("EEE, dd", Locale.getDefault())
        val mounthFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())

        try {
            val date = inputFormat.parse(inputDate)
            val day = dayFormat.format(date!!)
            val mounth = mounthFormat.format(date)

            holder.binding.tvDay.text = day
            holder.binding.tvMounth.text = mounth

        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.binding.itemLog.setOnClickListener {
            val intent = Intent(context, DetailSkinActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listLogItem.size
    }

    class ViewHolder(var binding: ItemLogSkinBinding) : RecyclerView.ViewHolder(binding.root)
}