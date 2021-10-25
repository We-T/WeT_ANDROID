package com.wetour.we_t.ui.addSchedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wetour.we_t.R
import kotlinx.android.synthetic.main.activity_add_schedule.*

class AddScheduleActivity : AppCompatActivity() {

    lateinit var addScheduleHorizontalAdapter: AddScheduleHorizontalAdapter
    lateinit var addScheduleVerticalAdapter: AddScheduleVerticalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_schedule)

        setRv()
        loadData()

    }

    private fun setRv() {
        addScheduleHorizontalAdapter = AddScheduleHorizontalAdapter(this)
        act_add_schedule_recyclerview_horizontal.adapter = addScheduleHorizontalAdapter
        act_add_schedule_recyclerview_horizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        addScheduleVerticalAdapter = AddScheduleVerticalAdapter(this)
        act_add_schedule_recyclerview_vertical.adapter = addScheduleVerticalAdapter
        act_add_schedule_recyclerview_vertical.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun loadData() {
        val dummyH = mutableListOf<AddScheduleModel>(
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원1",
                "제주특별자치도 서귀포시",
                null
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원2",
                "제주특별자치도 서귀포시",
                null
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원3",
                "제주특별자치도 서귀포시",
                null
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                null
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                null
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                null
            )
        )

        val dummyV = mutableListOf<AddScheduleModel>(
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                "관광지"
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                "관광지"
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                "관광지"
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                "관광지"
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                "관광지"
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                "관광지"
            ),
            AddScheduleModel(
                "https://api.cdn.visitjeju.net/photomng/imgpath/201902/20/e14a8e6d-fb6d-43cc-9630-db4fbd95a50b.jpg",
                "휴애리자연생활공원",
                "제주특별자치도 서귀포시",
                "관광지"
            )
        )

        addScheduleHorizontalAdapter.datas = dummyH
        addScheduleVerticalAdapter.datas = dummyV

        addScheduleHorizontalAdapter.notifyDataSetChanged()
        addScheduleVerticalAdapter.notifyDataSetChanged()
    }
}