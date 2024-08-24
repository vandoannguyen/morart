package com.example.moart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moart.databinding.FragmentMyStoriesBinding
class MyStoriesFragment : Fragment() {
    private lateinit var binding:FragmentMyStoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }
}