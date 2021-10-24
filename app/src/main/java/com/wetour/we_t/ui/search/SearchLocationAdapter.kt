package com.wetour.we_t.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wetour.we_t.R
import com.wetour.we_t.data.SelectTourLocationData
import kotlinx.android.synthetic.main.item_select_tour_location.view.*

class SearchLocationAdapter(private val context: Context, private val onClickSelectPlace: OnClickSelectPlace): RecyclerView.Adapter<SearchLocationAdapter.SearchLocationViewHolder>() {

    var datas = mutableListOf<SelectTourLocationData>()

    inner class SearchLocationViewHolder(itemView: View, onClickSelectPlace: OnClickSelectPlace): RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.item_select_tour_img)
        val placeName: TextView = itemView.findViewById(R.id.item_select_tour_placeName)
        val placeSub: TextView = itemView.findViewById(R.id.item_select_tour_placeSub)
        val btn: Button = itemView.findViewById(R.id.item_select_tour_btn)

        fun bind(selectTourLocationData: SelectTourLocationData) {
            Glide.with(itemView).load(selectTourLocationData.image).circleCrop().into(image)

            placeName.text = selectTourLocationData.placeName
            if (!selectTourLocationData.placeSub.isNullOrEmpty()) {
                placeSub.visibility = View.VISIBLE
                placeSub.text = selectTourLocationData.placeSub
            } else {
                placeSub.visibility = View.GONE
            }

            btn.visibility = View.GONE
        }

        init {
            itemView.item_select_tour_btn.setOnClickListener {
                onClickSelectPlace.selectPlace(adapterPosition)
            }
        }

    }

    interface OnClickSelectPlace{
        fun selectPlace(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchLocationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_select_tour_location, parent, false)
        return SearchLocationViewHolder(view, onClickSelectPlace)
    }

    override fun onBindViewHolder(holder: SearchLocationViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}
