package com.wetour.we_t.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.wetour.we_t.R
import kotlinx.android.synthetic.main.activity_auth.*
import org.json.JSONObject

class AuthActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var jsonData: JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_auth_btn_back -> finish()
            R.id.act_auth_btn_register -> {
                val intent = Intent(this, SignUpActivity::class.java)
                intent.putExtra("type", 2)
                intent.putExtra("inherence_number", act_auth_edit_number.text.toString().toInt())
                startActivity(intent)
            }
        }
    }

//    private fun showDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("인증 완료")
//        builder.setMessage(
//            "환영합니다!\n" +
//                    "회원가입이 완료되었습니다"
//        )
//
//        builder.setPositiveButton(
//            "로그인하러가기"
//        ) { dialog, which ->
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
////        builder.setNegativeButton(
////            "아니오"
////        ) { dialog, which ->
////            Toast.makeText(applicationContext, "아니오를 선택했습니다.", Toast.LENGTH_LONG).show()
////        }
//
//        builder.show()
//    }
}