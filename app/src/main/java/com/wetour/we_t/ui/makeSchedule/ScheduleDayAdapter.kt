package com.wetour.we_t.ui.makeSchedule

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ScheduleDayAdapter(fm: FragmentManager, private val fragmentCount: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return ScheduleFragment(position)
    }

    override fun getCount(): Int = fragmentCount

}