package com.duramas.simulaciongobiernopueblo.parqueo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.duramas.simulaciongobiernopueblo.R
import com.duramas.simulaciongobiernopueblo.databinding.ActivityParqueoBinding

class ActivityParqueo : AppCompatActivity() {

    private lateinit var binding: ActivityParqueoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityParqueoBinding.inflate(layoutInflater)
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
                binding.motion1.transitionToEnd()
                true
            }
            R.id.stop -> {
                binding.motion1.transitionToState(R.id.anima1)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}