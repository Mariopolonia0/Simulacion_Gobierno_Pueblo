package com.duramas.simulaciongobiernopueblo.frio_frio


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityFrioFrioBinding
import com.duramas.simulaciongobiernopueblo.databinding.DialogoEditarCuboBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

/*
    descripcion de cubo
    ----------------------------
    +cubo es de 5 litro = 5000 ml  coje 6 litro
    -cuando el cubo se acabe termina la simulacion


    tipos de vaso
    ------------------------------
    -vaso grande  $90,  3.15
    -vaso mediano $50, 2.1
    -vaso pequeño $25  1.05

    descripcion de vaso
    -----------------------------------
    vaso pequeño
    2 ml  jugo limon
    2 oz azucar
    3 cubitos de hielos
    3 minutos

    vaso mediano (se multiplica lo del el vaso pequeño por 2.1)
    4.2 ml jugo limon
    4.2 oz azucar
    6 cubitos de hielos

    vaso grande (se multiplica lo del el vaso pequeño por 3.15)
    6.3 ml jugo limon
    6.3 oz azucar
    9 cubitos de hielos

    imprimir en ejecucion
    -----------------------
    -persona que llegan
    -persona de la que llegan preguntan
    -persona que compran
    -frozen comprado
    -propina de 10 peso dejada por el baso grande

    imprimir fin de programa
    --------------------------------
    -benefio
    -costo totales
    -ventas
    -propinas

 */


class ActivityFrioFrio : AppCompatActivity() {

    private lateinit var binding: ActivityFrioFrioBinding

    val vasoUno = Vaso(90.0, 2.0, 2.0, 3)
    val vasoDos = Vaso(50.0, 4.2, 4.2, 6)
    val vasoTres = Vaso(25.0, 6.3, 6.3, 9)

    var cubo = 1000.0

    var proceso = Proceso()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrioFrioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.valorCuboTextView.setText(cubo.toString())
        binding.imageButtonEditarCubo.setOnClickListener({
            showDialogEditarCubo()
        })

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
                Snackbar.make(this.binding.root, "Inicio la Simulacion", Snackbar.LENGTH_LONG)
                calcularIngredienteVaso()
                true
            }
            R.id.stop -> {
                binding.progressBar.visibility = View.INVISIBLE
                Snackbar.make(binding.root, "Termino la Simulacion", Snackbar.LENGTH_LONG)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun calcularIngredienteVaso() {
        binding.imageButtonEditarCubo.isEnabled = false

        if (cubo <= 0) {
            Toast.makeText(
                applicationContext,
                "Cubo es 0 o menor a 0. No se puede iniciar la simulación",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        proceso = Proceso()

        //funcion para iniciar el 2do hilo y no bloquear la interfaz pricipal
        Thread(Runnable() {
            //aqui se hacede a al hilo que maneja la interfaz de usuario
            this@ActivityFrioFrio.runOnUiThread({
                binding.progressBar.visibility = View.VISIBLE
            })

            while (cubo > 8) {
                val personaLlegan = rand(1, 10)
                val lleganPreguntan = rand(1, personaLlegan)
                val personaCopran = rand(1, lleganPreguntan)

                proceso.personaLLegan += personaLlegan
                proceso.personaPregunta += lleganPreguntan
                proceso.personaCompran += personaCopran

                for (i in 1..personaCopran) {
                    when (rand(1, 3)) {
                        1 -> {
                            proceso.frozenCompradoUno++
                            cubo -= 6.3
                        }
                        2 -> {
                            proceso.frozenCompradoDos++
                            cubo -= 4.2
                        }
                        3 -> {
                            proceso.frozenCompradoTres++
                            cubo -= 2
                        }
                    }
                }

                this@ActivityFrioFrio.runOnUiThread({
                    binding.valorPersonaLlegaTextView.setText(proceso.personaLLegan.toString())
                    binding.valorPersonaCopranTextView.setText(proceso.personaCompran.toString())
                    binding.valorPersonaPreguntanTextView.setText(proceso.personaPregunta.toString())
                    binding.valorNumeroVendidoUnotextView.setText(proceso.frozenCompradoUno.toString())
                    binding.valorNumeroVendidoDostextView.setText(proceso.frozenCompradoDos.toString())
                    binding.valorNumeroVendidoTrestextView.setText(proceso.frozenCompradoTres.toString())
                    binding.valorCuboTextView.setText(cubo.toString())
                })

                Thread.sleep(1000)
            }

            val costoTotal =
                ((proceso.frozenCompradoUno * 90) + (proceso.frozenCompradoDos * 45) + (proceso.frozenCompradoTres * 20))
            val ventas =
                ((proceso.frozenCompradoUno * 90) + (proceso.frozenCompradoDos * 50) + (proceso.frozenCompradoTres * 25))
            val beneficio = ventas - costoTotal
            val propina = rand(0, proceso.frozenCompradoUno) * 10
            val propinaMas = beneficio + propina

            this@ActivityFrioFrio.runOnUiThread({
                binding.valorCuboTextView.setText("0")
                binding.progressBar.visibility = View.INVISIBLE
                binding.imageButtonEditarCubo.isEnabled = true
                binding.textViewValorGastoTotal.setText(costoTotal.toString())
                binding.textViewTotalVentas.setText(ventas.toString())
                binding.textViewValorBeneficio.setText(beneficio.toString())
                binding.textViewTotalPropina.setText(propina.toString())
                binding.textViewValorGananciaMas.setText(propinaMas.toString())
                binding.cardViewResultado.visibility = View.VISIBLE
            })

        }).start()
    }

    fun showDialogEditarCubo() {
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialogo_editar_cubo)
        val botonCambiarValorCubo =
            dialog.findViewById<FloatingActionButton>(R.id.floatingActionButtonAceptarEditarCubo)
        val textEditarCubo = dialog.findViewById<EditText>(R.id.editTextEditarCubo)

        botonCambiarValorCubo?.setOnClickListener({
            if (textEditarCubo?.text?.length == 0) {
                Toast.makeText(
                    applicationContext,
                    "Digite El numero de ml del cubo",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                cubo = textEditarCubo?.text.toString().toDouble()
                dialog.dismiss()
                binding.valorCuboTextView.setText(cubo.toString())
            }
        })

        dialog.show()

    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

}