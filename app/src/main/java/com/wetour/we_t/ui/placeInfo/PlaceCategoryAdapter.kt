package com.wetour.we_t.ui.placeInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.databinding.ItemPlaceCategoryBinding

class PlaceCategoryAdapter(private val context: Context, private val onClickListener: OnClickListener):RecyclerView.Adapter<PlaceCategoryAdapter.PlaceCategoryViewHolder>() {

    var datas = arrayListOf("관광지", "문화시설", "축제·공연·행사", "쇼핑", "관광지", "문화시설", "축제·공연·행사", "쇼핑")

    inner class PlaceCategoryViewHolder(val binding: ItemPlaceCategoryBinding, onClickListener: OnClickListener): RecyclerView.ViewHolder(binding.root) {

        fun onBind(category: String) {
            binding.item = category
            binding.itemPlaceCategoryText.isSelected = false
        }

        init {
            binding.root.setOnClickListener {
//                onClickListener.clickCategory(absoluteAdapterPosition)
                binding.itemPlaceCategoryText.isSelected = !binding.itemPlaceCategoryText.isSelected
            }
        }
    }

    interface OnClickListener{
        fun clickCategory(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceCategoryViewHolder {
        val binding = ItemPlaceCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return PlaceCategoryViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: PlaceCategoryViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}