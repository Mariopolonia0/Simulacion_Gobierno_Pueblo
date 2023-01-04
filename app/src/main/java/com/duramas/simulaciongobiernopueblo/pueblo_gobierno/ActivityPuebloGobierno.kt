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
    private val listaGobierno = ArrayList<Gobierno>()
    private var progreso: Progreso = Progreso()

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

        listaGobierno.add(
            Gobierno(
                binding.editTextNombreGobierno.text.toString(),
                binding.spinnerTipoGobierno.selectedItem.toString(),
                tipoGobierno()
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

        binding.progressBarGobiernoPueblo.visibility = View.VISIBLE
        progreso.detenerse = false

        Thread(Runnable() {

            while (progreso.detenerse == false) {
                cambiarPeriodo()
                if (binding.ratingBar.rating < 3) {
                    seleccionarGobierno()
                }

                for (contador in 1..8) {
                    if (progreso.detenerse == false) {
                        cambioContiendaCivil()
                        cambioEstadoGobierno()
                        Thread.sleep(2000)
                    }
                }
                Thread.sleep(2000)
            }

        }).start()
    }

    private fun seleccionarGobierno() {
        val seleccionado = rand(1, listaGobierno.size)
        progreso.cambioGobierno++

        this@ActivityPuebloGobierno.runOnUiThread {
            binding.textViewGobiernoSeleccionado.setText(listaGobierno[seleccionado - 1].nombre)
        }
    }

    private fun cambioEstadoGobierno() {
        EstadoGobierno()
        this@ActivityPuebloGobierno.runOnUiThread {
            binding.textViewEstadoGobierno.setText(progreso.estadoGobierno)
        }
    }

    private fun cambioContiendaCivil() {
        progreso.contiendaCivil = rand(1, 5)

        if (binding.ratingBar.rating < 2)
            progreso.protestas++

        this@ActivityPuebloGobierno.runOnUiThread {
            binding.ratingBar.rating = progreso.contiendaCivil.toFloat()
        }
    }

    private fun tipoGobierno(): String {
        return if (binding.spinnerTipoGobierno.selectedItemPosition == 0)
            "COERCITIVA"
        else
            "PERMISIVA"
    }

    private fun EstadoGobierno() {
        if (progreso.contiendaCivil < 3)
            progreso.estadoGobierno = "PERMISIVA"
        else
            progreso.estadoGobierno = "COERCITIVA"
    }

    private fun cambiarPeriodo() {
        progreso.periodoInicio += 4
        progreso.periodoFin += 4

        val texto = "${progreso.periodoInicio} - ${progreso.periodoFin}"

        this@ActivityPuebloGobierno.runOnUiThread {
            binding.textViewPeriodoGobieno.setText(texto)
        }
    }

    private fun terminarSimulacion() {
        binding.progressBarGobiernoPueblo.visibility = View.INVISIBLE

        this@ActivityPuebloGobierno.runOnUiThread {
            val textoCambios = "${progreso.cambioGobierno} cambios de gobierno"
            binding.textViewCambiosGobierno.setText(textoCambios)

            val textProtestas = "Se realizaron ${progreso.protestas} protestas"
            binding.textViewProstestas.setText(textProtestas)
        }

        progreso = Progreso()
        progreso.detenerse = true
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