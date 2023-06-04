package com.android.skinchekai.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.skinchekai.R
import com.android.skinchekai.databinding.ItemLogSkinBinding
import com.android.skinchekai.databinding.ItemWawasanHarianBinding
import com.android.skinchekai.response.LogSkinResponse
import com.android.skinchekai.ui.myskin.DetailSkinActivity

class LogSkinAdapter(private val context: Context, private val data: ArrayList<LogSkinResponse>) :
    RecyclerView.Adapter<LogSkinAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLogSkinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvTitleResult.text = item.title
        holder.binding.itemLog.setOnClickListener {
            val intent = Intent(context, DetailSkinActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(var binding: ItemLogSkinBinding) : RecyclerView.ViewHolder(binding.root)
}