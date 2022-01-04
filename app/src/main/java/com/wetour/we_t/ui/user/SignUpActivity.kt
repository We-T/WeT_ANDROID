package com.wetour.we_t.ui.user

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var jsonData: JSONObject
    lateinit var preferenceUtil: PreferenceUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        preferenceUtil = PreferenceUtil(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_signup_btn_back -> finish()
            R.id.act_signup_btn_next -> {
                jsonData = JSONObject()
                jsonData.put("email", act_signup_edit_id.text.toString())
                jsonData.put("pwd", act_signup_edit_pwd.text.toString())
                jsonData.put("name", act_signup_edit_name.text.toString())
                jsonData.put("phone", act_signup_edit_phone.text.toString())
                jsonData.put("type", intent.getIntExtra("type", 1).toString().toInt())

                if (intent.getIntExtra("type", 1).toString().toInt() == 1) {
                    jsonData.put("inherence_number", null)
                } else {
                    jsonData.put("inherence_number", intent.getIntExtra("inherence_number", 0))
                }

                val body = JsonParser.parseString(jsonData.toString()) as JsonObject
                Log.e("body", body.toString())

                RequestToServer.service.requestJoin(body).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (response.isSuccessful) {
                            Log.e("SelectSignInMode", "onResponse ${response.body().toString()}")

                            val res = response.body()
                            when(res!!.get("code").asString) {
                                "200" -> {

                                    preferenceUtil.setString("email", act_signin_edit_id.text.toString())

                                    val intent = Intent(this@SignUpActivity, HomeActivity::class.java)
                                    startActivity(intent)
                                }
                                "404" -> Toast.makeText(this@SignUpActivity, "회원가입 실패 - 기존에 있는 회원입니다.", Toast.LENGTH_SHORT).show()
                                else -> Toast.makeText(this@SignUpActivity, "서버에서 알 수 없는 코드 수신", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Log.e("SelectSignInMode", "onResponse ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e("SelectSignInMode", "onFailure ${t.message}")
                    }

                })
            }
        }
    }
}
