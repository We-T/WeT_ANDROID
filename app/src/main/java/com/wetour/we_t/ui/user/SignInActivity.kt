package com.wetour.we_t.ui.user

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.PreferenceUtil
import com.wetour.we_t.R
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var preferenceUtil: PreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        preferenceUtil = PreferenceUtil(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_signin_btn_login -> {

                val jsonData = JSONObject()
                jsonData.put("email", act_signin_edit_id.text.toString())
                jsonData.put("pwd", act_signin_edit_pwd.text.toString())

                val body = JsonParser.parseString(jsonData.toString()) as JsonObject

                RequestToServer.service.requestLogin(body).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (response.isSuccessful) {
                            Log.e("SignInActivity", "onResponse ${response.body().toString()}")
                            val res = response.body()
                            when(res!!.get("code").asString) {
                                "200" -> {

                                    preferenceUtil.setString("email", act_signin_edit_id.text.toString())

                                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                                    startActivity(intent)
                                }
                                "204" -> {
                                    // ???????????? ?????? || ????????? ???????????? ??????
                                    Toast.makeText(this@SignInActivity, "???????????? ??????????????? ??????????????????!", Toast.LENGTH_SHORT).show()
                                }
                                else -> Toast.makeText(this@SignInActivity, "???????????? ??? ??? ?????? ?????? ??????", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Log.e("SignInActivity", "onResponse-fail ${response.message()}")
                            Toast.makeText(this@SignInActivity, "????????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e("SignInActivity", "onFailure ${t.message}")
                    }

                })
            }
            R.id.act_singin_btn_signup -> {
                val intent = Intent(this@SignInActivity, AgreementActivity::class.java)
                startActivity(intent)
            }
        }
    }
}