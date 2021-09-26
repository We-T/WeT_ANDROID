package com.wetour.we_t.ui.makeSchedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.data.DayBtnData

class ScheduleDayAdapter(private val context: Context, private val onClickDayListener: OnClickDayListener) : RecyclerView.Adapter<ScheduleDayAdapter.ScheduleDayViewHolder>() {

    var days = arrayListOf<DayBtnData>()

    inner class ScheduleDayViewHolder(itemView: View, onClickDayListener: OnClickDayListener) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.item_schedule_day)

        fun bind(days: DayBtnData) {
            day.text = days.day
            day.isSelected = days.selected
        }

        init {
            itemView.setOnClickListener {
                onClickDayListener.onClickDay(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleDayViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_schedule_day, parent, false)
        return ScheduleDayViewHolder(view, onClickDayListener)
    }

    override fun onBindViewHolder(holder: ScheduleDayViewHolder, position: Int) {
        holder.bind(days[position])
    }

    override fun getItemCount(): Int {
        return days.size
    }

    interface OnClickDayListener {
        fun onClickDay(position: Int)
    }
}