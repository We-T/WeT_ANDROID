package com.wetour.we_t.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wetour.we_t.R
import kotlinx.android.synthetic.main.activity_agreement.*

class AgreementActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agreement)

        act_agreement_btn_done.isSelected = false

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_agreement_btn_detail -> {
                val intent = Intent(this, PersonalInformationSecurityActivity::class.java)
                startActivity(intent)
            }

            R.id.act_agreement_btn_checkbox1 -> {
                act_agreement_btn_checkbox1.isSelected = !act_agreement_btn_checkbox1.isSelected
                act_agreement_btn_done.isSelected = act_agreement_btn_checkbox1.isSelected && act_agreement_btn_checkbox2.isSelected && act_agreement_btn_checkbox3.isSelected
            }

            R.id.act_agreement_btn_checkbox2 -> {
                act_agreement_btn_checkbox2.isSelected = !act_agreement_btn_checkbox2.isSelected
                act_agreement_btn_done.isSelected = act_agreement_btn_checkbox1.isSelected && act_agreement_btn_checkbox2.isSelected && act_agreement_btn_checkbox3.isSelected
            }

            R.id.act_agreement_btn_checkbox3 -> {
                act_agreement_btn_checkbox3.isSelected = !act_agreement_btn_checkbox3.isSelected
                act_agreement_btn_done.isSelected = act_agreement_btn_checkbox1.isSelected && act_agreement_btn_checkbox2.isSelected && act_agreement_btn_checkbox3.isSelected
            }

            R.id.act_agreement_btn_done -> {
                if (act_agreement_btn_done.isSelected) {
                    val intent = Intent(this, SelectSignInMode::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}