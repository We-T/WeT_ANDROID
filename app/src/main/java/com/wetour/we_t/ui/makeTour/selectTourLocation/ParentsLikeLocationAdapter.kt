package com.wetour.we_t.ui.makeTour.selectTourLocation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wetour.we_t.R
import com.wetour.we_t.data.PlaceData

class ParentsLikeLocationAdapter(private val context: Context, private val onClickListener: OnClickListener): RecyclerView.Adapter<ParentsLikeLocationAdapter.ParentsLikeLocationViewHolder>() {

    var datas = mutableListOf<PlaceData>()

    inner class ParentsLikeLocationViewHolder(itemview: View, onClickListener: OnClickListener): RecyclerView.ViewHolder(itemview){
        val image: ImageView = itemView.findViewById(R.id.item_place_img)
        val place: TextView = itemView.findViewById(R.id.item_place_text)

        fun bind(placeData: PlaceData) {
            Glide.with(itemView).load(placeData.image).transform(CenterCrop(), RoundedCorners(30)).into(image)
            place.text = placeData.name
            place.setTextColor(ContextCompat.getColor(context, R.color.white))
        }

        init {
            itemview.setOnClickListener {
                onClickListener.onClickParentsLikePlace(adapterPosition)
            }
        }
    }

    interface OnClickListener{
        fun onClickParentsLikePlace(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ParentsLikeLocationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_place, parent, false)
        return ParentsLikeLocationViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: ParentsLikeLocationViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}