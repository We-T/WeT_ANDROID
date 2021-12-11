package com.wetour.we_t.ui.makeTour.selectTourDate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wetour.we_t.R
import kotlinx.android.synthetic.main.activity_select_tour_date.*
import java.util.*
import kotlin.collections.ArrayList

class SelectTourDateActivity : AppCompatActivity() {

    var selectedDate = arrayListOf<Date>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_tour_date)

        Toast.makeText(this, act_select_tour_date_calendarview.date.toString(), Toast.LENGTH_SHORT).show()

        act_select_tour_date_calendarview.setOnDateChangeListener { view, year, month, dayOfMonth ->
            var calendar = Calendar.getInstance()
            Toast.makeText(this, "$calendar $year $month $dayOfMonth", Toast.LENGTH_SHORT).show()
            view.date = calendar.timeInMillis
//            view.dateTextAppearance =
        }
    }
}