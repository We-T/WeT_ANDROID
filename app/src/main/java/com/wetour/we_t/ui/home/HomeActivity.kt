package com.wetour.we_t.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wetour.we_t.R
import com.wetour.we_t.data.TourRoomData
import com.wetour.we_t.ui.home.tourContents.MultiRecyclerTitleAdapter
import com.wetour.we_t.data.MultiRecyclerData
import com.wetour.we_t.ui.home.tourContents.model.MultiRecyclerTitle
import com.wetour.we_t.ui.home.tourRoom.TourRoomAdapter
import com.wetour.we_t.ui.makeTour.MakeTourActivity
import com.wetour.we_t.ui.myPage.MyPageActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() , View.OnClickListener{

    lateinit var tourRoomAdapter: TourRoomAdapter
    var tourRoomDatas = mutableListOf<TourRoomData>()

    var titleData = mutableListOf<MultiRecyclerTitle>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setRv()
    }

    private fun setRv() {
        tourRoomAdapter = TourRoomAdapter(this)
        act_home_tour_room_recyclerview.adapter = tourRoomAdapter
        act_home_tour_room_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getDummyData()


        val multiRecyclerTitleAdapter = MultiRecyclerTitleAdapter(this)
        act_home_contents_recyclerview.adapter = multiRecyclerTitleAdapter
        act_home_contents_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        multiRecyclerTitleAdapter.datas = titleData
        multiRecyclerTitleAdapter.notifyDataSetChanged()
    }

    private fun getDummyData() {

//        val list0 = arrayListOf(
//            RecyclerModel(0, "제주", R.drawable.img_jeju_exam, null),
//            RecyclerModel(0, "여수", R.drawable.img_yeosu_exam, null),
//            RecyclerModel(0, "부산 광안리", R.drawable.img_busan_exam, null)
//        )

        val list1 = arrayListOf(
            MultiRecyclerData(2, "대관령 양떼목장", R.drawable.img_wet_reco1, arrayListOf("풍경", "산")),
            MultiRecyclerData(2, "제주도 성산일출봉", R.drawable.img_wet_reco2, arrayListOf("풍경", "섬"))
        )

        val list2 = arrayListOf(
            MultiRecyclerData(1, "전라남도 담양", R.drawable.img_tv1, arrayListOf("산", "대나무 숲")),
            MultiRecyclerData(1, "경상남도 거제도", R.drawable.img_tv2, arrayListOf("풍경", "섬")),
            MultiRecyclerData(1, "보성 녹차밭", R.drawable.img_tv3, arrayListOf("밭", "자연"))
        )

        val list3 = arrayListOf(
            MultiRecyclerData(3, "태백 해바라기 축제", R.drawable.img_fes1, null)
        )
        titleData.apply {
            add(
                MultiRecyclerTitle(
                    "부모님이 이 여행지를 좋아해요!",
                    arrayListOf(
                        MultiRecyclerData(0, "제주", R.drawable.img_jeju_exam, null),
                        MultiRecyclerData(0, "여수", R.drawable.img_yeosu_exam, null),
                        MultiRecyclerData(0, "부산 광안리", R.drawable.img_busan_exam, null)
                    )

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

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_home_btn_make_tour -> {
                val intent = Intent(this, MakeTourActivity::class.java)
                startActivity(intent)
            }

            R.id.act_home_btn_mypage -> {
                val intent = Intent(this, MyPageActivity::class.java)
                startActivity(intent)
            }

            R.id.act_home_btn_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }
    }
}