package com.example.kaftapp

data class itemData(
    var txtCategoria: String = "",
    var txtDescripcion: String = "",
    var imageId: Int = 0
){
    constructor(item: itemData) : this(
        txtCategoria = item.txtCategoria,
        txtDescripcion = item.txtDescripcion,
        imageId = item.imageId
    )
}
