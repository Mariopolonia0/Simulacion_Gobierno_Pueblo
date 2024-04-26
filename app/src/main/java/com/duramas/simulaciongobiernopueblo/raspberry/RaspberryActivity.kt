package com.duramas.simulaciongobiernopueblo.raspberry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.duramas.simulaciongobiernopueblo.databinding.ActivityRaspberryBinding

class RaspberryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaspberryBinding

    val conect = conectBluetooth()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRaspberryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewState.text = conect.state

        binding.imageButtonForward.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    conect.impimir("1")
                }

                MotionEvent.ACTION_UP -> {
                    conect.impimir("0")
                }
            }
            return@OnTouchListener true
        })

        binding.imageButtonBack.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    conect.impimir("2")
                }

                MotionEvent.ACTION_UP -> {
                    conect.impimir("0")
                }
            }
            return@OnTouchListener true
        })

        binding.imageButtonLeft.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    conect.impimir("3")
                }

                MotionEvent.ACTION_UP -> {
                    conect.impimir("0")
                }
            }
            return@OnTouchListener true
        })

        binding.imageButtonRight.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    conect.impimir("4")
                }

                MotionEvent.ACTION_UP -> {
                    conect.impimir("0")
                }
            }
            return@OnTouchListener true
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        conect.close()
    }

}