package com.example.moart

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SliderPagerAdapter(i2: Int, fm: FragmentManager?) :
    FragmentPagerAdapter(fm!!) {
    private val mCount: Int = i2
    override fun getCount(): Int {
        return this.mCount
    }

    override fun getItem(index: Int): Fragment {
        return SliderFragment(index)
    }
}