package com.duramas.simulaciongobiernopueblo.ascensor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duramas.simulaciongobiernopueblo.databinding.ActivityAscensorBinding
import com.duramas.simulaciongobiernopueblo.utils.rand

/*
 *
 * Escriba un programa que simule el funcionamiento
 * normal de un ascensor (elevador) moderno con 25
 * pisos (niveles) y que posee dos botones de SUBIR y
 * BAJAR, excepto en el piso (nivel) inferior, que sólo
 * existe botón de llamada para SUBIR y en el último
 * piso (nivel) que sólo existe botón de BAJAR
 *
 */

class AscensorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAscensorBinding
    var numeroPiso = rand(1, 25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAscensorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButtonArriba.setOnClickListener {
            numeroPiso++
            desactivarButton()
        }

        binding.floatingActionButtonABajo.setOnClickListener {
            numeroPiso--
            desactivarButton()
        }

        binding.textViewNumeroPiso.text = numeroPiso.toString()

    }

    fun desactivarButton(){
        binding.floatingActionButtonArriba.isEnabled = numeroPiso != 25

        binding.floatingActionButtonABajo.isEnabled = numeroPiso != 1

        binding.textViewNumeroPiso.setText(numeroPiso.toString())
    }
}