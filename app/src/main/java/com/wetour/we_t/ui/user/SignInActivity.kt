package com.wetour.we_t.ui.user

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wetour.we_t.R
import com.wetour.we_t.ui.placeHome.PlaceHomeActivity
import com.wetour.we_t.ui.placeInfo.PlaceInfoActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


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
                val intent = Intent(this, PlaceInfoActivity::class.java)
                startActivity(intent)
            }

            R.id.act_signin_btn_google -> {
                val intent = Intent(this, PlaceHomeActivity::class.java)
                startActivity(intent)
            }
        }
    }
}