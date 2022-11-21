package com.duramas.simulaciongobiernopueblo.pueblo_gobierno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityPuebloGobiernoBinding

class ActivityPuebloGobierno : AppCompatActivity() {
    private lateinit var binding: ActivityPuebloGobiernoBinding
    val listaGobierno = ArrayList<Gobierno>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuebloGobiernoBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_pueblo_gobierno)
        binding.recyclerView.adapter = AdacterGobierno()
        val adacter = binding.recyclerView.adapter as AdacterGobierno
        adacter.submitList(listaGobierno)

        val ideologias = resources.getStringArray(R.array.ideologias)

        val adapter = ArrayAdapter(this, R.layout.text_view, ideologias)
        binding.spinnerTipoGobierno.adapter = adapter

        binding.floatingActionButtonAgregarGobierno.setOnClickListener({

            if (!agregar())
                Toast.makeText(
                    this,
                    "El nombre del gobierto esta vacio",
                    Toast.LENGTH_SHORT
                ).show()

            adacter.submitList(listaGobierno)
        })

    }

    private fun agregar(): Boolean {

        if (binding.editTextNombreGobierno.text.length == 0)
            return false

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

        binding.editTextNombreGobierno.setText("")

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.play -> {
                true
            }
            R.id.stop -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}