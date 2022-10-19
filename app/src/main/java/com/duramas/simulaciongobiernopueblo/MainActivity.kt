package com.duramas.simulaciongobiernopueblo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.duramas.simulaciongobiernopueblo.animacion.ActivityAnimacion
import com.duramas.simulaciongobiernopueblo.databinding.FragmentMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listaGobierno = ArrayList<Gobierno>()

        binding.floatingActionButtonIrAnimacion.setOnClickListener {
            val animacion = Intent(this, ActivityAnimacion::class.java)
            startActivity(animacion)
        }

        binding.recyclerView.adapter = AdacterGobierno()
        val adacter = binding.recyclerView.adapter as AdacterGobierno

        adacter.submitList(listaGobierno)

        val ideologias = resources.getStringArray(R.array.ideologias)

        val adapter = ArrayAdapter(this, R.layout.text_view, ideologias)

        binding.spinnerTipoGobierno.adapter = adapter
        binding.floatingActionButtonAgregarGobierno.setOnClickListener({

            var tipogobierno = "PERMISIVA"

            if (binding.spinnerTipoGobierno.selectedItemPosition == 0)
                tipogobierno = "COERCITIVA"

            listaGobierno.add(
                Gobierno(
                    binding.editTextNombreGobierno.text.toString(),
                    binding.spinnerTipoGobierno.selectedItem.toString(),
                    tipogobierno
                )
            )
            adacter.submitList(listaGobierno)
            binding.editTextNombreGobierno.setText("")
        })
    }
}