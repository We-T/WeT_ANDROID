package com.wetour.we_t

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object BindingUtils {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String?){
        Glide.with(imageView.context).load(url).transform(CenterCrop(), RoundedCorners(30)).into(imageView)
    }

    @BindingAdapter("isPlaceHeart")
    @JvmStatic
    fun ImageView.isPlaceHeart(heart : Boolean) {
        if (heart){
            setImageResource(R.drawable.ic_heart_active)
        }else{
            setImageResource(R.drawable.ic_heart_inactive)
        }
    }
}