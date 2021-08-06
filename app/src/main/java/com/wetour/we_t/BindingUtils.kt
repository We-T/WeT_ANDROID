package com.wetour.we_t

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtils {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String?){
        Glide.with(imageView.context).load(url)
            .into(imageView)
    }
}