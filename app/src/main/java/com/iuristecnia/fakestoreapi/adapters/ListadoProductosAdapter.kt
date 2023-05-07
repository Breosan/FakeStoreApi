import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iuristecnia.fakestoreapi.R
import com.iuristecnia.fakestoreapi.database.entities.Producto
import com.iuristecnia.fakestoreapi.databinding.ItemProductoBinding

class ListadoProductosAdapter(private val productos: List<Producto>, private val listener:AdapterRvFunctions ) : RecyclerView.Adapter<ListadoProductosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListadoProductosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListadoProductosViewHolder(layoutInflater.inflate(R.layout.item_producto, parent, false))
    }
    override fun getItemCount(): Int {
        Log.d("ADAPTER", "Cantidad de productos: ${productos.size}")
        return productos.size
    }



    override fun onBindViewHolder(holder: ListadoProductosViewHolder, position: Int) {
        val item = productos[position]
        holder.bind(item, listener)
    }
}

class ListadoProductosViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemProductoBinding.bind(view)
    fun bind(producto: Producto, listener: AdapterRvFunctions) {
        //Picasso.get().load(image).into(binding.ivDog)
        binding.textId.text = producto.id.toString()
        binding.textTitle.text = producto.title.toString()
        binding.textPrice.text = producto.price.toString()
        itemView.setOnClickListener {
            Log.d("VIEWHOLDER", "Click en producto: $producto")
            listener.onItemClick(producto)
        }


        Log.d("VIEWHOLDER", "Enlazando producto: $producto")

    }
}


interface AdapterRvFunctions {
    fun onItemClick(producto: Producto)
}

