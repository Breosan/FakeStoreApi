package com.iuristecnia.fakestoreapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.iuristecnia.fakestoreapi.databinding.ActivityMainBinding
import com.iuristecnia.fakestoreapi.databinding.ActivityProductoBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ButtonOk.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val id = intent.getStringExtra("id")
        if (id != null) {
            getProducto(id)
        } else {
            Toast.makeText(this, "No se encuentra el producto", Toast.LENGTH_SHORT).show()
        }

    }

    fun getProducto(id:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.apiService.getProducto(id)
            val response = call.execute()

            //El siguiente código es para que se ejecute en el hilo principal y lo muestre por pantalla
            runOnUiThread() {
                if (response.isSuccessful) {
                    val productoResponse = response.body()
                    if (productoResponse != null) {
                        Log.d("PRODUCTOS", productoResponse.toString())
                        binding.textInputId.setText(productoResponse.id)
                        binding.textTitle.text = productoResponse.title
                        binding.textInputDescripcion.setText(productoResponse.description)
                        binding.textInputCategoria.setText(productoResponse.category)
                        binding.textInputPrecio.setText(productoResponse.price)
                        Picasso.get().load(productoResponse.image).into(binding.image)
                    }

                } else {
                    Log.d("PRODUCTOS", response.message())
                }
            }
        }
    }
}