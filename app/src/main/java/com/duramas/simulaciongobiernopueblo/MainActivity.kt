package com.duramas.simulaciongobiernopueblo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.duramas.simulaciongobiernopueblo.animacion.ActivityAnimacion
import com.duramas.simulaciongobiernopueblo.databinding.FragmentMainBinding
import com.duramas.simulaciongobiernopueblo.frio_frio.ActivityFrioFrio
import com.duramas.simulaciongobiernopueblo.parqueo.ActivityParqueo

class MainActivity : AppCompatActivity() {

    private lateinit var binding: FragmentMainBinding
    val listaGobierno = ArrayList<Gobierno>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.floatingActionButtonIrAnimacion.setOnClickListener {
            val animacion = Intent(this, ActivityAnimacion::class.java)
            startActivity(animacion)
        }

        binding.floatingActionButtonSimulacionParqueo.setOnClickListener {
            val parqueo = Intent(this, ActivityParqueo::class.java)
            startActivity(parqueo)
        }

        binding.floatingActionButtonSimulacionFrioFrio.setOnClickListener {
            val frio = Intent(this, ActivityFrioFrio::class.java)
            startActivity(frio)
        }

        binding.recyclerView.adapter = AdacterGobierno()
        val adacter = binding.recyclerView.adapter as AdacterGobierno

        adacter.submitList(listaGobierno)

        val ideologias = resources.getStringArray(R.array.ideologias)

        val adapter = ArrayAdapter(this, R.layout.text_view, ideologias)

        binding.spinnerTipoGobierno.adapter = adapter
        binding.floatingActionButtonAgregarGobierno.setOnClickListener({

            adacter.submitList(listaGobierno)
            if (agregar())
                Log.e("Error", "esta Vacio")

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