package com.duramas.simulaciongobiernopueblo.pueblo_gobierno

data class Progreso(
    var periodoInicio: Int = 2020,
    var periodoFin: Int = 2024,
    var detenerse: Boolean = false,
    var contiendaCivil: Int = 2,
    var estadoGobierno: String = "",
    var cambioGobierno: Int = 0,
    var protestas: Int = 0,
)