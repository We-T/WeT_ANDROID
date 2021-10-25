package com.wetour.we_t.ui.myPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.PreferenceUtil
import com.wetour.we_t.R
import com.wetour.we_t.data.MyFamilyData
import com.wetour.we_t.network.RequestToServer
import kotlinx.android.synthetic.main.activity_family_list.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FamilyListActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var preferenceUtil: PreferenceUtil

    private lateinit var myFamilyAdapter: MyFamilyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_list)

        preferenceUtil = PreferenceUtil(this)
        setRv()
        getDataFromServer()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_family_list_btn_back -> finish()
            R.id.act_family_list_btn_invite -> {
                val intent = Intent(this, InviteFamilyActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setRv() {
        myFamilyAdapter = MyFamilyAdapter(this)

        act_family_list_text_emptyFamily.isVisible = myFamilyAdapter.datas.isNotEmpty()

        act_family_list_recyclerview.adapter = myFamilyAdapter
        act_family_list_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getDataFromServer() {

        val jsonData = JSONObject()
        jsonData.put("email", preferenceUtil.getString("email", "noEmail"))

        val body = JsonParser.parseString(jsonData.toString()) as JsonObject

        RequestToServer.service.requestFamilyList(body).enqueue(object : Callback<JsonObject>{
            override fun onResponse(
                call: Call<JsonObject>,
                response: Response<JsonObject>
            ) {
                if (response.isSuccessful) {
                    Log.e("FamilyListActivity", "onResponse ${response.body()}")

                    val res = response.body()!!.get("family_list")

                    act_family_list_num.text = res.asJsonArray.size().toString()

                    res.asJsonArray.forEach {
                        myFamilyAdapter.datas.add(
                            MyFamilyData(
                                name = it.asJsonObject.get("name").asString,
                                image = it.asJsonObject.get("profile").asString,
                                isSelected = false
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
}