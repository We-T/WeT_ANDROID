package com.wetour.we_t.ui.myPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.tabs.TabLayout
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.PreferenceUtil
import com.wetour.we_t.R
import com.wetour.we_t.data.CommentData
import com.wetour.we_t.data.PlaceInfoData
import com.wetour.we_t.databinding.ActivityMyPageBinding
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.network.data.*
import com.wetour.we_t.ui.myPage.TravelLog.MultiTravelLog
import com.wetour.we_t.ui.myPage.TravelLog.TravelLogAdapter
import com.wetour.we_t.ui.placeInfo.PlaceInfoAdapter
import com.wetour.we_t.ui.placeInfo.PlaceInfoViewModel
import kotlinx.android.synthetic.main.activity_my_page.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var preferenceUtil: PreferenceUtil

    private lateinit var placeInfoAdapter: PlaceInfoAdapter
    private lateinit var travelLogAdapter: TravelLogAdapter
    private lateinit var reviewAdapter: ReviewAdapter

    private lateinit var binding: ActivityMyPageBinding
    private lateinit var placeInfoViewModel: PlaceInfoViewModel

    var likeData = mutableListOf<PlaceInfoData>()
    var logDatas = mutableListOf<MultiTravelLog>()
    var tripRecordDatas = mutableListOf<MyPageTripRecord>()
    var reviewDatas = mutableListOf<CommentData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page)
        binding.activityMyPage = this

        placeInfoViewModel = ViewModelProviders.of(this).get(PlaceInfoViewModel::class.java)

        preferenceUtil = PreferenceUtil(this)
        setRv()
        getInitDataFromServer()

        placeInfoViewModel.getPlaceData().observe(this, Observer<List<PlaceInfoData>> { item ->
            // update UI
            placeInfoAdapter.datas = likeData
            placeInfoAdapter.notifyDataSetChanged()
        })
    }

    private fun addTabListener(isParent: Boolean?) {
        act_mypage_tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        Toast.makeText(this@MyPageActivity, tab.position.toString(), Toast.LENGTH_SHORT).show()
                        // ??????, ?????? ???????????? "?????? ?????????"
                        binding.actMypageRecyclerview.adapter = placeInfoAdapter
                        requestDataFromServer("child_good")
                    }
                    1 -> {
                        Toast.makeText(this@MyPageActivity, tab.position.toString(), Toast.LENGTH_SHORT).show()
                        if (isParent == true) {
                            // ?????? ??????
                            // "?????? ??????"
                            binding.actMypageRecyclerview.adapter = travelLogAdapter
                        } else {
                            // ?????? ??????
                            // "????????? ????????? "
                            binding.actMypageRecyclerview.adapter = placeInfoAdapter
                            requestDataFromServer("parent_good")
                        }
                    }
                    2 -> {
                        Toast.makeText(this@MyPageActivity, tab.position, Toast.LENGTH_SHORT).show()
                        if (isParent == true) {
                            // ?????? ??????
                            // "??????"
                            binding.actMypageRecyclerview.adapter = reviewAdapter
                        } else {
                            // ?????? ??????
                            // "?????? ?????? "
                            binding.actMypageRecyclerview.adapter = travelLogAdapter
                        }
                    }
                    else -> Toast.makeText(this@MyPageActivity, "???????????? ?????? ??? ??????", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setRv() {
        placeInfoAdapter = PlaceInfoAdapter(this)
        binding.actMypageRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.actMypageRecyclerview.adapter = placeInfoAdapter

        travelLogAdapter = TravelLogAdapter(this)
        reviewAdapter = ReviewAdapter(this)
    }


    private fun getInitDataFromServer() {
        val jsonData = JSONObject()
        jsonData.put("email", preferenceUtil.getString("email", "noEmail"))

        val body = JsonParser.parseString(jsonData.toString()) as JsonObject
        RequestToServer.service.requestMypage(body).enqueue(object : Callback<MypageResponse> {
            override fun onResponse(
                call: Call<MypageResponse>,
                response: Response<MypageResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("MyPageActivity", "getInitDataFromServer onResponse - ${response.body()}")

                    val res = response.body()
                    act_mypage_text_userName.text = res?.name
                    // ?????? ??????
                    Glide.with(this@MyPageActivity).load(res?.profile).transform(CircleCrop())
                        .into(act_mypage_img_profile)

                    // Init Staitc Data ------------------------------------------------------------
                    if (res != null) {
                        if (res.type == 1) {
                            addTabListener(false)

                            // ?????? ??????
                            act_mypage_text_mode.text = "????????????"
                            act_mypage_btn_invite.visibility = View.VISIBLE

                            act_mypage_tab_layout.getTabAt(0)!!.text = "?????? ?????????"
                            act_mypage_tab_layout.getTabAt(1)!!.text = "????????? ?????????"
                            act_mypage_tab_layout.getTabAt(2)!!.text = "?????? ??????"

                            requestDataFromServer("child_good")
                        } else {
                            // ?????? ??????
                            addTabListener(true)

                            act_mypage_text_mode.text = "????????????"
                            act_mypage_btn_invite.visibility = View.INVISIBLE

                            act_mypage_tab_layout.getTabAt(0)!!.text = "????????? ?????????"
                            act_mypage_tab_layout.getTabAt(1)!!.text = "?????? ??????"
                            act_mypage_tab_layout.getTabAt(2)!!.text = "??????"

                            requestDataFromServer("parent_good")
                        }
                    }

                } else {
                    Log.e(
                        "MyPageActivity",
                        "getInitDataFromServer onResponse - ${response.message()}"
                    )
                }
            }

            override fun onFailure(call: Call<MypageResponse>, t: Throwable) {
                Log.e("MyPageActivity", "getInitDataFromServer onFailure - ${t.message}")
            }

        })
    }

    private fun requestDataFromServer(request: String) {
        val jsonData = JSONObject()
        jsonData.put("email", preferenceUtil.getString("email", "noEmail"))

        val body = JsonParser.parseString(jsonData.toString()) as JsonObject

        when (request) {
            "child_good" -> {
                RequestToServer.service.requestMypageMyGoodList(body)
                    .enqueue(object : Callback<MypageMyGoodListResponse> {
                        override fun onResponse(
                            call: Call<MypageMyGoodListResponse>,
                            response: Response<MypageMyGoodListResponse>
                        ) {
                            if (response.isSuccessful) {
                                Log.e(
                                    "MyPageActivity",
                                    "requestDataFromServer child_good onResponse - ${response.body()}"
                                )

                                val res = response.body()
                                likeData.clear()

                                // Init Tab Dynamic Data -------------------------------------------------------
                                res?.my_good_list?.forEach {
                                    likeData.apply {
                                        add(
                                            PlaceInfoData(
                                                image = it.firstimage,
                                                true,
                                                placeName = it.title,
                                                placeAddress = it.addr1,
                                                star = it.is_good,
                                                hashTag = it.tag_list
                                            )
                                        )
                                    }
                                }

                                placeInfoAdapter.datas = likeData
                                placeInfoAdapter.notifyDataSetChanged()

                            } else {
                                Log.e(
                                    "MyPageActivity",
                                    "requestDataFromServer onResponse - ${response.message()}"
                                )
                            }
                        }

                        override fun onFailure(call: Call<MypageMyGoodListResponse>, t: Throwable) {
                            Log.e(
                                "MyPageActivity",
                                "requestDataFromServer onFailure - ${t.message}"
                            )
                        }

                    })
            }
            "parent_good" -> {
                RequestToServer.service.requestMypageParentsGoodList(body)
                    .enqueue(object : Callback<MypageParentGoodListResponse> {
                        override fun onResponse(
                            call: Call<MypageParentGoodListResponse>,
                            response: Response<MypageParentGoodListResponse>
                        ) {
                            if (response.isSuccessful) {
                                Log.e(
                                    "MyPageActivity",
                                    "requestDataFromServer parent_good onResponse - ${response.body()}"
                                )
                                val res = response.body()
                                likeData.clear()

                                // Init Tab Dynamic Data -------------------------------------------------------
                                res?.p_good_list?.forEach {
                                    likeData.apply {
                                        add(
                                            PlaceInfoData(
                                                image = it.firstimage,
                                                true,
                                                placeName = it.title,
                                                placeAddress = it.addr1,
                                                star = it.is_good,
                                                hashTag = it.tag_list
                                            )
                                        )
                                    }
                                }

                                placeInfoAdapter.datas = likeData
                                placeInfoAdapter.notifyDataSetChanged()

                            } else {
                                Log.e(
                                    "MyPageActivity",
                                    "requestDataFromServer onResponse - ${response.message()}"
                                )
                            }
                        }

                        override fun onFailure(call: Call<MypageParentGoodListResponse>, t: Throwable) {
                            Log.e(
                                "MyPageActivity",
                                "requestDataFromServer onFailure - ${t.message}"
                            )
                        }

                    })
            }
            "trip_record" -> {
                RequestToServer.service.reqeustMypageTripRecord(body)
                    .enqueue(object : Callback<MyPageTripRecordResponse> {
                        override fun onResponse(
                            call: Call<MyPageTripRecordResponse>,
                            response: Response<MyPageTripRecordResponse>
                        ) {
                            if (response.isSuccessful) {
                                Log.e(
                                    "MyPageActivity",
                                    "requestDataFromServer trip_record onResponse - ${response.body()}"
                                )

                                val res = response.body()
                                tripRecordDatas.clear()

                                // Init Tab Dynamic Data -------------------------------------------------------
                                res?.trip_record_list?.forEach {
                                    tripRecordDatas.apply {
                                        add(
                                            MyPageTripRecord(
                                                trip_name = it.trip_name,
                                                start_day = it.start_day,
                                                end_day = it.end_day,
                                                area_name = it.area_name,
                                                attend_famliy = it.attend_famliy
                                            )
                                        )
                                    }
                                }
                                logDatas.apply {
                                    add(
                                        MultiTravelLog(
                                            "???????????? ??????",
                                            tripRecordDatas
                                        )
                                    )
                                }

                                travelLogAdapter.datas = logDatas
                                travelLogAdapter.notifyDataSetChanged()

                            } else {
                                Log.e(
                                    "MyPageActivity",
                                    "requestDataFromServer trip_record onResponse - ${response.message()}"
                                )
                            }
                        }

                        override fun onFailure(
                            call: Call<MyPageTripRecordResponse>,
                            t: Throwable
                        ) {
                            Log.e(
                                "MyPageActivity",
                                "requestDataFromServer trip_record onFailure - ${t.message}"
                            )
                        }

                    }
                    )
            }
            "review" -> {
                reviewDatas.apply {
                    add(
                        CommentData(
                            "",
                            "???????????????????????????",
                            "2021.07.29",
                            5,
                            "???????????? ??????????????? ???????????? ?????? ??????????????? ????????? ??? ???????????????"
                        )
                    )

                    add(
                        CommentData(
                            "",
                            "???????????????????????????",
                            "2021.06.21",
                            4,
                            "???????????? ??????????????? ???????????? ?????? ??????????????? ????????? ??? ???????????????"
                        )
                    )

                    add(
                        CommentData(
                            "",
                            "???????????????????????????",
                            "2021.02.04",
                            3,
                            "???????????? ??????????????? ???????????? ?????? ??????????????? ????????? ??? ???????????????"
                        )
                    )
                }

                reviewAdapter.datas = reviewDatas
                reviewAdapter.notifyDataSetChanged()
            }
            else -> Toast.makeText(this, "??? ??? ?????? ???????????????.", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_mypage_btn_close -> finish()
            R.id.act_mypage_btn_invite -> {
                val intent = Intent(this, FamilyListActivity::class.java)
                startActivity(intent)
            }
        }
    }
}