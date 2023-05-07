package com.iuristecnia.fakestoreapi.database.entities

import com.google.gson.annotations.SerializedName

/*data class ProductosResponse(
    @SerializedName("productos")
    val productos: Productos
)

data class Productos(
    @SerializedName("producto")
    val productoList: List<Producto>
)*/



data class Producto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String
)


