package com.duramas.simulaciongobiernopueblo.test_actitudes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityTestActitudesBinding

class TestActitudesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestActitudesBinding
    var currentQuiestion = 0
    val questions = Preguntas().listaPreguntas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestActitudesBinding.inflate(layoutInflater)
        CargarPregunta()
        setContentView(binding.root)

        binding.floatingActionButtonNext.setOnClickListener({
            if (currentQuiestion < questions.size - 1)
                currentQuiestion++
            CargarPregunta()

        })

        binding.floatingActionButtonBefore.setOnClickListener({
            if (currentQuiestion > 0)
                currentQuiestion--
            CargarPregunta()

        })
    }

    private fun CargarPregunta() {

        if (currentQuiestion < questions.size && currentQuiestion >= 0) {
            binding.preguntaTextView.setText(questions[currentQuiestion].preguntas)
            binding.radioButtonUno.setText(questions[currentQuiestion].opcionUna)
            binding.radioButtonDos.setText(questions[currentQuiestion].opcionDos)
            binding.radioButtonTres.setText(questions[currentQuiestion].opcionTres)
            binding.radioButtonCuatro.setText(questions[currentQuiestion].opcionCuatro)
        }
    }
}