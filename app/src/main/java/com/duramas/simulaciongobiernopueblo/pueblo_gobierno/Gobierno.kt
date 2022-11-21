package com.duramas.simulaciongobiernopueblo.pueblo_gobierno

//la ideologia puede ser CONSERVADOR, LIBERAL
//el tipo gobierno puede ser PERMISIVA, COERCITIVA
data class Gobierno(
    var nombre: String,
    var ideologia :String,
    var tipoGobierno :String
)
