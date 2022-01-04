package com.wetour.we_t.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wetour.we_t.R

class PersonalInformationSecurityActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_information_security)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_personal_btn_back -> finish()
        }
    }
}