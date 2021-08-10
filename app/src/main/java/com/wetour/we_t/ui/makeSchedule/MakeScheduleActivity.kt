package com.wetour.we_t.ui.makeSchedule

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.wetour.we_t.R
import kotlinx.android.synthetic.main.activity_make_schedule.*


class MakeScheduleActivity : AppCompatActivity(), View.OnClickListener {

    var radioButtonList = arrayListOf<RadioButton>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_schedule)

        for (i in 1..3) {
            val radioButton = RadioButton(this)
            radioButton.text = "DAY$i"
            radioButton.id = i
            radioButton.gravity = Gravity.CENTER
            radioButton.setBackgroundResource(R.drawable.background_gradient_round_all_btn_45)
            radioButton.setButtonDrawable(0)
            radioButton.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            radioButton.textSize = 12F
            radioButtonList.add(radioButton)
            val rprms: RadioGroup.LayoutParams =
                RadioGroup.LayoutParams(
                    RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT,
                    1f
                )

            act_make_schedule_radioGroup.addView(radioButton, rprms)
        }

        act_make_schedule_radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                1 -> Toast.makeText(this, radioButtonList[checkedId].text, Toast.LENGTH_SHORT).show()
                2 -> Toast.makeText(this, radioButtonList[checkedId].text, Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(this, radioButtonList[checkedId].text, Toast.LENGTH_SHORT).show()
            }
        }

    }

    //            android:fontFamily="@font/kopubworld_dotum_bold"
    //            android:onClick="onClick"

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_make_schedule_btn_back -> finish()
        }
    }

}