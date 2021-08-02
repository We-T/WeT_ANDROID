package com.wetour.we_t.ui.home.tourContents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R

class HashTagAdpater(private val context:Context): RecyclerView.Adapter<HashTagAdpater.HashTagViewHolder>() {

    var datas = ArrayList<String>()

    inner class HashTagViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val text = itemView.findViewWithTag<TextView>(R.id.item_hashTag)
        fun bind(hashTag: String) {
            text.text = hashTag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashTagViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_hashtag, parent, false)
        return HashTagViewHolder(view)
    }

    override fun onBindViewHolder(holder: HashTagViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

