package com.duramas.simulaciongobiernopueblo.test_actitudes

data class Preguntas(
    val preguntas: String = "",
    val opcionUna: String = "",
    val opcionDos: String = "",
    val opcionTres: String = "",
    val opcionCuatro: String = "",
    val seleccion: Int = 0
) {
    fun listaPreguntas(): List<Preguntas> = mutableListOf(
        Preguntas(
            "¿Cuáles de las siguientes materias son las que más te interesan en la escuela?",
            "Matemáticas, Físicas y Química",
            "Historia, Taller de Lectura y Redacción e Inglés",
            "Biología, Química y Psicología",
            "Economía, Estructura Socioeconómica y Contabilidad"
        ),
        Preguntas(
            "Me resulta más fácil expresar mis pensamientos, sensaciones y emociones con palabras",
            "Si",
            "A veces",
            "no",
            "nunca"
        ),
        Preguntas(
            "Soy capaz de captar lo que otro siente y ayudarlo a sentirse mejor.",
            "Si",
            "A veces",
            "no",
            "nunca"
        ),
        Preguntas(
            "¿Qué tipo de libros te llama la atención leer?",
            "Aquellos en los que necesitas poner mucha atención para identificar las claves y pistas para así armar la historia",
            "Los que están basados en hechos reales o pasajes de la Historia",
            "Libros en los que se encuentren muchos detalles de los ambientes, que sean muy descriptivos",
            "Historias en las que pueda saber fácilmente cómo el protagonista resolvió el problema que lo metió allí"
        ),
        Preguntas(
            "Si pudieras ser un personaje de la historia mundial ¿quién serías?",
            "Un inventor famoso, como Steve Jobs",
            "Un novelista reconocido con el premio nobel, como Octavio Paz",
            "Un cientifíco destacado, como Luis Pasteur",
            "Un hombre de negocios exitoso, como Carlos Slim"
        ),
        Preguntas(
            "Si escogieras uno de los siguientes trabajos para el resto de tus días ¿cúal sería?",
            "Técnico de reparación de aparatos eléctricos y electrónicos (lavadoras, tvs, planchas, etc...)",
            "Profesor en una escuela secundaria",
            "Laboratorista en un hospital",
            "asistente de agenda de algún personaje importante"
        ),
        Preguntas(
            "¿Cuál de las siguientes causas es con la que te sientes más identificado?",
            "Luchas en contra de la extinción de áreras verdes y espacios públicos",
            "La paz mundial y el trato igualitario hacia cualquier tipo de persona",
            "Campañas para acabar con la desnutrición mundial y la obesidad de las personas",
            "Movimiento en contra de nuevos impuestos y reformas de las leyes"
        ),
        Preguntas(
            "¿Con cuál de las siguientes actividades artísticas o culturales te sientes más cómodo?",
            "Clases de instrumentos de música como guitarra, piano o batería",
            "Talleres de oratoria y declamación",
            "Carpintería, diseño de interiores o jardinería",
            "Algún curso de pintura o escultura"
        ),
        Preguntas(
            "Las personas que me conocen dicen que soy creativo y que, produzco ideas originales y divertidas.",
            "Si",
            "A veces",
            "no",
            "nunca"
        ),
        Preguntas(
            "Creo que el dinero no es lo más importante a tener en cuenta en la elección de una carrera.",
            "Si",
            "A veces",
            "no",
            "nunca"
        )
    )
}
