package com.wetour.we_t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class SelectSignInMode : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_sign_in_mode)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_select_sign_in_mode_btn_modeChild -> {

            }

            R.id.act_select_sign_in_mode_btn_modeParent -> {

            }
        }
    }
}