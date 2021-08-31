package com.wetour.we_t.ui.makeSchedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wetour.we_t.R
import com.wetour.we_t.data.ScheduleItemData
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment(private val position: Int) : Fragment() {

    lateinit var scheduleItemAdapter: ScheduleItemAdapter
    var pos = position.toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_schedule, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRv()

        if (pos == "1") {
            setData()
        }
    }

    private fun setRv() {
        scheduleItemAdapter = activity?.let { ScheduleItemAdapter(it) }!!
        frag_schedule_recyclerview.adapter = scheduleItemAdapter
        frag_schedule_recyclerview.setHasFixedSize(true)
        frag_schedule_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun setData() {
        scheduleItemAdapter.datas.apply {
            add(
                ScheduleItemData(
                    1,
                    "14.3km",
                    "제주 무지개 하우스",
                    "350km",
                    "숙소",
                    null,
                    null
                )
            )
            add(
                ScheduleItemData(
                    2,
                    "17.3km",
                    "칠돈가 함덕 점",
                    "350km",
                    "음식점",
                    "30분 소요",
                    "여유"
                )
            )
            add(
                ScheduleItemData(
                    3,
                    "2.3km",
                    "성산일출봉",
                    "350km",
                    "관광지",
                    "2시간 소요",
                    "혼잡"
                )
            )
            add(
                ScheduleItemData(
                    4,
                    "14.3km",
                    "제주 무지개 하우스",
                    "350km",
                    "숙소",
                    null,
                    null
                )
            )
        }
        scheduleItemAdapter.notifyDataSetChanged()
    }


}