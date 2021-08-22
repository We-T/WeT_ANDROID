package com.wetour.we_t.ui.makeSchedule

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager


class CustomViewPager : ViewPager {
    private var enables = false
    private var isPagingEnabled = true
    private var mCurrentPagePosition = 0

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context) : super(context)


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (enables) return super.onInterceptTouchEvent(ev)
        else {
            if (ev!!.action == MotionEvent.ACTION_MOVE) {

            } else if (super.onInterceptTouchEvent(ev)) super.onTouchEvent(ev)
        }
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (enables) return super.onTouchEvent(ev)
        else return ev!!.action != MotionEvent.ACTION_MOVE && super.onTouchEvent(ev)
    }

    fun setEnable(enable: Boolean) {
        this.enables = enable
    }

    fun setPagingEnabled(b: Boolean) {
        this.isPagingEnabled = b
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var heightMeasureSpec = heightMeasureSpec
        try {
            val child: View? = getChildAt(mCurrentPagePosition)
            if (child != null) {
                child.measure(
                    widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                )
                val h: Int = child.getMeasuredHeight()
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun reMeasureCurrentPage(position: Int) {
        mCurrentPagePosition = position
        requestLayout()
    }

}