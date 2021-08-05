package com.wetour.we_t.ui.home.tourContents

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.databinding.ItemHashtagBinding

class HashTagAdpater(private val context:Context): RecyclerView.Adapter<HashTagAdpater.HashTagViewHolder>() {

    var datas = ArrayList<String>()

    inner class HashTagViewHolder(val binding: ItemHashtagBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(hashTagItem: String) {
            binding.hashTagItem = hashTagItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HashTagViewHolder {
        val binding = ItemHashtagBinding.inflate(LayoutInflater.from(context), parent, false)
        return HashTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HashTagViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

