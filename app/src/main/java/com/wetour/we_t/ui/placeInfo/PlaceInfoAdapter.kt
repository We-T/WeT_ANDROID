package com.wetour.we_t.ui.placeInfo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.data.PlaceInfoData
import com.wetour.we_t.databinding.ItemPlaceInfoBinding
import com.wetour.we_t.ui.home.tourContents.HashTagAdpater

class PlaceInfoAdapter(private val context: Context):RecyclerView.Adapter<PlaceInfoAdapter.PlaceInfoViewHolder>() {

    var datas = mutableListOf<PlaceInfoData>()

    inner class PlaceInfoViewHolder(private val binding: ItemPlaceInfoBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(datas: PlaceInfoData){
            binding.item = datas

            val hashTagAdpater = HashTagAdpater(context)
            binding.itemPlaceInfoHashtagRecyclerview.adapter = hashTagAdpater
            binding.itemPlaceInfoHashtagRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hashTagAdpater.setHashTag(datas.hashTag)
        }
        init {
            binding.itemPlaceInfoHeart.setOnClickListener {
                if (binding.item!!.heart){
                    binding.itemPlaceInfoHeart.setImageResource(R.drawable.ic_heart_inactive)
                }else{
                    binding.itemPlaceInfoHeart.setImageResource(R.drawable.ic_heart_active)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceInfoViewHolder {
        val binding = ItemPlaceInfoBinding.inflate(LayoutInflater.from(context), parent, false)
        return PlaceInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaceInfoViewHolder, position: Int) {
        holder.onBind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}