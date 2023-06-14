package com.android.skinchekai.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.skinchekai.R
import com.android.skinchekai.databinding.BottomSheetDialogLayoutDetailProductBinding
import com.android.skinchekai.databinding.BottomSheetDialogLayoutProfileBinding
import com.android.skinchekai.databinding.ItemProductBinding
import com.android.skinchekai.response.DataItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProductAdapter(private val context: Context,private val listProductItem: List<DataItem>):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemProductBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listProductItem.size

    @SuppressLint("SetTextI18n")
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

        holder.binding.itemProduct.setOnClickListener {
            val inflater = LayoutInflater.from(context)
            val bottomSheetBinding = BottomSheetDialogLayoutDetailProductBinding.inflate(inflater)
            val bottomSheetDialog = BottomSheetDialog(context, R.style.RoundedBottomSheetDialogStyle)
            bottomSheetDialog.setContentView(bottomSheetBinding.root)

            Glide.with(holder.itemView.context)
                .load(list.image)
                .into(bottomSheetBinding.imgDetail)

            bottomSheetBinding.tvPrice.text = list.price
            bottomSheetBinding.tvNameProduct.text = list.name
            bottomSheetBinding.tvBrandProduct.text = "By ${list.brand}"
            bottomSheetBinding.tvProductIngridientDetail.text = "Ingredients : \n${list.ingredients}"

            bottomSheetDialog.show()
        }

    }
}
