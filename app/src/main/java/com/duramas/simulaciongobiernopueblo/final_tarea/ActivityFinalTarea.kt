package com.duramas.simulaciongobiernopueblo.final_tarea

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityFinalTareaBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActivityFinalTarea : AppCompatActivity() {
    private lateinit var binding: ActivityFinalTareaBinding

    var variableTiempoReal = VariableTiempoReal()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalTareaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textViewProcesoSimulacion.setText("Realizando Siembra")
        binding.textViewSemillaTractor.setText(variableTiempoReal.semillaEnTractorDisponible.toString() + " KiloGramo")

        binding.buttonCambiarSemillaTractor.setOnClickListener {
            if (binding.editTextSemillaTractor.text.toString().isEmpty()){
                Toast.makeText(
                    applicationContext,
                    "Digite El número Kilogramos de semilla para subir al tractor",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                variableTiempoReal.semillaEnTractorDisponible =
                    binding.editTextSemillaTractor.text.toString().toInt()
                variableTiempoReal.semillaEnTractorInicio = variableTiempoReal.semillaEnTractorDisponible
                binding.textViewSemillaTractor.setText(variableTiempoReal.semillaEnTractorDisponible.toString() + " KiloGramo")
            }

        }
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
                binding.buttonCambiarSemillaTractor.isEnabled = false
                binding.editTextSemillaTractor.isEnabled = false
                realizarSiembra()
                true
            }
            R.id.stop -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun realizarSiembra() {

        Thread(Runnable() {
            while (variableTiempoReal.semillaEnTractorDisponible > 0) {
                variableTiempoReal.terrenoSembrado += 14
                variableTiempoReal.semillaEnTractorDisponible -= 1

                this@ActivityFinalTarea.runOnUiThread {
                    binding.textViewTerrenoSembrado.setText(variableTiempoReal.terrenoSembrado.toString() + " Metro")
                    binding.textViewSemillaTractor.setText(variableTiempoReal.semillaEnTractorDisponible.toString() + " KiloGramo")
                }

                variableTiempoReal.moverTractor += 5
                moverTractor((variableTiempoReal.moverTractor).toFloat())

                if ((variableTiempoReal.semillaEnTractorInicio / 2) > variableTiempoReal.semillaEnTractorDisponible)
                    this@ActivityFinalTarea.runOnUiThread {
                        binding.textViewProcesoSimulacion.setText("Terminado la Siembra")
                    }

                Thread.sleep(200)
            }

            Thread.sleep(2000)
            germinar()
            Thread.sleep(2000)
            abonos()
            Thread.sleep(2000)
            abonos()
            Thread.sleep(2000)
            abonos()
            resultadoFinal()
        }).start()
    }

    private fun abonos() {

        this@ActivityFinalTarea.runOnUiThread {
            binding.textViewProcesoSimulacion.setText("Empezando abono")
        }

        if (variableTiempoReal.diaTrancurrido < 8)
            variableTiempoReal.diaTrancurrido += rand(4, 6)
        else
            variableTiempoReal.diaTrancurrido += rand(11, 15)

        val recuperada = rand(1, (variableTiempoReal.semillaDanada / 2))

        variableTiempoReal.semillaRecuperada += recuperada
        variableTiempoReal.semillaDanada -= recuperada

        this@ActivityFinalTarea.runOnUiThread {
            binding.textViewDiaTrascurrido.setText(variableTiempoReal.diaTrancurrido.toString())
            binding.textViewSemillaRecuperada.setText(variableTiempoReal.semillaRecuperada.toString())
            binding.textViewSemillaDanada.setText(variableTiempoReal.semillaDanada.toString())
            binding.textViewProcesoSimulacion.setText("Terminando abono")
        }
    }

    private fun resultadoFinal() {
        val cosecha = variableTiempoReal.semillaGerminada + variableTiempoReal.semillaRecuperada
        val aguaUsada = variableTiempoReal.diaTrancurrido * 400
        val fertilizante = ((variableTiempoReal.terrenoSembrado * 30) / 100) * 3

        this@ActivityFinalTarea.runOnUiThread {
            binding.textViewCosecha.setText(cosecha.toString() + " Kilogramos")
            binding.textViewAguaUtilizada.setText(aguaUsada.toString() + " Litros")
            binding.textViewFertilizanteUtilizado.setText(fertilizante.toString() + " Kilogramos")
            binding.buttonCambiarSemillaTractor.isEnabled = true
            binding.editTextSemillaTractor.isEnabled = true
        }
    }

    private fun germinar() {
        this@ActivityFinalTarea.runOnUiThread {
            binding.textViewProcesoSimulacion.setText("La Semillas están germinando")
        }

        variableTiempoReal.diaTrancurrido += rand(5, 7)
        variableTiempoReal.semillaGerminada = (variableTiempoReal.semillaEnTractorInicio * 80) / 100
        variableTiempoReal.semillaDanada = (variableTiempoReal.semillaEnTractorInicio * 20) / 100

        this@ActivityFinalTarea.runOnUiThread {
            binding.textViewSemillaGerminada.setText(variableTiempoReal.semillaGerminada.toString())
            binding.textViewSemillaDanada.setText(variableTiempoReal.semillaDanada.toString())
        }
    }

    private fun moverTractor(distacia: Float) {
        binding.imageView.translationX = distacia
    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }


}