import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moart.R
import com.example.moart.databinding.ItemTemplateBinding


class TemplateAdapter(
    context: Context,
    private var positionCategory: Int,
    z10: Boolean,
    val onClickItem: (Int, Int) -> Unit,
) :
    ListAdapter<Int, TemplateAdapter.ItemTemplateHolder>(
        DiffItemCallBack()
    ) {
    private var height = 0
    private var isPaddingBot: Boolean = false
    var width: Int = 0

    inner class ItemTemplateHolder(itemTemplateBinding: ItemTemplateBinding) :
        RecyclerView.ViewHolder(itemTemplateBinding.getRoot()) {
        private var mBinding: ItemTemplateBinding = itemTemplateBinding
        fun bindData(template: Int, index: Int, context: Context) {
            mBinding.ivIsPro.visibility = View.VISIBLE
            mBinding.ivIsPro.setImageResource(R.drawable.ic_pro_new)
            mBinding.ivReward.visibility = View.GONE
            mBinding.imgNew.visibility = View.VISIBLE
            mBinding.ivVideoTemplate.visibility = View.VISIBLE
            mBinding.templateView.setImageResource(template)
            mBinding.root.setOnClickListener {
                onClickItem(positionCategory, index)
            }
        }
    }

    init {
        if (z10) {
            val d10 = (context.resources.displayMetrics.widthPixels * 0.76f).toInt()
            this.width = d10
            this.height = (d10 * 0.5625f).toInt()
        } else {
            val d11 = (context.resources.displayMetrics.widthPixels * 0.38f).toInt()
            this.width = d11
            this.height = (d11 * 1.8f).toInt()
        }
    }

    override fun onBindViewHolder(viewHolder: ItemTemplateHolder, index: Int) {
        viewHolder.bindData(
            getItem(index), index, viewHolder.itemView.context
        )

        if (this.isPaddingBot) {
            val layoutParams = viewHolder.itemView.layoutParams as RecyclerView.LayoutParams
            if (itemCount % 2 != 0) {
                if (index == itemCount - 1) {
                    (layoutParams as MarginLayoutParams).bottomMargin =
                        viewHolder.itemView.context.resources.getDimension(R.dimen._100sdp).toInt()
                    return
                } else {
                    (layoutParams as MarginLayoutParams).bottomMargin = 0
                    return
                }
            }
            if (index != itemCount - 1 && index != itemCount - 2) {
                (layoutParams as MarginLayoutParams).bottomMargin = 0
            } else {
                (layoutParams as MarginLayoutParams).bottomMargin =
                    viewHolder.itemView.context.resources.getDimension(R.dimen._100sdp).toInt()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemTemplateHolder {

        val inflate: ItemTemplateBinding = ItemTemplateBinding.inflate(
            LayoutInflater.from(viewGroup.context), viewGroup, false
        )
        val layoutParams: ViewGroup.LayoutParams = inflate.getRoot().getLayoutParams()
        layoutParams.width = this.width
        layoutParams.height = this.height
        return ItemTemplateHolder(inflate)
    }

    fun setPositionCategory(position: Int) {
        this.positionCategory = position
    }

    override fun getItem(index: Int): Int {
        return currentList[index]
    }

    class DiffItemCallBack : DiffUtil.ItemCallback<Int>() {
        override fun areContentsTheSame(template: Int, template2: Int): Boolean {
            return template == template2
        }

        override fun areItemsTheSame(template: Int, template2: Int): Boolean {
            return template == template2
        }
    }
}