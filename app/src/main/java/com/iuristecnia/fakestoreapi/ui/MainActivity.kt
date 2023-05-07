package com.iuristecnia.fakestoreapi.ui

import AdapterRvFunctions
import ListadoProductosAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.iuristecnia.fakestoreapi.database.entities.Producto
import com.iuristecnia.fakestoreapi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity(), AdapterRvFunctions {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListadoProductosAdapter
    private val productoList = mutableListOf<Producto>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSearch.setOnClickListener {
            getAllProductos()
        }
        initRecyclerView()
    }

    fun getAllProductos() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.apiService.getProductos()
            val response = call.execute()

            //El siguiente código es para que se ejecute en el hilo principal y lo muestre por pantalla
            runOnUiThread() {
                if (response.isSuccessful) {
                    val productosResponse = response.body()
                    if (productosResponse != null) {
                        Log.d("PRODUCTOS", productosResponse.toString())
                        productoList.clear()
                        productoList.addAll(productosResponse!!)
                        adapter.notifyDataSetChanged()
                    }

                } else {
                    Log.d("PRODUCTOS", response.message())
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = ListadoProductosAdapter(productoList, this)
        binding.rvProductos.layoutManager = LinearLayoutManager(this)
        binding.rvProductos.adapter = adapter
    }

    override fun onItemClick(producto: Producto) {
        val intent = Intent(this, ProductoActivity::class.java)
        intent.putExtra("id", producto.id)
        startActivity(intent)
        //finish()

        Log.d("PRODUCTO", producto.toString())
    }
}
