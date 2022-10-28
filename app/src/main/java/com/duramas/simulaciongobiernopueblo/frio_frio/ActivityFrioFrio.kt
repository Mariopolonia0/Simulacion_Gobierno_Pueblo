package com.duramas.simulaciongobiernopueblo.frio_frio


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityFrioFrioBinding
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

    var listaVaso = arrayListOf<Vaso>(
        Vaso(2.0, 2.0, 3),
        Vaso(4.2, 4.2, 6),
        Vaso(6.3, 6.3, 9),
    )

    var cubo = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFrioFrioBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        //funcion para iniciar el 2do hilo y no bloquear la interfaz pricipal
        Thread(Runnable() {
            //aqui se hacede a al hilo que maneja la interfaz de usuario
            this@ActivityFrioFrio.runOnUiThread({
                binding.progressBar.visibility = View.VISIBLE
            })

            while (cubo > 0) {
                println(cubo.toString())
                this@ActivityFrioFrio.runOnUiThread({
                    binding.porcientoCuboTextView.setText(cubo.toString())
                })

            }

            this@ActivityFrioFrio.runOnUiThread({
                binding.progressBar.visibility = View.INVISIBLE
            })


        }).start()


    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (start..end).random()
    }

}

//        binding.progressBar.visibility = View.VISIBLE
//        while (cubo > 0) {
//            println(cubo.toString())
//            cubo--
//        }
//        Thread.sleep(3000)
//        binding.progressBar.visibility = View.INVISIBLE