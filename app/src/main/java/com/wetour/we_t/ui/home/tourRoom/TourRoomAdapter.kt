package com.wetour.we_t.ui.home.tourRoom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.wetour.we_t.R
import com.wetour.we_t.data.TourRoomData

class TourRoomAdapter(private val context: Context): RecyclerView.Adapter<TourRoomViewHolder>() {

    var datas = mutableListOf<TourRoomData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourRoomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home_tour_room, parent, false)
        return TourRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: TourRoomViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class TourRoomViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

    val img1 = itemview.findViewById<ImageView>(R.id.item_home_tour_img_first)
    val img2 = itemview.findViewById<ImageView>(R.id.item_home_tour_img_second)
    val name = itemview.findViewById<TextView>(R.id.item_home_tour_name)
    val dDay = itemview.findViewById<TextView>(R.id.item_home_tour_dDay)
    val date = itemview.findViewById<TextView>(R.id.item_home_tour_date)

    fun bind(tourRoomData: TourRoomData) {
        Glide.with(itemView).load(tourRoomData.profile1).transform(CircleCrop()).into(img1)
        Glide.with(itemView).load(tourRoomData.profile2).transform(CircleCrop()).into(img2)
        name.text = tourRoomData.roomName
        dDay.text = tourRoomData.dDay
        date.text = tourRoomData.date
    }
}