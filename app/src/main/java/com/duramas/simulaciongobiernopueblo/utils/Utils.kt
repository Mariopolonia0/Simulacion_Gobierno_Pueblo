package com.duramas.simulaciongobiernopueblo.utils

import android.content.Context
import android.widget.Toast

fun rand(start: Int, end: Int): Int {
    require(start <= end) { "Illegal Argument" }
    return (start..end).random()
}

fun toast(mensaje: String, context: Context) {
    Toast.makeText(
        context,
        mensaje,
        Toast.LENGTH_LONG
    ).show()
}
