package com.duramas.simulaciongobiernopueblo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.duramas.simulaciongobiernopueblo.databinding.FragmentMainBinding
import com.duramas.simulaciongobiernopueblo.final_tarea.ActivityFinalTarea
import com.duramas.simulaciongobiernopueblo.frio_frio.ActivityFrioFrio
import com.duramas.simulaciongobiernopueblo.parqueo.ActivityParqueo
import com.duramas.simulaciongobiernopueblo.pueblo_gobierno.ActivityPuebloGobierno
import com.duramas.simulaciongobiernopueblo.salud_cardio_vascular.ActivitySaludCardioVascular
import com.duramas.simulaciongobiernopueblo.test_actitudes.TestActitudesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTextActitudes.setOnClickListener {
            val test = Intent(this, TestActitudesActivity::class.java)
            startActivity(test)
        }

        binding.buttonTareaFinal.setOnClickListener {
            val tareaFinal = Intent(this, ActivityFinalTarea::class.java)
            startActivity(tareaFinal)
        }

        binding.buttonSimulacionParqueo.setOnClickListener {
            val parqueo = Intent(this, ActivityParqueo::class.java)
            startActivity(parqueo)
        }

        binding.buttonVentaFrio.setOnClickListener {
            val frio = Intent(this, ActivityFrioFrio::class.java)
            startActivity(frio)
        }

        binding.buttonSaludCardio.setOnClickListener {
            val cardio = Intent(this, ActivitySaludCardioVascular::class.java)
            startActivity(cardio)
        }

        binding.buttonPuebloGobierno.setOnClickListener({
            val pueblo = Intent(this, ActivityPuebloGobierno::class.java)
            startActivity(pueblo)
        })
    }
}