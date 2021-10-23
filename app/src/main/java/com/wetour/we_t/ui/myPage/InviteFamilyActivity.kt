package com.wetour.we_t.ui.myPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.R
import com.wetour.we_t.network.RequestToServer
import kotlinx.android.synthetic.main.activity_invite_family.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InviteFamilyActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_family)

        getData()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_invite_family_btn_back -> finish()
        }
    }

    private fun getData() {
        val jsonData = JSONObject()
        jsonData.put("email", "test2@a.com")

        val body = JsonParser.parseString(jsonData.toString()) as JsonObject

        RequestToServer.service.requestFamilyCode(body).enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    Log.e("InviteFamilyActivity", "success ${response.body()!!.get("inherence_number").asString}")
                    val res = response.body()!!.get("inherence_number").asString
                    act_invide_family_code.text = res
                } else {
                    Log.e("InviteFamilyActivity", "onResponse-fail ${response.message()}")
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("InviteFamilyActivity", "onFailure ${t.message}")
            }

        })
    }
}