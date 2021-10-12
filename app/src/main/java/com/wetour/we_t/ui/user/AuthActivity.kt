package com.wetour.we_t.ui.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.wetour.we_t.R
import com.wetour.we_t.ui.home.HomeActivity

class AuthActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_auth_btn_back -> finish()
            R.id.act_auth_btn_register -> {
                showDialog()
            }
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("인증 완료")
        builder.setMessage("환영합니다!\n" +
                "변다솔님의 가족 구성원으로 가입이 완료되었습니다")

        builder.setPositiveButton(
            "예"
        ) { dialog, which ->
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("mode", "parent")
            startActivity(intent)
        }
        builder.setNegativeButton(
            "아니오"
        ) { dialog, which ->
            Toast.makeText(applicationContext, "아니오를 선택했습니다.", Toast.LENGTH_LONG).show()
        }

        builder.show()
    }
}