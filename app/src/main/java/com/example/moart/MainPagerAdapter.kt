package com.example.moart

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
class MainPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(i2: Int): Fragment {
        if (i2 != 0) {
            if (i2 != 1) {
                return SettingFragment()
            }
            return MyStoriesFragment()
        }
        return HomeFragment()
    }
}