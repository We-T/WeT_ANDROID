package com.wetour.we_t.ui.makeSchedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wetour.we_t.R
import com.wetour.we_t.data.DayBtnData
import com.wetour.we_t.data.ScheduleItemData
import com.wetour.we_t.ui.addSchedule.AddScheduleActivity
import kotlinx.android.synthetic.main.activity_make_schedule.*
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class MakeScheduleActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var scheduleItemAdapter: ScheduleItemAdapter
    lateinit var scheduleDayAdapter: ScheduleDayAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_schedule)

        setMap()
        setDayRv()
        setRv()
        setData()

    }

    private fun setMap() {
        val mapView = MapView(this)
        val marker = MapPOIItem()
        val mapViewContainer = findViewById<View>(R.id.act_make_schedule_map) as ViewGroup

        mapViewContainer.addView(mapView)

        mapView.setMapCenterPoint(
            MapPoint.mapPointWithGeoCoord(
                33.308916574785165, 126.63376954043169
            ), false
        )

        marker.itemName = "Default Marker"
        marker.tag = 0
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(33.308916574785165, 126.63376954043169)
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

        mapView.addPOIItem(marker)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_make_schedule_btn_back -> finish()
            R.id.act_make_schedule_btn_addSchedule -> {
                val intent = Intent(this, AddScheduleActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun setDayRv() {
        scheduleDayAdapter =
            ScheduleDayAdapter(this, object : ScheduleDayAdapter.OnClickDayListener {
                override fun onClickDay(position: Int) {

                    Toast.makeText(this@MakeScheduleActivity, position.toString(), Toast.LENGTH_SHORT).show()

                    for (i in 0..10) {
                        scheduleDayAdapter.days[i].selected = false
                    }

                    scheduleDayAdapter.days[position].selected = true
                    scheduleDayAdapter.notifyDataSetChanged()
                }
            })
        act_make_schedule_day_recyclerview.adapter = scheduleDayAdapter
        act_make_schedule_day_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setRv() {
        scheduleItemAdapter = ScheduleItemAdapter(this)
        act_make_schedule_frame_recyclerview.adapter = scheduleItemAdapter
        act_make_schedule_frame_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setData() {

        for (i in 0..10) {
            if (i == 0) {
                scheduleDayAdapter.days.add(DayBtnData("DAY"+(i+1).toString(), true))
            } else {
                scheduleDayAdapter.days.add(DayBtnData("DAY"+(i+1).toString(), false))
            }
        }

        scheduleDayAdapter.notifyDataSetChanged()

        scheduleItemAdapter.datas.apply {
            add(
                ScheduleItemData(
                    1,
                    "14.3km",
                    "제주 무지개 하우스",
                    "350km",
                    "숙소",
                    null,
                    null,
                    null
                )
            )
            add(
                ScheduleItemData(
                    2,
                    "17.3km",
                    "칠돈가 함덕 점",
                    "350km",
                    "음식점",
                    "30분 소요",
                    "여유",
                    null
                )
            )
            add(
                ScheduleItemData(
                    3,
                    "2.3km",
                    "성산일출봉",
                    "350km",
                    "관광지",
                    "2시간 소요",
                    "혼잡",
                    "엄마 아빠 ! 입구까지는 택시타는게 편하대~"
                )
            )
            add(
                ScheduleItemData(
                    4,
                    "",
                    "제주 무지개 하우스",
                    "350km",
                    "숙소",
                    null,
                    null,
                    null
                )
            )
        }
        scheduleItemAdapter.notifyDataSetChanged()
    }

}