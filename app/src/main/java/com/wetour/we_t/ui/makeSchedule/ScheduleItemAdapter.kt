package com.wetour.we_t.ui.makeSchedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.data.ScheduleItemData


class ScheduleItemAdapter(private val context:Context): RecyclerView.Adapter<ScheduleItemAdapter.ScheduleItemViewHolder>() {

    var datas = arrayListOf<ScheduleItemData>()

    inner class ScheduleItemViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        val number = itemview.findViewById<TextView>(R.id.item_schedule_number)
        val distance = itemview.findViewById<TextView>(R.id.item_schedule_distance)
        val itemLocation = itemview.findViewById<TextView>(R.id.item_schedule_item_location)
        val itemDistance = itemview.findViewById<TextView>(R.id.item_schedule_item_distance)
        val itemKind = itemview.findViewById<TextView>(R.id.item_schedule_item_kind)
        val itemRunningTime = itemview.findViewById<TextView>(R.id.item_schedule_item_runningTime)
        val itemCongestion = itemview.findViewById<TextView>(R.id.item_schedule_item_congestion)
        val itemMemo = itemview.findViewById<TextView>(R.id.item_schedule_memo)

        val const = itemview.findViewById<ConstraintLayout>(R.id.item_schedule_item)

        fun bind(scheduleItemData: ScheduleItemData) {
            number.text = scheduleItemData.number.toString()
            distance.text = scheduleItemData.distance
            itemLocation.text = scheduleItemData.item_location
            itemDistance.text = scheduleItemData.item_distance
            itemKind.text = scheduleItemData.item_kind
            itemMemo.text = scheduleItemData.item_memo

            if (!scheduleItemData.item_runningTime.isNullOrBlank()){
                // 데이터 있다면
                itemRunningTime.text = scheduleItemData.item_runningTime
            }

            if (!scheduleItemData.item_congestion.isNullOrBlank()){
                // 데이터 있다면
                itemCongestion.text = scheduleItemData.item_congestion
                if (scheduleItemData.item_congestion == "여유") {
                    itemCongestion.setTextColor(ContextCompat.getColor(context, R.color.tree_green))
                } else {
                    itemCongestion.setTextColor(ContextCompat.getColor(context, R.color.reddish))
                }
            }

            if (!scheduleItemData.item_memo.isNullOrBlank()) {
                // 데이터 있다면
                itemMemo.visibility = View.VISIBLE
                const.isSelected = true
            } else {
                itemMemo.visibility = View.GONE
                const.isSelected = false

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScheduleItemViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}