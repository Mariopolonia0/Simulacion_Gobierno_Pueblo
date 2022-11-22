package com.duramas.simulaciongobiernopueblo.salud_cardio_vascular

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Toast
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivitySaludCardioVascularBinding
import com.duramas.simulaciongobiernopueblo.databinding.FragmentMainBinding

class ActivitySaludCardioVascular : AppCompatActivity() {
    private lateinit var binding: ActivitySaludCardioVascularBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaludCardioVascularBinding.inflate(layoutInflater)

        binding.imageButtonMostrarResultado.setOnClickListener({
            MostraResultado()
        })

        setContentView(binding.root)
    }

    private fun MostraResultado() {
        if (binding.editTextNumberPusoSanguineo.text.length == 0) {
            Toast.makeText(binding.root.context, "Digite el pulso sanguíneo", Toast.LENGTH_SHORT)
                .show()

        } else {
            val puso = binding.editTextNumberPusoSanguineo.text.toString().toInt()
            if (puso <= 30) {
                binding.textViewResultado.setTextColor(Color.RED)
                binding.textViewRecomendacion.setTextColor(Color.RED)
                binding.textViewMostrarRecomendacion.setText("El paciente está muriendo y necesita RCP rápidamente")
                binding.cardViewResultado.visibility = View.VISIBLE
            }
            if (puso >= 60 && puso <= 70) {
                binding.textViewResultado.setTextColor(Color.BLUE)
                binding.textViewRecomendacion.setTextColor(Color.BLUE)
                binding.textViewMostrarRecomendacion.setText(
                    "El paciente necesita ejercicio cardio vascular y si la presión sanguínea sigue bajando tiene que ir al médico"
                )
                binding.cardViewResultado.visibility = View.VISIBLE
            }
            if (puso < 60 || puso > 100 ) {
                binding.textViewResultado.setTextColor(Color.RED)
                binding.textViewRecomendacion.setTextColor(Color.RED)
                binding.textViewMostrarRecomendacion.setText(
                    "El paciente necesita el RCP como nivel de gravedad, debe hacer una llamada o un mensaje a una persona o institución de alerta"
                )
                binding.cardViewResultado.visibility = View.VISIBLE
            }
            if (puso > 70 && puso <= 100) {
                binding.textViewResultado.setTextColor(Color.BLUE)
                binding.textViewRecomendacion.setTextColor(Color.BLUE)
                binding.textViewMostrarRecomendacion.setText("La presión sanguínea está normal")
                binding.cardViewResultado.visibility = View.VISIBLE
            }
        }
    }

    //peligrosa 60

}