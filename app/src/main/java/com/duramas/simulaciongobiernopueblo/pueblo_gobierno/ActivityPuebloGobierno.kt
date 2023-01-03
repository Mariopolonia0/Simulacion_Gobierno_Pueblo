package com.duramas.simulaciongobiernopueblo.pueblo_gobierno

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityPuebloGobiernoBinding
import com.duramas.simulaciongobiernopueblo.utils.rand
import com.duramas.simulaciongobiernopueblo.utils.toast

class ActivityPuebloGobierno : AppCompatActivity() {
    private lateinit var binding: ActivityPuebloGobiernoBinding
    val listaGobierno = ArrayList<Gobierno>()

    var detener = false

    var contador = 2022

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPuebloGobiernoBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.recyclerView.adapter = AdacterGobierno()
        val adacterRecycler = binding.recyclerView.adapter as AdacterGobierno
        adacterRecycler.submitList(listaGobierno)

        val ideologias = arrayOf("CONSERVADOR", "LIBERAL")
        val adapterSpinner = ArrayAdapter(binding.root.context, R.layout.text_view, ideologias)
        binding.spinnerTipoGobierno.adapter = adapterSpinner

        binding.floatingActionButtonAgregarGobierno.setOnClickListener {
            if (!agregar())
                toast("El nombre del gobierto esta vacio", this)

            adacterRecycler.submitList(listaGobierno)
        }
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

    private fun iniciarSimulacion() {
        if (listaGobierno.size <= 1) {
            toast("La lista tiene que tener mas de un gobierno", this)
            return
        }
        detener = false
        Thread(Runnable() {
            //aqui se hacede a al hilo que maneja la interfaz de usuario

            while (detener == false){
                this@ActivityPuebloGobierno.runOnUiThread {
                    binding.textViewPeriodoGobieno.setText((contador++).toString())
                }
            }

        }).start()

        binding.progressBarGobiernoPueblo.visibility = View.VISIBLE
    }

    private fun terminarSimulacion() {
        binding.progressBarGobiernoPueblo.visibility = View.INVISIBLE
        detener = true
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
                iniciarSimulacion()
                true
            }
            R.id.stop -> {
                terminarSimulacion()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}