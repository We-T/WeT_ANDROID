package com.wetour.we_t.ui.addSchedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wetour.we_t.R

class AddScheduleHorizontalAdapter(private val context: Context) :
    RecyclerView.Adapter<AddScheduleHorizontalAdapter.HorizontalViewHolder>() {

    var datas = mutableListOf<AddScheduleModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_add_schedule_horizontal, parent, false)
        return HorizontalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: HorizontalViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_add_schedule_hor_image)
        val location: TextView = itemView.findViewById(R.id.item_add_schedule_hor_location)
        val address: TextView = itemView.findViewById(R.id.item_add_schedule_hor_address)

        fun bind(addScheduleModel: AddScheduleModel) {
            location.text = addScheduleModel.location
            address.text = addScheduleModel.address
            Glide.with(itemView).load(addScheduleModel.image).into(image)
        }
    }


}