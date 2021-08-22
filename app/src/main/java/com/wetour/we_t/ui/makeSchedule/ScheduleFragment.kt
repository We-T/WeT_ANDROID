package com.wetour.we_t.ui.makeSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wetour.we_t.R
import kotlinx.android.synthetic.main.fragment_schedule.view.*

class ScheduleFragment(private val position: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        view.text1.text = position.toString()

        return view
    }


}