package com.duramas.simulaciongobiernopueblo.raspberry

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.duramas.simulaciongobiernopueblo.databinding.ActivityRaspberryBinding

class RaspberryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaspberryBinding
    val conect = conectBluetooth()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityRaspberryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBlue.setOnClickListener({

            conect.impimir("1 ")
        })

        binding.buttonGreen.setOnClickListener({

            conect.impimir("2 ")

        })

        binding.buttonRed.setOnClickListener({
            //Toast.makeText(applicationContext,conect.conectarImpresora(),Toast.LENGTH_SHORT).show()
            conect.impimir("3 ")

        })

        binding.textViewState.text = conect.state

    }

    override fun onDestroy() {
        super.onDestroy()
        conect.close()
    }



}