package com.wetour.we_t.ui.makeTour

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wetour.we_t.R

class InviteFamilyActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite_family)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_invite_family_btn_back -> finish()
        }
    }
}