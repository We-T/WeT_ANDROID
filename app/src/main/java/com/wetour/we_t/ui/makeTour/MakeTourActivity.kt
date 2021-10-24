package com.wetour.we_t.ui.makeTour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.PreferenceUtil
import com.wetour.we_t.R
import com.wetour.we_t.data.MyFamilyData
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.ui.home.HomeActivity
import com.wetour.we_t.ui.makeTour.selectTourLocation.SelectTourLocationActivity
import com.wetour.we_t.ui.myPage.MyFamilyAdapter
import kotlinx.android.synthetic.main.activity_family_list.*
import kotlinx.android.synthetic.main.activity_make_tour.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MakeTourActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var preferenceUtil: PreferenceUtil
    private val REQUEST_CODE = 100
    private lateinit var myFamilyAdapter: MyFamilyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_tour)

        preferenceUtil = PreferenceUtil(this)
        setRv()
        getDataFromServer()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == 200) {
            act_make_tour_btn_tourLocation.text = data?.getStringExtra("place").toString()
        }
    }


    private fun setRv() {
        myFamilyAdapter = MyFamilyAdapter(this)

        act_make_tour_recyclerview_family.adapter = myFamilyAdapter
        act_make_tour_recyclerview_family.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getDataFromServer() {

        val jsonData = JSONObject()
        jsonData.put("email", preferenceUtil.getString("email", "noEmail"))

        val body = JsonParser.parseString(jsonData.toString()) as JsonObject

        RequestToServer.service.requestFamilyList(body).enqueue(object : Callback<JsonObject> {
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.isSuccessful) {
                    Log.e("FamilyListActivity", "onResponse ${response.body()}")

                    val res = response.body()!!.get("family_list")

                    res.asJsonArray.forEach {
                        myFamilyAdapter.datas.add(
                            MyFamilyData(
                                name = it.asJsonObject.get("name").asString,
                                image = it.asJsonObject.get("profile").asString,
                                isSelected = true
                            )
                        )
                    }
                    myFamilyAdapter.notifyDataSetChanged()

                } else {
                    Log.e("FamilyListActivity", "onResponse ${response.message()}")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("FamilyListActivity", "onFailure ${t.message}")
            }
        })
    }

    private fun makeTour() {
        val jsonData = JSONObject()
        jsonData.put("email", preferenceUtil.getString("email", "noEmail"))
        jsonData.put("area_name", act_make_tour_btn_tourLocation.text)
        jsonData.put("trip_name", act_make_tour_edit_tourName.text.toString())
        jsonData.put("start_day", "00000000")
        jsonData.put("end_day", "11111111")

        // 선택된 부모 배열 만들기
        val attentFamily = ArrayList<String>()
        myFamilyAdapter.datas.forEach {
            if (it.isSelected) {
                attentFamily.add(it.name)
            }
        }

        jsonData.put("name", attentFamily)


        val body = JsonParser.parseString(jsonData.toString()) as JsonObject

        RequestToServer.service.requestMakeTour(body).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.e("MakeTourActivity", "onResponse ${response.body()}")
                    if (response.body()?.get("code")?.asInt == 200) {
                        Toast.makeText(this@MakeTourActivity, "여행을 성공적으로 개설했습니다.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MakeTourActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                } else {
                    Log.e("MakeTourActivity", "onResponse ${response.message()}")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("MakeTourActivity", "onFailure ${t.message}")
            }

        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_make_tour_btn_close -> finish()

            R.id.act_make_tour_btn_makeTour -> {
                makeTour()
            }

            R.id.act_make_tour_edit_tourName -> {

            }
            R.id.act_make_tour_edit_tourDate -> {}

            R.id.act_make_tour_btn_tourLocation -> {
                val intent = Intent(this, SelectTourLocationActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }
    }
}