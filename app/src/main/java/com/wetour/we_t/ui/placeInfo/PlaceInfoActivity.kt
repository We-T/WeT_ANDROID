package com.wetour.we_t.ui.placeInfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.PreferenceUtil
import com.wetour.we_t.R
import com.wetour.we_t.data.PlaceInfoData
import com.wetour.we_t.databinding.ActivityPlaceInfoBinding
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.network.data.PlaceInfoResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlaceInfoActivity : AppCompatActivity() {

    private lateinit var preferenceUtil: PreferenceUtil

    private lateinit var placeInfoAdapter: PlaceInfoAdapter
    private lateinit var binding: ActivityPlaceInfoBinding
    private lateinit var placeInfoViewModel: PlaceInfoViewModel
    lateinit var placeCategoryAdapter: PlaceCategoryAdapter

    var placeInfoDatas = mutableListOf<PlaceInfoData>()

    var area = ""
    var category = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_place_info)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_place_info)
        binding.activityPlaceInfo = this

        preferenceUtil = PreferenceUtil(this)
        placeInfoViewModel = ViewModelProviders.of(this).get(PlaceInfoViewModel::class.java)

        // intent로 넘겨온 데이터 init
        area = intent.getStringExtra("area").toString()
        category = intent.getStringExtra("category").toString()

        initRv()
        getPlaceInfoData()

        placeInfoViewModel.getPlaceData().observe(this, Observer<List<PlaceInfoData>>{ item ->
            // update UI
            placeInfoAdapter.datas = placeInfoDatas
            placeInfoAdapter.notifyDataSetChanged()
        })

    }

    private fun initRv() {
        // 상단 카테고리에 대한 어댑터
        placeCategoryAdapter = PlaceCategoryAdapter(this,
            object :PlaceCategoryAdapter.OnClickListener{
                override fun clickCategory(position: Int) {
                    // 나머지 애들 초기화
                    for (i in 0..placeCategoryAdapter.itemCount) {
                        placeCategoryAdapter.datas[i].selected = false
                    }
                    // 클릭한 애만 selected상태로 버튼 change
                    placeCategoryAdapter.datas[position].selected = true

                    // 클릭한 카테고리로 검색
                    category = placeCategoryAdapter.datas[position].category
                    getPlaceInfoData()
                }
            })

        binding.actPlaceInfoCategoryRecyclerview.adapter = placeCategoryAdapter
        binding.actPlaceInfoCategoryRecyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        placeInfoAdapter = PlaceInfoAdapter(this)
        binding.actPlaceInfoPlaceRecyclerview.adapter = placeInfoAdapter
        binding.actPlaceInfoPlaceRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun getPlaceInfoData() {

        placeInfoAdapter.datas.clear()

        val jsonData = JSONObject()
        jsonData.put("email", preferenceUtil.getString("email", "noEmail"))
        jsonData.put("area_name", area)
        jsonData.put("category", category)

        val body = JsonParser.parseString(jsonData.toString()) as JsonObject
        RequestToServer.service.requestPlaceInfo(body).enqueue(object : Callback<PlaceInfoResponse> {
            override fun onResponse(
                call: Call<PlaceInfoResponse>,
                response: Response<PlaceInfoResponse>
            ) {
                if (response.isSuccessful) {
                    Log.e("PlaceInfoActivity", "onResponse ${response.body()}")

                    val res = response.body()

                    res?.p_good_area?.forEach {
                        placeInfoDatas.add(
                            PlaceInfoData(
                                image = it.firstimage,
                                heart = it.is_good==1,
                                placeName = it.title,
                                placeAddress = it.addr1,
                                star = it.score.toInt(),
                                hashTag = it.tag_list
                            )
                        )
                    }

                    placeInfoAdapter.datas = placeInfoDatas
                    placeInfoAdapter.notifyDataSetChanged()

                } else {
                    Log.e("PlaceInfoActivity", "onResponse ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PlaceInfoResponse>, t: Throwable) {
                Log.e("PlaceInfoActivity", "onFailure ${t.message}")
            }

        })
    }
}