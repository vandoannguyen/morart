package com.example.moart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.moart.databinding.FragmentSliderBinding

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SliderFragment(position: Int) : Fragment() {
    private var position = 0

    init {
        this.position = position
    }

    lateinit var binding: FragmentSliderBinding
    private val listImage: ArrayList<Int> = arrayListOf(
        R.drawable.iv_splash1, R.drawable.iv_splash2, R.drawable.iv_splash3
    )

    private fun listTitle(): ArrayList<String> {
        val string = getString(R.string.create_animated_social_content_for_your_brand)
        val string2 = getString(R.string.choose_from_hundreds_of_animated_templates)
        val string3 = getString(R.string.create_tons_of_expressive_text_effects)
        return arrayListOf(string, string2, string3)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSliderBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        if (this.position == 0) {
            binding.imageView2.visibility = View.VISIBLE
            binding.imageView.visibility = View.GONE
        } else {
            binding.imageView2.visibility = View.GONE
            binding.imageView.visibility = View.VISIBLE
        }
        val i2 = this.position
        if (i2 != 0) {
            if (i2 != 1) {
                binding.container.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_splash
                    )
                )
            } else {
                binding.container.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_splash
                    )
                )
            }
        } else {
            binding.container.setBackgroundResource(R.drawable.iv_splash1)
        }
        val appCompatTextView: AppCompatTextView = binding.tvTitle
        appCompatTextView.text = listTitle()[position]
        val appCompatImageView: AppCompatImageView = binding.imageView
        val num = listImage[position]
        appCompatImageView.setImageResource(num)
    }
}