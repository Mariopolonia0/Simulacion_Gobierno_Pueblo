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
            if (puso == 0) {
                binding.textViewResultado.setTextColor(Color.RED)
                binding.textViewRecomendacion.setTextColor(Color.RED)
                binding.textViewMostrarRecomendacion.setText("El paciente está muerto o revisé él puso sanguíneo otras ves")
                binding.cardViewResultado.visibility = View.VISIBLE
            }
            if (puso <= 60){
                binding.textViewResultado.setTextColor(Color.RED)
                binding.textViewRecomendacion.setTextColor(Color.RED)
                binding.textViewMostrarRecomendacion.setText("Nivel de gravedad en tendencia peligrosa")
                binding.cardViewResultado.visibility = View.VISIBLE
            }
        }
    }

    //peligrosa 60

}