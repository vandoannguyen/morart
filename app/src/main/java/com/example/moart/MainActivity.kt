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
    lateinit var tinyDB: TinyDB
    private var currentPage = 0
    private var isPro: Boolean = false
    private val loaded = false
    private val lastClickTime: Long = 0
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
        tinyDB = TinyDB(this)
        if (savedInstanceState != null) {
            val i2: Int = savedInstanceState.getInt("CURRENT_POSITION_BNV", 0)
            this.currentPage = i2
            setTitle(i2)
//            this.mainViewModel.currentFragment = this.currentPage
        }
        if (tinyDB.getBool("IS_HOME")) {
            setPageState(isHome = true, isMyStory = false, isSetting = false)
        } else if (tinyDB.getBool("IS_MY_STORY")) {
            setPageState(isHome = false, isMyStory = true, isSetting = true)
        } else if (tinyDB.getBool("IS_SETTING")) {
            setPageState(isHome = false, isMyStory = false, isSetting = true)
        }
        changeHeightAppbar(isPro)
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
//                            this.mainViewModel.currentFragment = 2
                            binding.tvTitle.text = resources.getString(R.string.settings)
                            EventBus.getDefault().post(MessageEvent("TO_ANOTHER_HOME_PAGE"))
                        }
                    }
                } else {
                    this.currentPage = 1
                    binding.tvTitle.post {
                        binding.tvTitle.text = resources.getString(R.string.my_projects)
                    }
                    selectMyStories(true)
//                    this.mainViewModel.currentFragment = 1
                    EventBus.getDefault().post(MessageEvent("TO_ANOTHER_HOME_PAGE"))
                }
            } else {
                if (this.currentPage != 0) {
                    this.currentPage = 0
                    binding.vpMain.post {
                        binding.vpMain.setCurrentItem(0)
                        binding.tvTitle.text = resources.getString(R.string.app_name)
                    }
                } else {
//                    this.mainViewModel.scrollToFirst.setValue(java.lang.Boolean.TRUE)
                }
//                this.mainViewModel.currentFragment = 0
                EventBus.getDefault().post(MessageEvent("TO_HOME_PAGE"))
            }
            true
        }
        for (i2 in 0 until binding.bottomNav.menu.size()) {
            binding.bottomNav.findViewById<View>(
                binding.bottomNav.menu.getItem(i2).itemId
            ).setOnLongClickListener {
//                MainFragment.b(view);
                true
            }
        }
    }

    private fun changeHeightAppbar(isPro: Boolean) {
        binding.layoutTryPlusBanner.rootView.post {
            ///TODO
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
                when (position) {
                    0 -> {
                        setPageState(isHome = true, isMyStory = false, isSetting = false)
                    }

                    1 -> {
                        setPageState(isHome = false, isMyStory = true, isSetting = false)
                    }

                    2 -> {
                        setPageState(isHome = false, isMyStory = false, isSetting = true)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })
    }

    fun setPageState(isHome: Boolean, isMyStory: Boolean, isSetting: Boolean) {
        this.tinyDB.setBool("IS_HOME", isHome)
        this.tinyDB.setBool("IS_MY_STORY", isMyStory)
        this.tinyDB.setBool("IS_SETTING", isSetting)
    }

    fun selectMyStories(z9: Boolean) {
        this.currentPage = 1
//        this.mainViewModel.currentFragment = 1
        EventBus.getDefault().post(MessageEvent("REQUEST_LIST_STORY"))
        binding.vpMain.post {
            binding.vpMain.setCurrentItem(1)
            binding.tvTitle.text = resources.getString(R.string.my_stories)
        }
        if (!z9) {
            binding.bottomNav.setSelectedItemId(R.id.myStoriesFragment)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: MessageEvent) {
        val str: String = event.message
        when (str) {
            "EVENT_CHANGE_PRO" -> {
//                d9.a.f21144a.b("HaiPd onMessageEvent: %s", this.isPro)
                changeHeightAppbar(this.isPro)
                return
            }

            "RATE_APP" -> {
                rate(false)
                return
            }

            "DENY_PERMISSION" -> {
                binding.bottomNav.setSelectedItemId(R.id.homeFragment)
                this.currentPage = 0
                return
            }

            else -> return
        }
    }

    private fun rate(b: Boolean) {
    }
}