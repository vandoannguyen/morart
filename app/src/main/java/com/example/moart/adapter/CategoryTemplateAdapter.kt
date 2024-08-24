package com.example.moart.adapter

import TemplateAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moart.Category
import com.example.moart.DesignItem
import com.example.moart.R
import com.example.moart.databinding.ItemCategoryTemplateBinding


class CategoryTemplateAdapter(
    context: Context,
    var onChooseTemplate: (Int) -> Unit
) : ListAdapter<Category, CategoryTemplateAdapter.CategoryTemplateViewHolder?>(
    diffCallbackTheme
) {
    private var heightItem: Int = 0
    private var isHasData: Boolean = false
    private var mRecyclerView: RecyclerView? = null
    private val recyclerViewStates: SparseArray<Parcelable?> = SparseArray()

    inner class CategoryTemplateViewHolder(itemCategoryTemplateBinding: ItemCategoryTemplateBinding) :
        RecyclerView.ViewHolder(itemCategoryTemplateBinding.getRoot()) {
        private var firstVisibleItem = 0
        private var lastVisibleItem = 0
        var mBinding: ItemCategoryTemplateBinding = itemCategoryTemplateBinding
        private var mDesignSampleAdapter: DesignSampleAdapter? = null
        private var mTemplateAdapter: TemplateAdapter? = null

        inner class OnScrollListener : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val findFirstVisibleItemPosition: Int
                val findLastVisibleItemPosition: Int
                super.onScrolled(recyclerView, dx, dy)
                val categoryTemplateViewHolder = this@CategoryTemplateViewHolder
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null) {
                    findFirstVisibleItemPosition =
                        linearLayoutManager.findFirstVisibleItemPosition()
                    findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition()
                } else {
                    findFirstVisibleItemPosition = 0
                    findLastVisibleItemPosition = 0
                }
                if (findFirstVisibleItemPosition != categoryTemplateViewHolder.firstVisibleItem || findLastVisibleItemPosition != categoryTemplateViewHolder.lastVisibleItem) {
                    categoryTemplateViewHolder.firstVisibleItem = findFirstVisibleItemPosition
                    categoryTemplateViewHolder.lastVisibleItem = findLastVisibleItemPosition
                }
            }
        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindData(category: Category, position: Int) {
            val context: Context = mBinding.getRoot().context
            if (position > 0) {
                val arrayList: ArrayList<Int> = ArrayList(category.images)
                if (position == 1) {
                    mBinding.tvNameCategory.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.colorTextTab
                        )
                    )
                } else {
                    mBinding.tvNameCategory.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.colorTextBlack
                        )
                    )
                }
                mBinding.tvNameCategory.text = category.title
                if (this.mTemplateAdapter == null) {
                    if (category.title.contains("Landscape")) {
                        this.mTemplateAdapter = TemplateAdapter(
                            context,
                            position,
                            true,
                        ) { catPosition, position ->
                            handleClickTemplate(catPosition, position)
                        }
                        (mBinding.rcvTemplate.layoutParams as MarginLayoutParams).height =
                            (context.resources.displayMetrics.widthPixels * 0.55).toInt()
                    } else {
                        mTemplateAdapter = TemplateAdapter(
                            context, position, false
                        ) { catPosition, position ->
                            handleClickTemplate(catPosition, position)
                        }
                        (mBinding.rcvTemplate.layoutParams as MarginLayoutParams).height =
                            (context.resources.displayMetrics.widthPixels * 0.8f).toInt()
                    }
                    mBinding.rcvTemplate.setAdapter(this.mTemplateAdapter)
                    mBinding.rcvTemplate.addOnScrollListener(OnScrollListener())
                }
                if (arrayList.size > 7) {
                    mBinding.tvShowAll.visibility = View.VISIBLE
                } else {
                    mBinding.tvShowAll.visibility = View.GONE
                }
                mTemplateAdapter!!.setPositionCategory(position)
                mTemplateAdapter!!.submitList(arrayList)

                if (recyclerViewStates[position] != null && mBinding.rcvTemplate.layoutManager != null) {
                    mBinding.rcvTemplate.layoutManager!!.onRestoreInstanceState(
                        recyclerViewStates[position]
                    )
                }
                if (position == currentList.size - 1) {
                    mBinding.tvTurnOnInternet.visibility = View.VISIBLE
                } else {
                    mBinding.tvTurnOnInternet.visibility = View.GONE
                }
                return
            }
            if (position == 0) {
                mBinding.tvShowAll.visibility = View.GONE
                val context2: Context = mBinding.getRoot().context
                val arrayList2: ArrayList<DesignItem> = arrayListOf(
                    DesignItem(
                        "TYPE_DESIGN_REEL",
                        context2.getString(R.string.reel),
                        context2.resources.getDrawable(R.drawable.ic_instagram_design)
                    ),
                    DesignItem(
                        "TYPE_DESIGN_STORY",
                        context2.getString(R.string.story),
                        context2.resources.getDrawable(R.drawable.ic_image_design)
                    ),
                    DesignItem(
                        "TYPE_DESIGN_STORY",
                        context2.getString(R.string.story),
                        context2.resources.getDrawable(R.drawable.ic_image_design)
                    ),
                    DesignItem(
                        "TYPE_DESIGN_TIKTOK",
                        context2.getString(R.string.tiktok),
                        context2.resources.getDrawable(R.drawable.ic_tiktok_design)
                    ),
                    DesignItem(
                        "TYPE_DESIGN_YOUTUBE",
                        context2.getString(R.string.you_tube),
                        context2.resources.getDrawable(R.drawable.ic_youtube_design)
                    ),
                    DesignItem(
                        "TYPE_DESIGN_FACEBOOK",
                        context2.getString(R.string.facebook),
                        context2.resources.getDrawable(R.drawable.ic_facebook_design)
                    ),
                )
                mBinding.tvNameCategory.text = context.getString(R.string.choose_a_design)
                if (mDesignSampleAdapter == null) {
                    mDesignSampleAdapter = DesignSampleAdapter(
                        context, position,
                    )
                    mBinding.rcvTemplate.setAdapter(mDesignSampleAdapter)
                }
                mBinding.tvTurnOnInternet.visibility = View.GONE
                mDesignSampleAdapter!!.submitList(arrayList2)
            }
        }

        val templateAdapter: TemplateAdapter?
            get() = this.mTemplateAdapter

        fun removeAllWhenRecycle() {
            val templateAdapter: TemplateAdapter? = this.mTemplateAdapter
            if (templateAdapter != null) {
                try {
                    templateAdapter.submitList(null)
                } catch (unused: IllegalStateException) {
                }
            }
        }
    }

    private fun handleClickTemplate(catPosition: Int, position: Int) {
        onChooseTemplate(getItem(catPosition).images[position])
    }

    init {
        this.isHasData = false
        this.heightItem = (context.resources.displayMetrics.widthPixels * 0.84).toInt()
    }

    override fun getItemId(i2: Int): Long {
        return i2.toLong()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.mRecyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun getItem(index: Int): Category {
        return currentList[index]
    }

    override fun onBindViewHolder(
        categoryTemplateViewHolder: CategoryTemplateViewHolder, position: Int
    ) {
        categoryTemplateViewHolder.bindData(getItem(position), position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i2: Int): CategoryTemplateViewHolder {
        val inflate: ItemCategoryTemplateBinding =
            ItemCategoryTemplateBinding.inflate(LayoutInflater.from(viewGroup.context))
        val layoutParams = inflate.rcvTemplate.getLayoutParams() as ConstraintLayout.LayoutParams
        if (i2 == 0) {
            (layoutParams as MarginLayoutParams).height = (this.heightItem * 0.54f).toInt()
        } else {
            (layoutParams as MarginLayoutParams).height = this.heightItem
        }
        inflate.rcvTemplate.setHasFixedSize(true)
        inflate.rcvTemplate.setLayoutManager(
            LinearLayoutManager(
                viewGroup.context, RecyclerView.HORIZONTAL, false
            )
        )
        return CategoryTemplateViewHolder(inflate)
    }

    override fun onViewRecycled(categoryTemplateViewHolder: CategoryTemplateViewHolder) {
        if (categoryTemplateViewHolder.mBinding.rcvTemplate.layoutManager != null) {
            recyclerViewStates.put(
                categoryTemplateViewHolder.layoutPosition,
                categoryTemplateViewHolder.mBinding.rcvTemplate.layoutManager!!.onSaveInstanceState()
            )
        }
        categoryTemplateViewHolder.removeAllWhenRecycle()
        super.onViewRecycled(categoryTemplateViewHolder)
    }

    companion object {
        val diffCallbackTheme = object : DiffUtil.ItemCallback<Category>() {
            override fun areContentsTheSame(bVar: Category, bVar2: Category): Boolean {
                return bVar == bVar2
            }

            override fun areItemsTheSame(bVar: Category, bVar2: Category): Boolean {
                return bVar == bVar2
            }
        }
    }
}