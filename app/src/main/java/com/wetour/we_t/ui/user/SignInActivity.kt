package com.wetour.we_t.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.R
import com.wetour.we_t.network.RequestToServer
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_singin_btn_signup -> {

                val jsonData = JSONObject()
                jsonData.put("email", act_signin_edit_id.text.toString())
                jsonData.put("pwd", act_signin_edit_pwd.text.toString())

                val body = JsonParser.parseString(jsonData.toString()) as JsonObject

                RequestToServer.service.requestLogin(body).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        Log.e("success", "${response.body()}")
                        if (response.isSuccessful) {
                            val res = response.body()
                            if (res!!.get("code").asString == "200") {
                                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e("fail", "${t.message}")
                    }

                })
            }
        }
    }
}