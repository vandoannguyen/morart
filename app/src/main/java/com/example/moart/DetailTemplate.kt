package com.example.moart

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import androidx.core.view.WindowInsetsCompat
import com.example.moart.databinding.ActivityDetailTemplateBinding

class DetailTemplate : AppCompatActivity() {
    lateinit var binding:ActivityDetailTemplateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTemplateBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val image = intent.getIntExtra("icon", -1)
        if(image!= -1){
            binding.imgView.setImageResource(image)
        }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}