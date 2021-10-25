package com.wetour.we_t.ui.user

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.auth.LoginClient
import com.kakao.sdk.auth.model.OAuthToken
import com.wetour.we_t.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SocialSignInActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_sign_in)

        getHashKey()

    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
        for (signature in packageInfo!!.signatures) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_signin_btn_kakao -> {
                // 로그인 공통 callback 구성
                LoginClient.instance.run {
                    if (isKakaoTalkLoginAvailable(this@SocialSignInActivity)) {
                        loginWithKakaoTalk(this@SocialSignInActivity, callback = kakaoCallback())
                    } else {
                        loginWithKakaoAccount(this@SocialSignInActivity, callback = kakaoCallback())
                    }
                }

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

    private fun kakaoCallback(): (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            //Login Fail
            Toast.makeText(this, "KakaoLogin Fail", Toast.LENGTH_SHORT).show()
        } else if (token != null) {
            //Login Success
            Toast.makeText(this, "KakaoLogin Success", Toast.LENGTH_SHORT).show()
            Log.e("kakaoLogin toker", token.toString())

            val intent = Intent(this, SelectSignInMode::class.java)
            startActivity(intent)
        }
    }
}