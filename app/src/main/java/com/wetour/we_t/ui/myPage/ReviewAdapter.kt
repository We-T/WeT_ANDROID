package com.wetour.we_t.ui.myPage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.data.CommentData

class ReviewAdapter(private val context: Context): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    var datas = mutableListOf<CommentData>()
    inner class ReviewViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        val image = itemview.findViewById<ImageView>(R.id.item_comment_image)
        val name = itemview.findViewById<TextView>(R.id.item_comment_text_userName)
        val date = itemview.findViewById<TextView>(R.id.item_comment_text_date)
        val comment = itemview.findViewById<TextView>(R.id.item_comment_text_comment)
        val rating = itemview.findViewById<RatingBar>(R.id.item_comment_rating)

        fun bind(data: CommentData) {

            image.setBackgroundResource(R.drawable.dasole)
            name.text = data.name
            date.text = data.date
            comment.text = data.comment
            rating.rating = data.rating.toFloat()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}