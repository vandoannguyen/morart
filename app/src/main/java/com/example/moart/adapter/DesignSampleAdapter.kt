package com.example.moart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moart.DesignItem
import com.example.moart.databinding.ItemDesignBinding

class DesignSampleAdapter(
    context: Context,
    private val positionCategory: Int,
) : ListAdapter<DesignItem, RecyclerView.ViewHolder?>(diffCallbackDesignSample) {
    private val height: Int
    private val width: Int

    inner class ItemDesignHolder(itemDesignBinding: ItemDesignBinding) :
        RecyclerView.ViewHolder(itemDesignBinding.getRoot()) {
        private var mBinding: ItemDesignBinding = itemDesignBinding

        fun bindData(item: DesignItem, position: Int, context: Context?) {
            mBinding.tvDesignItemType.text = item.name
            mBinding.ivDesignIcon.setImageDrawable(item.icon)
            val layoutParams: ViewGroup.LayoutParams = mBinding.viewBgDesign.layoutParams
            val str: String = item.type
            str.javaClass
            var c10 = 65535.toChar()
            when (item.type) {
                "TYPE_DESIGN_SHORT" -> {
                    layoutParams.height = height
                }

                "TYPE_DESIGN_STORY" -> {
                    layoutParams.height = height
                }

                "TYPE_DESIGN_FACEBOOK" -> {
                    layoutParams.height = height / 3
                }

                "TYPE_DESIGN_YOUTUBE" -> {
                    layoutParams.height = height / 3
                }

                "TYPE_DESIGN_POST" -> {
                    layoutParams.height = (height * 2) / 3
                }

                "TYPE_DESIGN_REEL" -> {
                    layoutParams.height = height
                }

                "TYPE_DESIGN_TIKTOK" -> {
                    layoutParams.height = height
                }
            }
            mBinding.getRoot().setPadding(0, 0, 0, 0)
            mBinding.getRoot().requestLayout()
            mBinding.viewBgDesign.requestLayout()
        }
    }

    init {
        this.width = (context.resources.displayMetrics.widthPixels * 0.2f).toInt()
        this.height = ((width * 16) / 9.0f).toInt()
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as ItemDesignHolder).bindData(
            getItem(position), position, viewHolder.itemView.context
        )
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i2: Int): RecyclerView.ViewHolder {
        val inflate: ItemDesignBinding =
            ItemDesignBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        val layoutParams: ViewGroup.LayoutParams = inflate.getRoot().layoutParams
        layoutParams.width = this.width
        layoutParams.height = this.height
        inflate.getRoot().requestLayout()
        return ItemDesignHolder(inflate)
    }

    companion object {
        val diffCallbackDesignSample: DiffUtil.ItemCallback<DesignItem> =
            object : DiffUtil.ItemCallback<DesignItem>() {
                override fun areItemsTheSame(oldItem: DesignItem, newItem: DesignItem): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: DesignItem, newItem: DesignItem): Boolean {
                    return oldItem.type == newItem.type
                }

            }
    }
}