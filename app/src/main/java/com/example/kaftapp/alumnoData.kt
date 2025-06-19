package com.example.kaftapp

data class alumnoData(
    var nombre: String = "",
    var carrera: String = "",
    var matricula: String = "",
    var fotoId: Int = 0
)
{
    constructor(item: alumnoData) : this(
        nombre = item.nombre,
        carrera = item.carrera,
        matricula = item.matricula,
        fotoId = item.fotoId

    )
}