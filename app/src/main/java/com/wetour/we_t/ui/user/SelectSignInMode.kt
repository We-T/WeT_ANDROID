package com.wetour.we_t.ui.user

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.wetour.we_t.R
import com.wetour.we_t.network.RequestToServer
import com.wetour.we_t.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectSignInMode : AppCompatActivity(), View.OnClickListener {

    lateinit var jsonData: JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_sign_in_mode)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_select_sign_in_mode_btn_modeChild -> {
                val intent = Intent(this, SignUpActivity::class.java)
                intent.putExtra("type", 1)
                startActivity(intent)
            }

            R.id.act_select_sign_in_mode_btn_modeParent -> {
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            }
        }
    }
}