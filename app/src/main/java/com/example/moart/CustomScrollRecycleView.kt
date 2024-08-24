package com.example.moart

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import kotlin.math.abs


class CustomScrollRecycleView : RecyclerView, OnItemTouchListener {
    private var gestureDetector: GestureDetector? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context) {
        this.gestureDetector = GestureDetector(context, OnGestureListener(this))
        addOnItemTouchListener(this)
    }

    class OnGestureListener(private val recyclerView: CustomScrollRecycleView) :
        GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            motionEvent: MotionEvent?,
            motionEvent2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            val abs = abs(distanceY.toDouble()).toFloat()
            val abs2 = abs(distanceX.toDouble()).toFloat()
            val recyclerView = this.recyclerView
            if (abs > abs2) {
                recyclerView.parent.requestDisallowInterceptTouchEvent(false)
            } else if (abs(distanceX.toDouble()) > 10.0f) {
                recyclerView.parent.requestDisallowInterceptTouchEvent(true)
            }
            return super.onScroll(motionEvent, motionEvent2, distanceX, distanceY)
        }

        override fun onDown(motionEvent: MotionEvent): Boolean {
            recyclerView.parent.requestDisallowInterceptTouchEvent(true)
            return super.onDown(motionEvent)
        }
    }

    override fun fling(i2: Int, i9: Int): Boolean {
        return super.fling(i2, (i9 * 0.8).toInt())
    }
    override fun onInterceptTouchEvent(
        recyclerView: RecyclerView,
        motionEvent: MotionEvent
    ): Boolean {
        val gestureDetector = this.gestureDetector
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(motionEvent)
            return false
        }
        return false
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(
        context,
        attributeSet
    ) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, i2: Int) : super(
        context,
        attributeSet,
        i2
    ) {
        init(context)
    }

    override fun onRequestDisallowInterceptTouchEvent(z9: Boolean) {
    }

    override fun onTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent) {
    }
}