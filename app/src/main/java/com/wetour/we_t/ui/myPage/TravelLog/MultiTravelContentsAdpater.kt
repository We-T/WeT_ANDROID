package com.wetour.we_t.ui.myPage.TravelLog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.data.TravelLogData

class MultiTravelContentsAdpater(private val context: Context, private val datas: List<TravelLogData>) :
    RecyclerView.Adapter<MultiTravelContentsAdpater.TravelContentsViewHolder>() {

    inner class TravelContentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image1: ImageView = itemView.findViewById(R.id.item_travel_log_image1)
        val image2: ImageView = itemView.findViewById(R.id.item_travel_log_image2)
        val title: TextView = itemView.findViewById(R.id.item_travel_log_text_title)
        val place: TextView = itemView.findViewById(R.id.item_travel_log_text_place)
        val date: TextView = itemView.findViewById(R.id.item_travel_log_text_date)

        fun bind(datas: TravelLogData) {
            image1.setBackgroundResource(R.drawable.img_papa)
            image2.setBackgroundResource(R.drawable.img_mama)
            title.text = datas.title
            place.text = datas.place
            date.text = "${datas.start_date}~${datas.end_date}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelContentsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_travel_log, parent, false)
        return TravelContentsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: TravelContentsViewHolder, position: Int) {
        holder.bind(datas[position])
    }
}