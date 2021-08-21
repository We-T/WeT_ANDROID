package com.wetour.we_t.ui.myPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.wetour.we_t.R
import com.wetour.we_t.data.MyFamilyData
import com.wetour.we_t.ui.makeTour.InviteFamilyActivity
import kotlinx.android.synthetic.main.activity_my_page.*

class MyPageActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var myFamilyAdapter: MyFamilyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        setRv()
        setData()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.act_mypage_btn_close -> finish()
            R.id.act_mypage_btn_addParents -> {
                val intent = Intent(this, InviteFamilyActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setRv() {
        myFamilyAdapter = MyFamilyAdapter(this)

        act_mypage_text_emptyFamily.isVisible = !myFamilyAdapter.datas.isEmpty()

        act_mypage_recyclerview.adapter = myFamilyAdapter
        act_mypage_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun setData() {
        myFamilyAdapter.datas.apply {
            add(
                MyFamilyData(
                    "data:image/jpeg;base64,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",
                    "철수",
                    "부모님모드"
                )
            )
            add(
                MyFamilyData(
                    "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBISEhgRERIZGRgZGBoaGBgZHBkcHhgaGRgZGRoYGBgcIS4lHB4rHxkZJzgmKy8xNTU1GiU7QDszPy40NTEBDAwMEA8QHxISHzUrIyg9NDQxNDQxNDY1NjQ0NDQ0NDQ2NDQ0MTQ0NTQ2NDQ0NDQ2ND00MTQ0MTQ0NDQ0NDQ0NP/AABEIAPkAygMBIgACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAAAQYEBQcDAgj/xABHEAACAQIDBQUFBQUDCwUAAAABAgADEQQSIQUGMUFREyJhcYEHMkJSkRRicqGxI4KSosGy4fAVFjNDY3ODwtHi8Rc0RFNU/8QAGgEBAAMBAQEAAAAAAAAAAAAAAAECAwQFBv/EACwRAAMAAgICAQIEBgMAAAAAAAABAgMRITEEEkEFURMUYcEVcYGhsdEiMpH/2gAMAwEAAhEDEQA/AOzSIiAIiIBMiIgCIiAIiIBMiIgExIkwBERAEREAREQBERAEREASJMQCIiIAiIgCIiAIiIAiIgCIiAJMiIBMSIgExImLWxarUSlYlnDEW5Ko1Y+FyB6wDJMqW0/aBgcPVNFi7kWu1NQ6gniMwbiOcrvtD3tLFsFh3sBpWcHiedNT06n06znInNlzqXpHqeJ9OrLPtT0vg/Q2zdrUMSuahVVxzynUeY4iZ4n5vw+IqU2D03ZGHBlJBHqJ0zc3fs1WTDYv3zolUaBjyDjkT1Ghlseea4fDM/J+nXhXsuUdGiQJM3PPEREAiIiAIiIAiIgCIiARESo72b60sHelTHaVrcPhS/Auf+Ua+UrVKVtl4x1kr1lbZaMTiadJC9V1RRxZiAB6mUra/tJw1IlMMhqn5r5F+pFz6Cc12vtnEYts2IqFui8FX8KjQTXzkvyX1J7Pj/SV3lf9EXDGe0XHvfIaaDllS5t4liZqq29e0GNzi6g8AQB+QmkDXJHT/peSovMXltvs758Px5W1K/ybb/OXH/8A66v8X90y8NvttGn/APJLfjVWv+UrsSFkv7l34mBrmUdF2Z7TnBAxNAMObUzY/wALaH6zY7S31wpDYihVGfsHVUcZWDl1tcHzvp0nKpDKDoQD5zWfIpdnHl+mYm9w9P7fAZyxJ4km5J5knU35wb8rfQ/reD/i8GY730dqXrw2/wCnQF/P8vpJVyCCLgjUenMGRBkbXyi7h64e/wBGdd3C3tOLvhsRbtVW6sP9Yo0Nx8w59ZeJwjcnP/lHD5L3z62+XK2a/wC7ed3no4adTtnzPnYZxZnM9H1ERNTjIiIgETwoYqm9wjq1uOUg21I1tw1B+k0m+O8YwFDMozVHJWmDwuOLHwE5Fs7eDFYes+Ip1O+7ZnuO65+8v+LTK80y9M6/H8LJnl1PSO/xKpurvnRxtqbgU63yE6N4oeflxlrl5pUto5risdetLTJkRNftzaS4XDvXfgi3A6twVfU2lm9EJNvSK3v5vZ9kTsKB/bOL3/8ArU/F+I8h6zkDsWJZiSSbknUkniSeZntjcW9eo9aq2Z3JLH+g8BwEx3YKLn/yegHMzzsuR3Wl0fT+J40eNj9q7+X+xIMgTb7K3axeJIy0GVW+NxlW3XXW36zd1dwaqD9piqKEkDW4sCdfetwEj8NlPz0JN7/2U4Dj58uY6yUaxB6Tpq7hYF1Ap1XvbVldWuetiCB6T4xu4FALT7FSzK4NQu5U1Esbr3RYHmPKW9E2YfxBKdJN/wAzmqoWNlBJ6AEnrwEgX5H1nZtj7r4XCVDUpKxYjKM5zZQeNvPrKpvLuOESricM7aAutEJmN7+6pB4ekhY+i/8AEZe01xrj+ZRDILC9ri/T+6XfZW4DVKfaYusaTNYogy93TTOTxPgLWmGm4+KNVqKmnZVDBwdGViQBYDTgdI/C7Lfn4prXGvuVUmRNltrYWJwjKKqHKzBVZdVYtooBHA3toZgVaToxR1Kspsym11I5G0pUNLZ1YPIjI3KfP7HzEiTaV7Rt/wBK18MvHsnpqcXUZh3lpd09LvZv0E67ObeyfZbqKmLYWVwET7wUksw8L6ek6SJ6OFNQkz5fzrV56a6JkSZE1OQREQDnHtcw/wCzoVb6hmS3XMuYH0yn6zmI5fn/AHS3e0raL1cc1EnuUQqqPF0V2b+YD0lVw9B6jBKaM7Hgqgkn0E4Mz9r0kfR+BCx+Oqp63z2fKOVIZSQRYgjiD4Ea3nTtz9+0dTRxzhWUHLUOgcLxDfe/Xzml2V7OMVUAau60geXvN5WGg+ssn/ptgxSZbu9TKcjs1grjVWCrYcbTXDFJ76Rx+f5GDJOk918PX7mXU3prFs1OguTkHYh2HU2FlPhrKr7Qt5FxKUqFK4t36qHirjRUblpqfHQzLWtUvkahV7S9igRz3udmtlIv8V7SkbbaocTUFUKHDFSFNwMulr8yNbzfO0p4+Ty/payZM/8AzWkuTBvbWdQ3M3do4fDriayL2jDOXfXs05AZvd01PnKNuvsv7Xi0pkd0XZ/wra49dB6zq208C1d6dMgCgvfcX98rbJTI+T4j+ECccL5PX87K21H/ALyYi4ivjCeyLUcPw7W1qlXqaYPuJ946nl1mZh9hYWmNKKsebv32PiWe5M1++W89PZ1EGwao9xTTlp8TfdE4ntLeHGYly1Wu5zfCCQB4Ko4TWZdHmtpHe6uwsK3eWkqNyen3GB6hljZ2JqLUfC1mzOqh0ewHaITluwGmdTobaag85+f8HtTFYZw1OrUpsNdS2vmG4idX3K2+20qiVHypWoK6vYaVKdQCxHQhlBMmpaRCrZfJpdobQq1KpwuEsHUA1arC60QeCgfFUI4LyGp5A7HaOINOjUqAqCiMwLGyggEjMel5xbG7810XscI2RblqlWwz1XbV3YngCeA5C0rMtlm9HWqG7mGAvVQ1nPvPWJdieZsdF8gAIfYapdsI5ovyCkmm3QPTOlvKxnGcDvztGk+cYhn5lX7yn0P9J2PdPeBNoYcVlGVgcrp0a3LwMmpckJpg0qe0cP2ddSrK5V0B9yomnqNQwPkZTqm5+Kx2Kr1aZVaRqOFdiTmK2U2Ua8QdZdqWHeni61REJR6SNpbWqmZbeZXL9Jk7s42mtFKDtkrWJam4KsXYlmyg+8Lk6i8mZmuGWnNeJ7l6ZzXEbgbRR8i00cE6OrqFHi2azD0Bl02L7PMLRIeves2VQwa+XMCSSFva2oFj8vjLtaTaazimekRl8zLlSVPo+KdMKAqgAAWAGgAHICekRNDmEREAiIiAUvb+5K4zGriGbLTKWcD3mZTZbHgAV4njoJYtk7Hw+FXLh6Sp1IGp/Ex1M2UiVUpPZesltKW+ETETA21ijSoO6+9YKni7kKn8xEsUNdUqNiXfvMKSMUUKSudl0ZmYa5QdAB0J6Sk74bnKiNicKDpdnS5bTiXQnW/MiXvB4cU6aUx8KgX6nmT4k3PrPczkuvZ8nXip42qnsonswwYFOpiCNWIRT91RmNvC5H0l7mFsvZyYZDTp6KXdwOmdixA8LkzNlf0LXTunT+TiPtXrO20Sje6tOnk8iLn87zf+xbZmGc1q7hWrIyqitYlFIuWCnmTpfwm83/3POPVatEgVkFrHQOvHLfkek5HW2XjcLU1p1abj4lDr/MvKdEUtHPSOre2j7MMIgYL2/aL2fDNk+Pxy26yo+yFGOOdhwFFrnzZbD/HSVilsrG4qp/oq1Rz8TB2NvxNynYtwN12wFFmq27WpYuBrkA4Jfn1MXS0JR4+1PEMmzmCm2d0U+XEj8pRPZZu/RxuLf7QAyUkD9meDsWsL9VFr28ROtbx7HTG4Z8O5tm1VvlcaqfrOF4/ZmN2ZWv36bj3aiEgMPB+BHgZXHS1otSOme2TZ+GXBU3CqtVXVKeUAEoffWw+EAX8xK97G6j/aK6XOXs1JHK+ewMo2JxWKxlQNVqVKz+6CxLkeAA4ek7F7Nt3HweHapWXLUqkEqeKIOAPieJHlLZGtFZT2XOeOKwqVVyVEDDx5HqDxB8RPaJzmprExdTBMoqu1TDEhc7avQJNlzn4qfLMdRzuNRZwZqatNXUo4BVgQwPAg6ET43ZqN2JpOSWou1Ik8SF1QnxylZ0Y63wzG51yjdRETUzEREASJMQCIkzXbR2rTw7ItS4DkgNburYXux5DxgGfNLt85nw1Pk1bMfJEdh+dvpNzmHXx9Os0u8SsvY1wpZaVQlwBchGRkLADja4J8LytdMmezIieAxtLLn7VMpF82ZbW+s8Ex5qaYZDU+/wC6g83PvfugzmUtnS6SM6JpMbtSphmKu9Go1rinTzh1Py5QGzeZt5T1w23qTKDUSpRJ5VUZAPDPbL+cOGiFSZtoIvoRfznxSqK4ujBh1Ug/pPuVJAiIgkT5qIrCzKCOhAP5GfUmAeNLDU01Smi/hUD9BPWJ4YzG0qK561RUHViBfy6wD3kzGweNpVlz0aiut7EqQbHoehmRAExtgW7fFW4don8XZJf+kyCwF7m1hc+A6zUbk7VoYjtjSJLPUao3dYDKSUSzEWJy076dZri7M8nRbYiJ0GAkSZEAmIiARKptMVKuMFFqihUy1UTKDnTKadQFr8bsQRbgRLXKWyvhsdVZMNV7JxmaoEDlqjG5yMpzBBzDczpKXvXBadb5PrC4JsLiftBYlC6UlzG+WkwICeS1CCCdbEy0YvH0aIzVaqIPvMF/WV3E7XoOhpmnVcsMvZilUDNfpcADzvpMTd/CUXq1aqozi4Gesl3FQFg6B2GqqAo00vKw3rku5TfBlV9oYd7tg8GKtRj3Xalkp3+dqjLw/DcmeybNrN3q+LqE/LSIpIvgAozW8zNtEtsspSMbA4KnQXJTWwvcnizE8Wdjqx8TMg66HWTEgk021NhUnRmp0wlQd5WQlCSNbEoRoeHrPfCbNL01q4fFVUDKCFcioo6g5+9odPemymJsx+yrvQPuPepT8Df9og9SG/eMlJPsre1yjzNHHJqVpVR90tTb0DXU/UTzO16a6V1eielRSB6Ot1PoZYibamaWttftSaeEUVOTVGv2a+R+M+C6eIkPHJWao+sPi6dTWnUR/wALA/oZ7SuY3dVGb7QmV6/xdoAFcfIAtuz8GXUc7zL2bg1rKfs+JrUXQ2ei5FTI3Qq9yV6EGxEz/C+zLutdm3ZrAk8hf6TmOIxbYhzXc3Lar0RL91V6aW9ZdtqvjMJSNR2o1hdVCZXps7OwUKpzMLm/SVGpu3j0qEU6CijxsHV3T7qXyhh0vCho1xZJT2zL3PLDGEJ7rUWL9NGXIT4+8PrL4SBqeA1MrWw0OGQqmCxBdtXd+yBcjhqHsAOQmNtPbdaohoKopVHY01p2NR73IOcrZKYKhiCSbgaSHLbIq022Y1Ch2uJNWtXOTEIC9JcxIpqzFA54U6eXidC1zLNulR1xFVFtSqVF7HSwKIirdRbRbg25cxxmrwpo1cY2CqjIqBStMDTEZVsHdxoQMtgh45bm8vCgDQTWJ+Tnp/B9RETQoIiIAiIgCIiARNHit3VZmejXrUSxzMKbDKSeJysCAT4Wm8iAV7/ImKQfs8e5PSqlNx/KFP5z5+y7SXXNhqluWWohP712A+kscGRpFvZlQfamMWqKL4JWbIX7lZSAoYL8QGtzp5TaYDEVHUmpRNIg2Clla463WY2zG7V6uK5O2RP93TJUEfibO3qJs5Vms71yJiY7DGoFKtldGDIx1AYaEEc1IJB85lxILGvfB1KxviqgK8qKXCfvk95/Ww8JnooAAAAA0AGgA6ASYklUkuhMDH4Auwq0nyVlHce1wR8jj4kPTlxEz4kE9mmWpWxNema1E00oAswJBV6x7qlCPeVRc3PNh0m3qOFUseCgk+QFzPqYm1Xy4eqf9m/9kyXyQlpcGTTcMoccGAI8iLiYKbIpZWDrnLvndm4luCm44WGg8Jk4JctNF6Ig/lE95BJptrYSnSohqVNUK1aTgqoGoqKDcjwJ+stcrW8P/tmHzNTUeZqIBLJLIyvsmIiWKCIkQCYiIAiIgCIkGAJXN69u08PRemH/AGrqQoUXKhtM7W91QCTc9Jhbf3mbM1DCEXGj1uIQ81QfE3jwHjwlYSmFvxJY3ZmN2cniWJ4mQ2dmDw7yL2fCOgYWkqU0RPdVVC26AACe0omydvthXGHYM9O1+7q1EcvxIeQ4jleXLB4ynWXPSqB18OXgRxB8DKi4qHpmTESJBQmIiAIiIAmt3hF8LUT517P1chP6zZTXbU7zUKXz10JH3ad6hv4XVfrJRV9H2Nj1aIAwta6jhTrXdbDkr+8vreQcRi19/BlvGnURh/PlMsES2kZKmitrgsRiKqNXQUqVNg4TMHeo6+5ny91VU62BJJA4WlkiISIb2TERJIEREAREQBERAImNjqRqUnRGysyMqt8pIIB9DMgzRb17wLgKHalczMcqJe2ZuOp5AC5MEynVJT2UPDI1MdjUTI6d10PI/MDzVuIPORiqjqvcXMxNl6Anmx5AS00FpbYwq4hR2VZcyBhrlYcUf50OhsevIytkOjGlWXJUX3l5EfMp+JT1lWj2sGf2X4dcNcHlhMMKa2vdibux4s3Xy6CKzZP2iMyPoFZDlJJ0ANveHgZ7TwNMtUDN7qDujqx4t6DT1Mg6qhOdaNrhtvYxAA7JV65lyE9e8un5T3r74VEKKcICXbKLVBobE3N14WBmqmKe9X8ET+Z/+0fnBzX4mP4LPQ3rIb9vQKr86Nnt+JbA28rywYTF06y56VRXXqpv9ehlCmBQoKa1VxcEFBdGZDfLc6qR1EGeTw0mvV9/c6naa/GbZw1E2qVkB+UHM38K3MoeNR2psva1T3SQGqORcC+ozaifeFCZFamoAZQdBbiLwUnw6daplvob04J2KiuFYaEOGT+0J9bGxaYvGPVpMGp0E7MMOBqOQz262ULr96UdzkxC9KiEH8aaj6qT9JtdkbS+yYhap/0b2Sr0W5slT0JsfBvCSjPL4jmW096OmxIBkyx5wiIgCIiAJEmRAJiIgCImNjcUlGm1WowVEUszHgAOMA+cbi6dFGq1WCooJZjwAE4hvVt98diDUNxTW6006L8xHzNx+gnpvZvRUx781oqe5T+bo7jmx5Dl5zI2TuNjsSnaZVpra6ipcM3koF1HifpKt76Pb8PDj8Zfi5mk/hFw3M2Ni8DRaoWplKgV2puWQ07DU57Ee7a9xy4ydobRo7TVaCUqiVCxKVSmiqh79SlU4MtrC3A5hpMalUxhXD7N2hTIDOb1AwZayU1zCmzDgxIFweKjzltFFMwcKMwXKD0W97DoNB9JS79eEeZTd273zsomOwtbCm1dbp8NZR3T+MfAfy8Z8g31Gs6Aygggi4PEHgfMStY7d1HrLTwhNJj36hXVFTxpnQOx4WtwJ5SJrfB3Y/McLVco0k86VEKXYcXbMfoAB5aS20tzV/1mJdvBVRL/AJEzzx+55y5sNXYMPhqWZW8LgBl89fKaaNPz+JtcMrUxsJSZTUZhbM5I/DlVR+kyaqtTfsqyFH+U6hh1RuDDy1iQdc1N6qWCL6TxwVI06aUzqVFvpw/KfdSsi6MwBPAcz5AamZS7OxTU2qLTCKqs16hILBRfRBqOHO0htLsreWIe2zFq0QxUnijZh52I/rJqU1dSjC4YEEdQdJuP818YUVkei1wDlOdeIvx1ihurjXNnalSXmVLO37oIAv5ydMwfmYNPn+xuNxtotVwxpVDd6DdmxPxKACjnxKkeoMs81Wxdj0sIhSncljmd2Ny7Wtdj5DgNBNrLni206euiYiIKiIiAIiIAiIgETB2ts+niqL4erfK4sbGx0IIIPmBM+RAT0VvYe52DwjdoiZn5O5zEfhHBfSWMSYgmrqnunsxMfgUroadQaaEEaFWGoZTyIPOajD13R/s+I9/4H4LVUcx0cc19RpLFMbGYSnVTJUUMvHyI4EEagjqJSpVIma0avGYkplVBmqOcqL1PNm6Ko1JmfszBCilr3ZjmdzxZjxPlyA5ACfOB2TTosXUuzEWzOxYhR8IJ4CbGIn1FV7CRJiXKmHjcBSrrkrU1cdGANvEdDK5tXdHDBO0pq4KEMUFSpZlHvLbN0va3MCW6CJDRabqemV/A4DD0wGo00FwCGAFyDwObiZO1Wth6p/2b/mpH9Zh1ceMO5wiI1SoGulNOPZtqGZjoirci56aTH2ocf2dnoUVR3SmbVGZwHdVuO4ATrwnP6Vs1drRbsKLU1HRVH5Ce0gSZ0mAiIgCIiAIiIAiIgEREQBERAEREAREQBERAEREAREQDW2C4u9vfo2v/ALt9Bf8A4h+kr+/u8KYNaPdDv2mfJmt3UVrM3RQ5X6TXe0beGph6tGnhmC1FDszWDWVxlC2PM2v6TmmNxdSs5qVqjOx4ux1sOA6AeAlXWj0vE+nXmn3fC/yfoXCVjUpo7DKWVWI6EgG0yJxHZex9pbQIYPVy8O0qO6pb7o+L90WnU92NhnBUezas9ViczMxJANrWQEnKJKZyZ8KxPXsm/wBDeRESTAREQBERAEREAREQBERAEREAREQBERAEREAREQDnm1fZ9UxOMqYipiQKbtcAKS4FgAtzoALTe7J3KwOGswpZ3Hx1DmN+oB0H0lliRo1fkZHKn2el8AC0mIkmQiIgCIiAIiIAiIgCIgQBERAEREARECAIiIAiBEARJkQBERAEREAREQBERAEREAREQD//2Q==",
                    "유리",
                    "부모님모드"
                )
            )
        }

        myFamilyAdapter.notifyDataSetChanged()
    }
}