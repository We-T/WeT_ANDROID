package com.wetour.we_t.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.PreferenceUtil
import com.wetour.we_t.R
import com.wetour.we_t.data.MultiRecyclerData
import com.wetour.we_t.data.TourRoomData
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.network.data.MainResponse
import com.wetour.we_t.ui.home.tourContents.MultiRecyclerTitleAdapter
import com.wetour.we_t.ui.home.tourContents.model.MultiRecyclerTitle
import com.wetour.we_t.ui.home.tourRoom.TourRoomAdapter
import com.wetour.we_t.ui.makeTour.MakeTourActivity
import com.wetour.we_t.ui.myPage.FamilyListActivity
import com.wetour.we_t.ui.myPage.MyPageActivity
import com.wetour.we_t.ui.search.SearchActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var preferenceUtil: PreferenceUtil

    lateinit var tourRoomAdapter: TourRoomAdapter
    lateinit var multiRecyclerTitleAdapter: MultiRecyclerTitleAdapter
    var mode = "child"
    var tourRoomDatas = mutableListOf<TourRoomData>()

    var titleData = mutableListOf<MultiRecyclerTitle>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        preferenceUtil = PreferenceUtil(this)

        setRv()
//        getDummyData()

        getDataFromServer()

    }

    private fun setRv() {
        tourRoomAdapter = TourRoomAdapter(this)
        act_home_tour_room_recyclerview.adapter = tourRoomAdapter
        act_home_tour_room_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        multiRecyclerTitleAdapter = MultiRecyclerTitleAdapter(this)
        act_home_contents_recyclerview.adapter = multiRecyclerTitleAdapter
        act_home_contents_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun getDataFromServer() {

        val jsonData = JSONObject()
        jsonData.put("email", preferenceUtil.getString("email", "noEmail"))
        val body = JsonParser.parseString(jsonData.toString()) as JsonObject

        RequestToServer.service.requestMain(body).enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {

                if (response.isSuccessful) {
                    Log.e("HomeActivity", "onResponse ${response.body()}")

                    val res = response.body()

                    if (res != null) {
                        // init Static ------------------------------------------------------------
                        act_home_text_user.text = "${res.name}???,"
                        Glide.with(this@HomeActivity).load(res.profile).into(act_home_btn_mypage)

                        if (res.type == "1") {
                            //??????
                            if (res.num_fam.toInt() < 1) {
                                // ?????? ?????? ??????
                                act_home_text.text = "????????? ???????????? ????????? ??????????????????."
                                act_home_btn_make_tour.isVisible = false
                                act_home_btn_invite_family.isVisible = true

                            } else {
                                // ????????? ?????? ??????
                                act_home_text.text = "????????? ??????????????? ??????????????????."
                                act_home_btn_make_tour.isVisible = true
                                act_home_btn_invite_family.isVisible = false
                            }

                        } else {
                            // ??????
                            act_home_text.text = "????????? ????????? ????????????????????????."
                            act_home_btn_make_tour.visibility = View.GONE
                            act_homt_text_open_tour.text = "????????? ????????? ?????????"
                        }
                        // init Dynamic ------------------------------------------------------------

                        val wet_reco = arrayListOf<MultiRecyclerData>()
                        val tv = arrayListOf<MultiRecyclerData>()
                        val festival = arrayListOf<MultiRecyclerData>()
                        val parent_good = arrayListOf<MultiRecyclerData>()

                        // ????????? ???????????? ????????? ?????????
                        res.wet_good.forEach {
                            wet_reco.add(
                                MultiRecyclerData(2, it.title, it.firstimage, it.tag_list)
                            )
                        }

                        // tv??? ?????????
                        res.tv_tour.forEach {
                            tv.add(
                                MultiRecyclerData(1, it.addr1, it.firstimage, it.tag_list)
                            )
                        }

                        // ???????????? ?????????
                        res.festival.forEach {
                            festival.add(
                                MultiRecyclerData(3, it.title, it.firstimage, null)
                            )
                        }

                        // ?????? - ????????? ???????????? ?????? ??????
                        res.p_good_list?.forEach {
                            parent_good.add(
                                MultiRecyclerData(0, it.title, it.firstimage, null)
                            )
                        }

                        Log.e("HomeActivity", "1 ${wet_reco}")
                        Log.e("HomeActivity", "2 ${tv}")
                        Log.e("HomeActivity", "3 ${festival}")
                        Log.e("HomeActivity", "4 ${parent_good}")

                        titleData.apply {
                            add(
                                MultiRecyclerTitle(
                                    "???????????? ??? ???????????? ????????????!",
                                    parent_good
                                )
                            )
                            add(
                                MultiRecyclerTitle(
                                    "????????? ???????????? ?????????",
                                    wet_reco
                                )
                            )
                            add(
                                MultiRecyclerTitle(
                                    "TV ??? ??? ?????????",
                                    tv
                                )
                            )
                            add(
                                MultiRecyclerTitle(
                                    "???????????? ?????????",
                                    festival
                                )
                            )
                        }

                        multiRecyclerTitleAdapter.datas = titleData
                        multiRecyclerTitleAdapter.notifyDataSetChanged()

                        // ????????? ????????????--------------------------------------------------------------
                        res.trip_record_list?.forEach {
                            tourRoomAdapter.datas.add(
                                TourRoomData(
                                    profile1 = it.attend_famliy[0].profile,
                                    profile2 = it.attend_famliy[1].profile,
                                    roomName = it.trip_name,
                                    dDay = it.dDay,
                                    date = it.day
                                )
                            )
                        }

                        tourRoomAdapter.notifyDataSetChanged()

                    }

                } else {
                    Log.e("HomeActivity", "onResponse ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Log.e("HomeActivity", "onFailure ${t.message}")
            }

        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
//            R.id.act_home_btn_mypage -> {
//                val intent = Intent(this, MyPageActivity::class.java)
//                startActivity(intent)
//            }
//
//            R.id.act_home_btn_search -> {
//                val intent = Intent(this, SearchActivity::class.java)
//                intent.putExtra("from", "home")
//                startActivity(intent)
//            }
//
//            R.id.act_home_btn_invite_family -> {
//                val intent = Intent(this, FamilyListActivity::class.java)
//                startActivity(intent)
//            }
//
//            R.id.act_home_btn_make_tour -> {
//                val intent = Intent(this, MakeTourActivity::class.java)
//                startActivity(intent)
//            }
        }
    }
}