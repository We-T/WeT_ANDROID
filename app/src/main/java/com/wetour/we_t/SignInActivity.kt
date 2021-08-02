package com.wetour.we_t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SignInActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)


    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_signin_btn_kakao -> {
                val intent = Intent(this, SelectSignInMode::class.java)
                startActivity(intent)
            }

            R.id.act_signin_btn_naver -> {
                val intent = Intent(this, SelectSignInMode::class.java)
                startActivity(intent)
            }

            R.id.act_signin_btn_google -> {
                val intent = Intent(this, SelectSignInMode::class.java)
                startActivity(intent)
            }
        }
    }
}