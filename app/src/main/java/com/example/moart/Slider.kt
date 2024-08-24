package com.example.moart

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.moart.databinding.ActivitySliderBinding


class Slider : AppCompatActivity() {
    companion object {
        private val countPage: Int = 3
    }

    private val mBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            finish();
        }

    }
    private var mSliderPagerAdapter: SliderPagerAdapter? = null

    lateinit var binding: ActivitySliderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressedDispatcher.addCallback(this, this.mBackPressedCallback)
        enableEdgeToEdge()
        binding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnApplyWindowInsetsListener { _, windowInsets ->
            val systemWindowInsetTop = windowInsets.systemWindowInsetTop
            if (systemWindowInsetTop > 0) {
                binding.root.setPadding(0, systemWindowInsetTop, 0, 0)
            }
            windowInsets
        }

        setUpPager()
        binding.ivNext.setOnClickListener {
            nextPage()
        }
    }

    private fun setUpPager() {
        val sliderPagerAdapter = SliderPagerAdapter(3, supportFragmentManager)
        this.mSliderPagerAdapter = sliderPagerAdapter
        binding.pagerIntroSlider.setAdapter(sliderPagerAdapter)
        binding.tabs.setupWithViewPager(binding.pagerIntroSlider)
    }

    private fun nextPage() {
        val currentItem: Int = binding.pagerIntroSlider.currentItem
        if (currentItem == mSliderPagerAdapter!!.count - 1) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            return
        }
        if (currentItem < mSliderPagerAdapter!!.count) {
            binding.pagerIntroSlider.setCurrentItem(
                currentItem + 1, true
            )
        }
    }

}