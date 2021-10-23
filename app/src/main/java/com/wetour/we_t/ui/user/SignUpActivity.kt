package com.wetour.we_t.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wetour.we_t.R

class SignUpActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_signup_btn_back -> finish()
            R.id.act_signup_btn_next -> {
                val intent = Intent(this, SelectSignInMode::class.java)
                startActivity(intent)
            }
        }
    }
}