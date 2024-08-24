package com.example.moart

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


/* loaded from: classes2.dex */
class CustomViewPager : ViewPager {
    private val isPagingEnabled: Boolean

    constructor(context: Context?) : super(context!!) {
        this.isPagingEnabled = false
    }

    override fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        return false
    }

    constructor(context: Context?, attributeSet: AttributeSet?) : super(
        context!!, attributeSet
    ) {
        this.isPagingEnabled = false
    }
}