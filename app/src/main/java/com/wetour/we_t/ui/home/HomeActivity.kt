package com.wetour.we_t.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wetour.we_t.R
import com.wetour.we_t.data.TourRoomData
import com.wetour.we_t.ui.home.tourContents.MultiRecyclerTitleAdapter
import com.wetour.we_t.ui.home.tourContents.model.RecyclerModel
import com.wetour.we_t.ui.home.tourContents.model.MultiRecyclerTitle
import com.wetour.we_t.ui.home.tourRoom.TourRoomAdapter

class HomeActivity : AppCompatActivity() {

    lateinit var tourRoomAdapter: TourRoomAdapter
    var tourRoomDatas = mutableListOf<TourRoomData>()

    var titleData = mutableListOf<MultiRecyclerTitle>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tourRoomRecyclerview = findViewById<RecyclerView>(R.id.act_home_tour_room_recyclerview)

        tourRoomAdapter = TourRoomAdapter(this)
        tourRoomRecyclerview.adapter = tourRoomAdapter
        tourRoomRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getDummyData()


        val homeRecycler = findViewById<RecyclerView>(R.id.act_home_contents_recyclerview)
        val multiRecyclerTitleAdapter = MultiRecyclerTitleAdapter(this)
        homeRecycler.adapter = multiRecyclerTitleAdapter
        homeRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        multiRecyclerTitleAdapter.datas = titleData
        multiRecyclerTitleAdapter.notifyDataSetChanged()

    }

    private fun getDummyData() {

        val list0 = arrayListOf(
            RecyclerModel(0, "제주", R.drawable.img_jeju_exam, null),
            RecyclerModel(0, "여수", R.drawable.img_yeosu_exam, null),
            RecyclerModel(0, "부산 광안리", R.drawable.img_busan_exam, null)
        )

        val list1 = arrayListOf(
            RecyclerModel(2, "대관령 양떼목장", R.drawable.img_wet_reco1, arrayListOf("풍경", "산")),
            RecyclerModel(2, "제주도 성산일출봉", R.drawable.img_wet_reco2, arrayListOf("풍경", "섬"))
        )

        val list2 = arrayListOf(
            RecyclerModel(1, "전라남도 담양", R.drawable.img_tv1, arrayListOf("산", "대나무 숲")),
            RecyclerModel(1, "경상남도 거제도", R.drawable.img_tv2, arrayListOf("풍경", "섬")),
            RecyclerModel(1, "보성 녹차밭", R.drawable.img_tv3, arrayListOf("밭", "자연"))
        )

        val list3 = arrayListOf(
            RecyclerModel(3, "태백 해바라기 축제", R.drawable.img_fes1, null)
        )
        titleData.apply {
            add(
                MultiRecyclerTitle(
                    "부모님이 이 여행지를 좋아해요!",
                    list0
                )
            )
            add(
                MultiRecyclerTitle(
                    "위트가 추천하는 관광지",
                    list1
                )
            )
            add(
                MultiRecyclerTitle(
                    "TV 속 그 여행지",
                    list2
                )
            )
            add(
                MultiRecyclerTitle(
                    "축제중인 여행지",
                    list3
                )
            )
        }

        tourRoomDatas.apply {
            add(
                TourRoomData(
                    "",
                    "",
                    "사랑하는 엄빠 여행",
                    "D-10",
                    "08.02~08.05"
                )
            )
            add(
                TourRoomData(
                    "",
                    "",
                    "사랑하는 엄빠 여행2",
                    "D-10",
                    "08.02~08.05"
                )
            )
            add(
                TourRoomData(
                    "",
                    "",
                    "사랑하는 엄빠 여행3",
                    "D-10",
                    "08.02~08.05"
                )
            )
        }

        tourRoomAdapter.datas = tourRoomDatas
        tourRoomAdapter.notifyDataSetChanged()
    }
}