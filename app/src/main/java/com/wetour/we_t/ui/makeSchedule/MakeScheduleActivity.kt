package com.wetour.we_t.ui.makeSchedule

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.wetour.we_t.R
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class MakeScheduleActivity : AppCompatActivity(), View.OnClickListener {

    //    var radioButtonList = arrayListOf<RadioButton>()
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_schedule)

        setMap()
//        setRadioButton()
        setTab()
        setScheduleAdapter()

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

    private fun setTab() {

        tabLayout = findViewById(R.id.act_make_schedule_tab)

        for (i in 0..2) {
            tabLayout.addTab(tabLayout.newTab().setText("DAY${i+1}"))
        }


    }

    private fun setScheduleAdapter() {
        viewPager = findViewById(R.id.act_make_schedule_viewpager)

        viewPager.adapter =
            ScheduleAdapter(
                supportFragmentManager,
                3
            )

        viewPager.offscreenPageLimit = 2
        viewPager.currentItem = 0

        viewPager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                tabLayout
            )
        )

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_make_schedule_btn_back -> finish()
        }
    }

}