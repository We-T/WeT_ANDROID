package com.wetour.we_t.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wetour.we_t.R
import com.wetour.we_t.ui.addSchedule.AddScheduleModel
import kotlinx.android.synthetic.main.item_select_tour_location.view.*

class SearchTourAdapter(private val context: Context, private val onClickSelectPlace: OnClickSelectPlace): RecyclerView.Adapter<SearchTourAdapter.SearchTourViewHolder>() {

    var datas = mutableListOf<AddScheduleModel>()

    inner class SearchTourViewHolder(itemView: View, onClickSelectPlace: OnClickSelectPlace): RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_add_schedule_ver_image)
        val location: TextView = itemView.findViewById(R.id.item_add_schedule_ver_text_location)
        val address: TextView = itemView.findViewById(R.id.item_add_schedule_ver_text_address)
        val category: TextView = itemView.findViewById(R.id.item_add_schedule_ver_text_category)
        val btn: TextView = itemView.findViewById(R.id.item_add_schedule_ver_btn)

        fun bind(addScheduleModel: AddScheduleModel) {
            location.text = addScheduleModel.location
            address.text = addScheduleModel.address
            category.text = addScheduleModel.category.toString()
            btn.visibility = View.GONE
            Glide.with(itemView).load(addScheduleModel.image).transform(CenterCrop(), RoundedCorners(10)).into(image)
        }

        init {
            itemView.setOnClickListener {
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
    ): SearchTourViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_add_schedule_vertical, parent, false)
        return SearchTourViewHolder(view, onClickSelectPlace)
    }

    override fun onBindViewHolder(holder: SearchTourViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}
