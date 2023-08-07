package com.duramas.simulaciongobiernopueblo.raspberry

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.duramas.simulaciongobiernopueblo.databinding.ActivityRaspberryBinding

class RaspberryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRaspberryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRaspberryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBlue.setOnClickListener({
            val conect = conectBluetooth()

            Toast.makeText(applicationContext,conect.conectarImpresora(),Toast.LENGTH_SHORT).show()

            conect.abrirConexion()
            conect.impimir("1 ")

        })

        binding.buttonGreen.setOnClickListener({
            val conect = conectBluetooth()

            Toast.makeText(applicationContext,conect.conectarImpresora(),Toast.LENGTH_SHORT).show()

            conect.abrirConexion()
            conect.impimir("2 ")

        })

        binding.buttonRed.setOnClickListener({
            val conect = conectBluetooth()

            Toast.makeText(applicationContext,conect.conectarImpresora(),Toast.LENGTH_SHORT).show()

            conect.abrirConexion()
            conect.impimir("3 ")

        })

    }

}