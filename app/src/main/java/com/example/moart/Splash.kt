package com.example.moart

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener
import com.example.moart.databinding.ActivitySplashBinding


class Splash : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root. setOnApplyWindowInsetsListener { _, windowInsets ->
            val systemWindowInsetTop = windowInsets.systemWindowInsetTop
            if (systemWindowInsetTop > 0) {
                binding.root.setPadding(0,systemWindowInsetTop, 0, 0)
            }
            windowInsets
        }
        enableEdgeToEdge()

        Handler(mainLooper).postDelayed({
            val intent = Intent(this, Slider::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }, 3000)
    }
}