package com.example.moart

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moart.adapter.CategoryTemplateAdapter
import com.example.moart.adapter.OnDesignSelectedListener
import com.example.moart.adapter.OnTemplateAdapterListener
import com.example.moart.databinding.FragmentHomeBinding
import kotlin.random.Random


class HomeFragment : Fragment(), OnDesignSelectedListener, OnTemplateAdapterListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapterCategory: CategoryTemplateAdapter
    private lateinit var mCategoryMiniAdapter: CategoryMiniAdapter
    private lateinit var miniCategory: List<MiniCategory>
    override fun onCreate(savedInstanceState: Bundle?) {
        miniCategory = Common.miniCategories
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initCategoryRecyclerView()
        initMiniCategoryRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMiniCategoryRecyclerView() {
        binding.rvCategory.layoutManager = LinearLayoutManager(
            context, RecyclerView.HORIZONTAL, false
        )
        binding.rvCategory.setHasFixedSize(true)
        mCategoryMiniAdapter = CategoryMiniAdapter(
            requireActivity(),
        ) {
            setupCategoryPosition(it, false)
            if (binding.rcvCategoryTemplate.layoutManager != null) {
                (binding.rcvCategoryTemplate.layoutManager as LinearLayoutManager?)!!.scrollToPositionWithOffset(
                    it, 0
                )
            }
        }
        binding.rvCategory.adapter = mCategoryMiniAdapter
        mCategoryMiniAdapter.submitList(miniCategory) {
            mCategoryMiniAdapter.notifyDataSetChanged()
        }
    }

    private fun initCategoryRecyclerView() {
        binding.rcvCategoryTemplate.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
        )
        binding.rcvCategoryTemplate.setHasFixedSize(true)
        adapterCategory = CategoryTemplateAdapter(
            requireActivity(),
            this,
            this,
        ) {
            val intent = Intent(requireActivity(), DetailTemplate::class.java)
            intent.putExtra("icon", it)
            startActivity(intent)
        }
        binding.rcvCategoryTemplate.adapter = adapterCategory
        binding.rcvCategoryTemplate.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                val lastItemPosition: Int
                val firstItemPosition: Int
                val isListEmpty: Boolean
                super.onScrollStateChanged(recyclerView, state)
                if (state == SCROLL_STATE_IDLE) {
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                    if (linearLayoutManager != null) {
                        firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                        lastItemPosition = linearLayoutManager.findLastVisibleItemPosition()
                    } else {
                        lastItemPosition = 0
                        firstItemPosition = 0
                    }
                    val visiblePosition = firstItemPosition + lastItemPosition
                    var position = visiblePosition / 2
                    if (visiblePosition % 2 == 1 && position != 0) {
                        position++
                    }
                    if (position < adapterCategory.itemCount) {
                        setupCategoryPosition(position, true)
                        if (position == 1) {
                            binding.rvCategory.scrollToPosition(0)
                        } else {
                            binding.rvCategory.scrollToPosition(position)
                        }
                    }
                }
            }

        })
        binding.rcvCategoryTemplate.post { }
        handleGenCategory()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleGenCategory() {
        val list = arrayListOf<Category>()
        val imageResources = arrayOf(
            R.drawable.temp_1,
            R.drawable.temp_2,
            R.drawable.temp_3,
            R.drawable.temp_4,
            R.drawable.temp_5,
            R.drawable.temp_6,
            R.drawable.temp_7,
            R.drawable.img_template_bill4,
            R.drawable.img_template_bill1,
            R.drawable.img_template_bill2,
            R.drawable.img_template_bill3,
            R.drawable.img_template_bill4,
            R.drawable.img_template_bill5,
            R.drawable.img_template_bill6,
            R.drawable.img_template_bill7,
            R.drawable.img_template_bill8,
            R.drawable.img_template_bill9,
        )
        val randomImages = Array(imageResources.size) {
            imageResources[Random.nextInt(imageResources.size)]
        }
        Common.miniCategories.forEach { item ->
            list.add(Category(title = item.title, images = randomImages.toList()))
        }
        adapterCategory.submitList(list) {
            adapterCategory.notifyDataSetChanged()
        }
    }

    private fun setupCategoryPosition(position: Int, b: Boolean) {
        val currentList: List<MiniCategory> = this.mCategoryMiniAdapter.currentList
        var index = 0
        while (true) {
            if (index < currentList.size) {
                if (currentList[index].isSelected) {
                    break
                } else {
                    index++
                }
            } else {
                index = -1
                break
            }
        }
        if (position == -1) {
            setUpListCategory(0, index, currentList, b)
        } else setUpListCategory(position, index, currentList, b)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpListCategory(
        position: Int, index: Int, currentList: List<MiniCategory>, b: Boolean
    ) {
        if (index >= 0 && position >= 0) {
            if (index == position && !b && index < currentList.size) {
                if (currentList[index].isSelected) {
                    binding.rvCategory.scrollToPosition(0)
                    binding.rcvCategoryTemplate.post {}
                }
                currentList[index].isSelected = !currentList[index].isSelected
                currentList[0].isSelected = true
                this.mCategoryMiniAdapter.notifyItemChanged(index)
                this.mCategoryMiniAdapter.notifyItemChanged(0)
                return
            }
            if ((!b || index != position) && (index < currentList.size) && (position < currentList.size)) {
                currentList[index].isSelected = !currentList[index].isSelected
                currentList[position].isSelected = !currentList[position].isSelected
                this.mCategoryMiniAdapter.notifyDataSetChanged()
                return
            }
            return
        }
        if (position < currentList.size && position != -1) {
            currentList[position].isSelected = !currentList[position].isSelected
            this.mCategoryMiniAdapter.notifyItemChanged(position)
        }
    }

    override fun onDesignSelectedListener(i2: Int, i9: Int, item: DesignItem) {
    }

    override fun onClickLoadMoreTemplates() {
    }

    override fun onClickShowAll(str: String?, i2: Int, z9: Boolean) {

    }

    override fun onRepeatAudio() {

    }

    override fun onStopAudio() {

    }

}