package com.example.moart

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moart.databinding.ItemCategoryMiniBinding


class CategoryMiniAdapter(context: Context, val mOnCategoryMiniClickListener: (Int) -> Unit) :
    ListAdapter<MiniCategory, CategoryMiniAdapter.CategoryMiniHolder>(diffCallBackCategoryMini) {
    private val mContext = context

    inner class CategoryMiniHolder(itemCategoryMiniBinding: ItemCategoryMiniBinding) :
        RecyclerView.ViewHolder(itemCategoryMiniBinding.getRoot()) {
        private var mBinding: ItemCategoryMiniBinding = itemCategoryMiniBinding

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindData(bVar: MiniCategory, position: Int, context: Context) {
            val name: String = bVar.title
            mBinding.tvNameCategory.text = name
            if (bVar.isSelected) {
                mBinding.tvNameCategory.setBackgroundDrawable(mContext.resources.getDrawable(R.drawable.bg_efc7b0_20))
            } else {
                mBinding.tvNameCategory.setBackgroundDrawable(mContext.resources.getDrawable(R.drawable.bg_white_20))
            }
            val appCompatTextView: AppCompatTextView = mBinding.tvNameCategory
            val allStr = context.getString(R.string.all)
            val iconMap = mapOf(
                allStr to R.drawable.ic_category_all,
                "Featured" to R.drawable.ic_cate_feature,
                "Beauty" to R.drawable.ic_cate_beauty,
                "Birthday" to R.drawable.ic_cate_birthday,
                "Blank" to R.drawable.ic_cate_blank,
                "Business" to R.drawable.ic_cate_business,
                "Classic" to R.drawable.ic_cate_classic,
                "Digital" to R.drawable.ic_cate_digital,
                "Film" to R.drawable.ic_cate_film,
                "Fitness" to R.drawable.ic_cate_fitness,
                "Food" to R.drawable.ic_cate_food,
                "Landscape" to R.drawable.ic_cate_lanscape,
                "Marketing" to R.drawable.ic_cate_maketing,
                "Minimal" to R.drawable.ic_cate_minimal,
                "Minimalist" to R.drawable.ic_cate_minimalist,
                "Mirror" to R.drawable.ic_cate_mirror,
                "News" to R.drawable.ic_cate_news,
                "Photography" to R.drawable.ic_cate_photography,
                "RealEstate" to R.drawable.ic_cate_real_estate,
                "Storytelling" to R.drawable.ic_cate_storytelling,
                "Audio" to R.drawable.ic_cate_audio,
                "Christmas" to R.drawable.ic_cate_christmas,
                "Cinema" to R.drawable.ic_cate_cinema,
                "Fashion" to R.drawable.ic_cate_fashion,
                "Music Slide" to R.drawable.ic_cate_music_slide,
                "New Year" to R.drawable.ic_cate_new_year,
                "Opener" to R.drawable.ic_cate_opener,
                "Paper Transitions" to R.drawable.ic_cate_paper_tran,
                "Reel Transitions" to R.drawable.ic_cate_reels_tran,
                "ScrapBooking" to R.drawable.ic_cate_scrap_booking,
                "Shop" to R.drawable.ic_cate_shop,
                "ShowLight" to R.drawable.ic_cate_showlight,
                "Social" to R.drawable.ic_cate_social,
                "Sport" to R.drawable.ic_cate_sport,
                "Typography" to R.drawable.ic_cate_typography,
                "Glitch" to R.drawable.ic_cate_glitch,
                "Party" to R.drawable.ic_cate_party,
                "Baby" to R.drawable.ic_cate_baby,
                "Travel" to R.drawable.ic_cate_travel,
                "Mother's Day" to R.drawable.ic_cate_motherday,
                "Fitness & Yoga" to R.drawable.ic_cate_fitness_yoga,
                "Trending Sound" to R.drawable.ic_cate_trending_sound // Đặt cuối cùng nếu không tìm thấy chuỗi nào
            )

            val icon = iconMap[name] ?: R.drawable.ic_cate_trending_sound
            appCompatTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(
                context.getDrawable(icon), null as Drawable?, null as Drawable?, null as Drawable?
            )
            mBinding.tvNameCategory.setOnClickListener {
                mOnCategoryMiniClickListener(position)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i2: Int): CategoryMiniHolder {
        return CategoryMiniHolder(
            ItemCategoryMiniBinding.inflate(
                LayoutInflater.from(viewGroup.context), viewGroup, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryMiniHolder, position: Int) {
        holder.bindData(getItem(position), position, this.mContext)
    }

    companion object {
        val diffCallBackCategoryMini = object : DiffUtil.ItemCallback<MiniCategory>() {
            override fun areItemsTheSame(oldItem: MiniCategory, newItem: MiniCategory): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MiniCategory, newItem: MiniCategory): Boolean {
                return oldItem == newItem
            }
        }
    }
}