package com.iuristecnia.fakestoreapi.ui


import com.iuristecnia.fakestoreapi.database.entities.Producto
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    fun getProductos(): Call<List<Producto>>

    @GET("products/{id}")
    fun getProducto(@Path("id") id: String): Call<Producto>
}