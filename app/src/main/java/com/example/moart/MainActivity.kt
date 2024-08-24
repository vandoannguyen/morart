package com.example.moart

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.moart.databinding.ActivityMainBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


open class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var currentPage = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnApplyWindowInsetsListener { _, windowInsets ->
            val systemWindowInsetTop = windowInsets.systemWindowInsetTop
            if (systemWindowInsetTop > 0) {
                binding.root.setPadding(0, systemWindowInsetTop, 0, 0)
            }
            windowInsets
        }
        initViewpager()
        binding.bottomNav.setOnApplyWindowInsetsListener { view, windowInsets ->
            view.setPadding(0, 0, 0, 0);
            windowInsets
        }
        setUpViewPager()
    }

    private fun setUpViewPager() {
        binding.bottomNav.setOnItemSelectedListener { menuItem ->
            val itemId = menuItem.itemId
            if (itemId != R.id.homeFragment) {
                if (itemId != R.id.myStoriesFragment) {
                    if (itemId == R.id.settingFragment) {
                        this.currentPage = 2
                        binding.vpMain.post {
                            binding.vpMain.setCurrentItem(2)
                            binding.tvTitle.text = resources.getString(R.string.settings)
                        }
                    }
                } else {
                    this.currentPage = 1
                    binding.tvTitle.post {
                        binding.tvTitle.text = resources.getString(R.string.my_projects)
                    }
                    selectMyStories(true)
                }
            } else {
                if (this.currentPage != 0) {
                    this.currentPage = 0
                    binding.vpMain.post {
                        binding.vpMain.setCurrentItem(0)
                        binding.tvTitle.text = resources.getString(R.string.app_name)
                    }
                }
            }
            true
        }
        for (i2 in 0 until binding.bottomNav.menu.size()) {
            binding.bottomNav.findViewById<View>(
                binding.bottomNav.menu.getItem(i2).itemId
            )
        }
    }

    private fun initViewpager() {
        binding.vpMain.setOffscreenPageLimit(2)
        binding.vpMain.setAdapter(
            MainPagerAdapter(
                supportFragmentManager
            )
        )
        binding.vpMain.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float, positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    private fun selectMyStories(z9: Boolean) {
        this.currentPage = 1
        binding.vpMain.post {
            binding.vpMain.setCurrentItem(1)
            binding.tvTitle.text = resources.getString(R.string.my_stories)
        }
        if (!z9) {
            binding.bottomNav.setSelectedItemId(R.id.myStoriesFragment)
        }
    }
}