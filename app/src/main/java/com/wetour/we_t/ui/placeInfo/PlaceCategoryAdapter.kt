package com.wetour.we_t.ui.placeInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.data.PlaceInfoCategoryData
import com.wetour.we_t.databinding.ItemPlaceCategoryBinding

class PlaceCategoryAdapter(
    private val context: Context,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<PlaceCategoryAdapter.PlaceCategoryViewHolder>() {

    var datas = arrayListOf(
        PlaceInfoCategoryData("관광지", true),
        PlaceInfoCategoryData("문화시설", false),
        PlaceInfoCategoryData("축제·공연·행사", false),
        PlaceInfoCategoryData("쇼핑", false),
        PlaceInfoCategoryData("레포츠", false),
        PlaceInfoCategoryData("음식점", false),
        PlaceInfoCategoryData("숙박", false),
        PlaceInfoCategoryData("여행코스", false)
    )

    inner class PlaceCategoryViewHolder(
        val binding: ItemPlaceCategoryBinding,
        onClickListener: OnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(category: PlaceInfoCategoryData) {
            binding.item = category.category
            binding.itemPlaceCategoryText.isSelected = category.selected
        }

        init {
            binding.root.setOnClickListener {
//                onClickListener.clickCategory(absoluteAdapterPosition)
                binding.itemPlaceCategoryText.isSelected = !binding.itemPlaceCategoryText.isSelected
            }
        }
    }

    interface OnClickListener {
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