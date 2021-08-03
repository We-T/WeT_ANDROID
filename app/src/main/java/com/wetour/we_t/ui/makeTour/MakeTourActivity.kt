package com.wetour.we_t.ui.makeTour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wetour.we_t.R
import com.wetour.we_t.ui.makeTour.selectTourLocation.SelectTourLocationActivity
import kotlinx.android.synthetic.main.activity_make_tour.*

class MakeTourActivity : AppCompatActivity(), View.OnClickListener {

    private val REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_tour)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_make_tour_btn_close -> finish()

            R.id.act_make_tour_btn_makeTour -> {

            }

            R.id.act_make_tour_edit_tourName -> {

            }
            R.id.act_make_tour_edit_tourDate -> {}

            R.id.act_make_tour_btn_tourLocation -> {
                val intent = Intent(this, SelectTourLocationActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
            R.id.act_make_tour_btn_inviteFamily -> {
                val intent = Intent(this, InviteFamilyActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == 200) {
            act_make_tour_btn_tourLocation.text = data?.getStringExtra("place").toString()
        }
    }
}