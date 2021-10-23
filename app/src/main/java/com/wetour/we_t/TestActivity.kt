package com.wetour.we_t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.ui.home.HomeActivity
import com.wetour.we_t.ui.makeSchedule.MakeScheduleActivity
import com.wetour.we_t.ui.makeTour.MakeTourActivity
import com.wetour.we_t.ui.myPage.MyPageActivity
import com.wetour.we_t.ui.placeDetail.PlaceDetailActivity
import com.wetour.we_t.ui.placeHome.PlaceHomeActivity
import com.wetour.we_t.ui.placeInfo.PlaceInfoActivity
import com.wetour.we_t.ui.user.SelectSignInMode
import com.wetour.we_t.ui.user.SignInActivity
import com.wetour.we_t.ui.user.SocialSignInActivity
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TestActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btnSignIn -> {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }

            R.id.btnSelectSignIn -> {
                val intent = Intent(this, SelectSignInMode::class.java)
                startActivity(intent)
            }

            R.id.btnHome -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }

            R.id.btnMakeTour -> {
                val intent = Intent(this, MakeTourActivity::class.java)
                startActivity(intent)
            }


            R.id.btnPlaceInfo -> {
                val intent = Intent(this, PlaceInfoActivity::class.java)
                startActivity(intent)
            }

            R.id.btnPlaceDetail -> {
                val intent = Intent(this, PlaceDetailActivity::class.java)
                startActivity(intent)
            }

            R.id.btnMakeSchedule -> {
                val intent = Intent(this, MakeScheduleActivity::class.java)
                startActivity(intent)
            }

            R.id.btnMypageChild -> {
                val intent = Intent(this, MyPageActivity::class.java)
                intent.putExtra("mode", "child")
                startActivity(intent)
            }

            R.id.btnMypageParent -> {
                val intent = Intent(this, MyPageActivity::class.java)
                intent.putExtra("mode", "parent")
                startActivity(intent)
            }

            R.id.btnPlaceHomeChild -> {
                val intent = Intent(this, PlaceHomeActivity::class.java)
                intent.putExtra("mode", "child")
                startActivity(intent)
            }

            R.id.btnPlaceHomeParent -> {
                val intent = Intent(this, PlaceHomeActivity::class.java)
                intent.putExtra("mode", "parent")
                startActivity(intent)
            }

            R.id.Login -> {
                val jsonData = JSONObject()
                jsonData.put("email", "test2@a.com")
                jsonData.put("pwd", "a123456")

                val body = JsonParser.parseString(jsonData.toString()) as JsonObject
                Log.e("body", "$body")

                RequestToServer.service.requestLogin(body).enqueue(object : Callback<JsonObject> {
                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        Log.e("success", "${response.body()}")
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Log.e("fail", "${t.message}")
                    }

                })
            }
        }
    }
}