package com.duramas.simulaciongobiernopueblo.animacion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duramas.simulaciongobiernopueblo.databinding.ActivityAnimacionBinding

class ActivityAnimacion : AppCompatActivity() {

    private lateinit var binding: ActivityAnimacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}