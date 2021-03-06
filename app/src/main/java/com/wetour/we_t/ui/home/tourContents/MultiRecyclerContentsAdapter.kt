package com.wetour.we_t.ui.home.tourContents

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wetour.we_t.R
import com.wetour.we_t.data.MultiRecyclerData

class MultiRecyclerContentsAdapter(private val context: Context, private val datas: List<MultiRecyclerData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // getItemViewType의 리턴값 Int가 viewType으로 넘어온다.
    // viewType으로 넘어오는 값에 따라 viewHolder를 알맞게 처리해주면 된다.
    //        const val BASIC_TYPE = 0
    //        const val HASHTAG_TYPE = 1
    //        const val HASHTAG_WIDE_TYPE = 2
    //        const val FESTIVAL_TYPE = 3
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when (viewType) {
            MultiRecyclerData.BASIC_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
                BasicViewHolder(view)
            }
            MultiRecyclerData.HASHTAG_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_with_hashtag, parent, false)
                HashTagViewHolder(view)
            }
            MultiRecyclerData.HASHTAG_WIDE_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_with_hashtag_wide, parent, false)
                HashTagWideViewHolder(view)
            }
            MultiRecyclerData.FESTIVAL_TYPE -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_place_festival, parent, false)
                FestivalHolder(view)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = datas[position]
        when (obj.type) {
            MultiRecyclerData.BASIC_TYPE -> {
                (holder as BasicViewHolder).bind(obj)
            }

            MultiRecyclerData.HASHTAG_TYPE -> {
                (holder as HashTagViewHolder).bind(obj)
                val hashTagAdpater = HashTagAdpater(context)
                holder.hashTagRecyclerView.adapter = hashTagAdpater
                holder.hashTagRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                hashTagAdpater.setHashTag(obj.hashTag)
            }

            MultiRecyclerData.HASHTAG_WIDE_TYPE -> {
                (holder as HashTagWideViewHolder).bind(obj)
            }

            MultiRecyclerData.FESTIVAL_TYPE -> {
                (holder as FestivalHolder).bind(obj)
            }
        }
    }

    // 여기서 받는 position은 데이터의 index다.
    override fun getItemViewType(position: Int): Int {
        return datas[position].type
    }

    inner class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)

        fun bind(obj: MultiRecyclerData) {
            place.text = obj.place
            Glide.with(itemView).load(obj.image).transform(CenterCrop(), RoundedCorners(30)).into(image)
        }
    }

    inner class HashTagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)
        val hashTagRecyclerView = itemView.findViewById<RecyclerView>(R.id.item_place_recyclerveiw)

        fun bind(obj: MultiRecyclerData) {
            place.text = obj.place
            Glide.with(itemView).load(Uri.parse(obj.image)).transform(CenterCrop(), RoundedCorners(30)).into(image)
        }
    }

    inner class HashTagWideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)
        var hashTagData = ArrayList<String>()

        fun bind(obj: MultiRecyclerData) {
            place.text = obj.place
            Glide.with(itemView).load(obj.image).transform(CenterCrop(), RoundedCorners(30)).into(image)
        }
    }

    inner class FestivalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)

        fun bind(obj: MultiRecyclerData) {
            place.text = obj.place
            Glide.with(itemView).load(obj.image).transform(CenterCrop(), RoundedCorners(30)).into(image)
        }
    }
}