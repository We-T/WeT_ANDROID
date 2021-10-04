package com.wetour.we_t.ui.myPage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.wetour.we_t.R
import com.wetour.we_t.data.MyFamilyData

class MyFamilyAdapter(private val context: Context): RecyclerView.Adapter<MyFamilyAdapter.MyFamilyViewHolder>() {

    var datas = arrayListOf<MyFamilyData>()

    inner class MyFamilyViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        val parentsImage = itemview.findViewById<ImageView>(R.id.item_family_image)
        val parentsName = itemview.findViewById<TextView>(R.id.item_family_name)
        val radioButton = itemview.findViewById<RadioButton>(R.id.item_family_btn_radio)

        fun bind(myFamilyData: MyFamilyData) {
            Glide.with(itemView).load(myFamilyData.image).transform(CircleCrop()).into(parentsImage)
            parentsName.text = myFamilyData.name
            if (myFamilyData.isSelected) {
                radioButton.isVisible = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyFamilyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_faily, parent, false)
        return MyFamilyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyFamilyViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }


}
