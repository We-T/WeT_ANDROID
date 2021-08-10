package com.wetour.we_t.ui.placeDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.data.PlaceDetailData

class PlaceDetailAdapter(private val context: Context): RecyclerView.Adapter<PlaceDetailAdapter.PlaceDetailViewHolder>() {

    var datas = mutableListOf<PlaceDetailData>()
    inner class PlaceDetailViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        val title = itemview.findViewById<TextView>(R.id.item_detail_info_title)
        val text = itemview.findViewById<TextView>(R.id.item_detail_info_text)

        fun bind(placeDetailData: PlaceDetailData) {
            title.text = placeDetailData.title
            text.text = placeDetailData.info
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceDetailViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_place_detail_info, parent, false)
        return PlaceDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceDetailViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}