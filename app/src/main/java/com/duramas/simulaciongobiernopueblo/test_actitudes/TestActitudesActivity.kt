package com.duramas.simulaciongobiernopueblo.test_actitudes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityTestActitudesBinding
import com.google.android.material.snackbar.Snackbar

class TestActitudesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestActitudesBinding
    var currentQuiestion = 0
    val questions = Preguntas().listaPreguntas()
    val respuestas = Respuestas()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestActitudesBinding.inflate(layoutInflater)
        CargarPregunta()
        setContentView(binding.root)

        binding.floatingActionButtonNext.setOnClickListener({
            getSeleccion()
        })

        binding.floatingActionButtonBefore.setOnClickListener({
            Toast.makeText(binding.root.context, "No hay retroceso", Toast.LENGTH_SHORT)
                .show()
        })
    }

    private fun getSeleccion() {
        var haySeleccionado = false

        when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioButtonUno -> {
                llenarResultado(1, currentQuiestion)
                haySeleccionado = true
            }
            R.id.radioButtonDos -> {
                llenarResultado(2, currentQuiestion)
                haySeleccionado = true
            }
            R.id.radioButtonTres -> {
                llenarResultado(3, currentQuiestion)
                haySeleccionado = true
            }
            R.id.radioButtonCuatro -> {
                llenarResultado(4, currentQuiestion)
                haySeleccionado = true
            }
        }

        if (haySeleccionado) {
            if (currentQuiestion < questions.size - 1) {
                currentQuiestion++
                CargarPregunta()
            } else {
                mostrarResultado()
                Toast.makeText(binding.root.context, "El test termino", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(binding.root.context, "Seleccione una repuesta", Toast.LENGTH_SHORT)
                .show()
        }

        binding.radioGroup.clearCheck()
    }

    private fun mostrarResultado() {
        binding.progressBarIngenieria.progress = respuestas.Ingenieria * 10
        binding.progressBarMedicina.progress = respuestas.Medicina * 10
        binding.progressBarTurimos.progress = respuestas.Turimos * 10
        binding.progressBarDerecho.progress = respuestas.Derecho * 10
        binding.progressBarEducacion.progress = respuestas.Educacion * 10
        binding.progressBarActitud.progress = respuestas.Actitud * 10
        binding.progressBarPersonalidad.progress = respuestas.Personalidad * 10

        binding.cardRespuesta.visibility = View.VISIBLE
        binding.radioGroup.visibility = View.INVISIBLE
        binding.preguntaTextView.visibility = View.INVISIBLE
        binding.floatingActionButtonNext.visibility = View.INVISIBLE
        binding.floatingActionButtonBefore.visibility = View.INVISIBLE
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

    private fun llenarResultado(opcion: Int, pregunta: Int) {
        when (pregunta) {
            0 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Ingenieria += 4
                        respuestas.Educacion += 2
                    }
                    2 -> {
                        respuestas.Educacion += 4
                        respuestas.Derecho + 2
                    }
                    3 -> {
                        respuestas.Medicina + 4
                        respuestas.Turimos + 3
                    }
                    4 -> {
                        respuestas.Derecho += 4
                        respuestas.Educacion += 3
                    }
                }
            }
            1 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Actitud += 4
                        respuestas.Personalidad += 4
                    }
                    2 -> {
                        respuestas.Actitud += 2
                        respuestas.Personalidad += 2
                    }
                    3 -> {
                        respuestas.Actitud += 1
                        respuestas.Personalidad += 1
                    }
                    4 -> {
                        respuestas.Actitud += 0
                        respuestas.Personalidad += 0
                    }
                }
            }
            2 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Actitud += 4
                        respuestas.Personalidad += 4
                    }
                    2 -> {
                        respuestas.Actitud += 2
                        respuestas.Personalidad += 2
                    }
                    3 -> {
                        respuestas.Actitud += 1
                        respuestas.Personalidad += 1
                    }
                    4 -> {
                        respuestas.Actitud += 0
                        respuestas.Personalidad += 0
                    }
                }
            }
            3 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Ingenieria += 4
                        respuestas.Educacion += 2
                    }
                    2 -> {
                        respuestas.Derecho += 4
                        respuestas.Educacion += 3
                    }
                    3 -> {
                        respuestas.Medicina + 4
                        respuestas.Turimos + 3
                    }
                    4 -> {
                        respuestas.Derecho += 4
                        respuestas.Educacion += 3
                    }
                }
            }
            4 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Ingenieria += 4
                        respuestas.Educacion += 2
                    }
                    2 -> {
                        respuestas.Derecho += 4
                        respuestas.Educacion += 3
                    }
                    3 -> {
                        respuestas.Medicina += 4
                        respuestas.Educacion += 3
                    }
                    4 -> {
                        respuestas.Derecho += 4
                        respuestas.Turimos += 3
                    }
                }
            }
            5 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Ingenieria += 4

                    }
                    2 -> {
                        respuestas.Educacion += 4
                    }
                    3 -> {
                        respuestas.Educacion += 4
                    }
                    4 -> {
                        respuestas.Turimos += 4
                    }
                }
            }
            6 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Derecho += 4
                        respuestas.Educacion += 4
                    }
                    2 -> {
                        respuestas.Educacion += 4
                    }
                    3 -> {
                        respuestas.Medicina += 4
                    }
                    4 -> {
                        respuestas.Derecho += 4
                    }
                }
            }
            7 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Derecho += 2
                        respuestas.Educacion += 2
                        respuestas.Medicina += 2
                        respuestas.Turimos += 2
                        respuestas.Ingenieria += 2
                    }
                    2 -> {
                        respuestas.Derecho += 4
                        respuestas.Educacion += 4
                        respuestas.Medicina += 2
                        respuestas.Turimos += 2
                        respuestas.Ingenieria += 2
                    }
                    3 -> {
                        respuestas.Derecho += 2
                        respuestas.Educacion += 2
                        respuestas.Medicina += 2
                        respuestas.Turimos += 2
                        respuestas.Ingenieria += 4
                    }
                    4 -> {
                        respuestas.Derecho += 2
                        respuestas.Educacion += 2
                        respuestas.Medicina += 2
                        respuestas.Turimos += 2
                        respuestas.Ingenieria += 2
                    }
                }
            }
            8 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Actitud += 4
                        respuestas.Personalidad += 4
                    }
                    2 -> {
                        respuestas.Actitud += 2
                        respuestas.Personalidad += 2
                    }
                    3 -> {
                        respuestas.Actitud += 1
                        respuestas.Personalidad += 1
                    }
                    4 -> {
                        respuestas.Actitud += 0
                        respuestas.Personalidad += 0
                    }
                }
            }
            9 -> {
                when (opcion) {
                    1 -> {
                        respuestas.Actitud += 4
                        respuestas.Personalidad += 4
                    }
                    2 -> {
                        respuestas.Actitud += 2
                        respuestas.Personalidad += 2
                    }
                    3 -> {
                        respuestas.Actitud += 1
                        respuestas.Personalidad += 1
                    }
                    4 -> {
                        respuestas.Actitud += 0
                        respuestas.Personalidad += 0
                    }
                }
            }
        }
    }
}