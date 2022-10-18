package com.duramas.simulaciongobiernopueblo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.navigation.findNavController
import com.duramas.simulaciongobiernopueblo.databinding.FragmentMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listaGobierno = ArrayList<Gobierno>()

        binding.floatingActionButtonIrAnimacion.setOnClickListener {
            it.findNavController().navigate(R.id.motion)
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