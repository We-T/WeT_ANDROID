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
import com.wetour.we_t.ui.placeInfo.PlaceInfoActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SignInActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

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