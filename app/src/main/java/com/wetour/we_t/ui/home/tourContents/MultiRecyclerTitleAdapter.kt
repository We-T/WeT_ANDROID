package com.wetour.we_t.ui.home.tourContents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.ui.home.tourContents.model.MultiRecyclerTitle

class MultiRecyclerTitleAdapter(private val context: Context): RecyclerView.Adapter<MultiRecyclerTitleAdapter.MultiReryclerViewHolder>() {

    var datas = mutableListOf<MultiRecyclerTitle>()

    inner class MultiReryclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.layout_multi_recycler_title)
        val recyclerContents = itemView.findViewById<RecyclerView>(R.id.layout_multi_recycler_recyclerview)

        fun bind(multiRecyclerTitle: MultiRecyclerTitle) {
            title.text = multiRecyclerTitle.title

            val contentsAdpater = MultiRecyclerContentsAdapter(multiRecyclerTitle.contents)
            recyclerContents.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerContents.adapter = contentsAdpater
            contentsAdpater.notifyDataSetChanged()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiReryclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_multi_recyclerview, parent, false)
        return MultiReryclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: MultiReryclerViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }

}