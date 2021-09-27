package com.example.examplemvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examplemvvm.databinding.ActivityMainBinding
import com.example.examplemvvm.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, HomeFragment.newInstance())
                .commitNow()
        }
    }
}