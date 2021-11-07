package com.wetour.we_t.ui.myPage.TravelLog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R

class TravelLogAdapter(private val context: Context): RecyclerView.Adapter<TravelLogAdapter.TravelLogViewHolder>() {

    var datas = mutableListOf<MultiTravelLog>()

    inner class TravelLogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.layout_multi_recycler_title)
        val recyclerContents = itemView.findViewById<RecyclerView>(R.id.layout_multi_recycler_recyclerview)

        fun bind(multiTravelLog: MultiTravelLog) {
            title.text = multiTravelLog.title

            val contentsAdpater = MultiTravelContentsAdpater(context, multiTravelLog.contents)
            recyclerContents.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerContents.adapter = contentsAdpater
            contentsAdpater.notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelLogViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_multi_recyclerview, parent, false)
        return TravelLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: TravelLogViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}