package com.wetour.we_t.ui.myPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.R
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.network.data.FamilyCodeRequest
import com.wetour.we_t.network.data.FamilyCodeResponse
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

        Log.e("body", body.toString())

        val familyCodeRequest = FamilyCodeRequest("test2@a.com")
        Log.e("body", familyCodeRequest.toString())
        Log.e("body", familyCodeRequest.email.toString())

        RequestToServer.service.requestFamilyCode(familyCodeRequest).enqueue(object : Callback<FamilyCodeResponse> {
            override fun onResponse(call: Call<FamilyCodeResponse>, response: Response<FamilyCodeResponse>) {
                if (response.isSuccessful) {
                    Log.e("response", response.body().toString())
//                    act_invide_family_code.text = response.body()[0]
                }
            }

            override fun onFailure(call: Call<FamilyCodeResponse>, t: Throwable) {
                Log.e("Fail", "requestFamilyCode Fail ${t.message}")
            }

        })
    }
}