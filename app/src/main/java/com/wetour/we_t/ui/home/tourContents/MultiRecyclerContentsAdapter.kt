package com.wetour.we_t.ui.home.tourContents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.ui.home.tourContents.model.RecyclerModel

class MultiRecyclerContentsAdapter(private val list: List<RecyclerModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // getItemViewType의 리턴값 Int가 viewType으로 넘어온다.
    // viewType으로 넘어오는 값에 따라 viewHolder를 알맞게 처리해주면 된다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            RecyclerModel.BASIC_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
                BasicViewHolder(view)
            }
            RecyclerModel.HASHTAG_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_with_hashtag, parent, false)
                HashTagViewHolder(view)
            }
            RecyclerModel.HASHTAG_WIDE_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_with_hashtag_wide, parent, false)
                HashTagWideViewHolder(view)
            }
            RecyclerModel.FESTIVAL_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_festival, parent, false)
                FestivalHolder(view)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = list[position]
        when (obj.type) {
            RecyclerModel.BASIC_TYPE -> {
                (holder as BasicViewHolder).place.text = obj.place
                holder.image.setImageResource(obj.image)
            }

            RecyclerModel.HASHTAG_TYPE -> {
                (holder as HashTagViewHolder).place.text = obj.place
                holder.image.setImageResource(obj.image)
                holder.hashTagData = obj.hashTag!!
            }

            RecyclerModel.HASHTAG_WIDE_TYPE -> {
                (holder as HashTagWideViewHolder).place.text = obj.place
                holder.image.setImageResource(obj.image)
                holder.hashTagData = obj.hashTag!!
            }

            RecyclerModel.FESTIVAL_TYPE -> {
                (holder as FestivalHolder).place.text = obj.place
                holder.image.setImageResource(obj.image)
            }
        }
    }

    // 여기서 받는 position은 데이터의 index다.
    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    inner class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)
    }

    inner class HashTagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)
        var hashTagData = ArrayList<String>()
    }

    inner class HashTagWideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)
        var hashTagData = ArrayList<String>()
    }

    inner class FestivalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)
    }
}