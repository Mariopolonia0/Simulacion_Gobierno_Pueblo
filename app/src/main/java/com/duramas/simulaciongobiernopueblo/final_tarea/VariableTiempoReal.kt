package com.duramas.simulaciongobiernopueblo.final_tarea

data class VariableTiempoReal(
    var moverTractor :Int = 0,
    var terrenoSembrado: Int = 0,
    var terrenoAbonado: Int = 0,
    var semillaDanada: Int = 0,
    var semillaRecuperada: Int = 0,
    var semillaGerminada: Int = 0,
    val terrenoDisponible: Int = 628,
    var semillaEnTractorDisponible: Int = 100,
    var semillaEnTractorInicio: Int = 100,
    var diaTrancurrido: Int = 0
)

