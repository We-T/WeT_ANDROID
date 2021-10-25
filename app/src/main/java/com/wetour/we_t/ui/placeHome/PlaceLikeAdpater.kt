package com.wetour.we_t.ui.placeHome

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wetour.we_t.R

class PlaceLikeAdpater(private val context:Context): RecyclerView.Adapter<PlaceLikeAdpater.PlaceLikeViewHolder>() {

    var datas = mutableListOf<String>()

    inner class PlaceLikeViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        val image = itemview.findViewById<ImageView>(R.id.item_place_img)
        fun bind(imageString: String) {
            Glide.with(context).load(imageString).transform(CenterCrop(), RoundedCorners(30)).into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceLikeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_place_only_image_92, parent, false)
        return PlaceLikeViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceLikeViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}