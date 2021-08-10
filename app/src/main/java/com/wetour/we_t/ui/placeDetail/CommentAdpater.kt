package com.wetour.we_t.ui.placeDetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.wetour.we_t.R
import com.wetour.we_t.data.CommentData

class CommentAdpater(private val context: Context): RecyclerView.Adapter<CommentAdpater.CommentViewHolder>() {

    var datas = mutableListOf<CommentData>()

    inner class CommentViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        val commentImage = itemview.findViewById<ImageView>(R.id.item_comment_image)
        val commentUserName = itemview.findViewById<TextView>(R.id.item_comment_text_userName)
        val commentDate = itemview.findViewById<TextView>(R.id.item_comment_text_date)
        val commentRating = itemview.findViewById<RatingBar>(R.id.item_comment_rating)
        val commentText = itemview.findViewById<TextView>(R.id.item_comment_text_comment)

        fun bind(commentData: CommentData) {
            Glide.with(context).load(commentData.image).transform(CircleCrop()).into(commentImage)
            commentUserName.text = commentData.name
            commentDate.text = commentData.date
            commentRating.rating = commentData.rating.toFloat()
            commentText.text = commentData.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}