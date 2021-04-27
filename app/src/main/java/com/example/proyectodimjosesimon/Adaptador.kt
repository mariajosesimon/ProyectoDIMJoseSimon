package com.example.proyectodimjosesimon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class Adaptador(var listaCompra: List<Producto>, val actividad: MainActivity) :
    RecyclerView.Adapter<Adaptador.ViewHolder>() {

    //v = cada uno de los holder = c/item.
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var txtnombre: TextView
        var txtCantidad: TextView

        var posicion: Int =0

        init {
            txtnombre = v.findViewById(R.id.idProducto)
            txtCantidad = v.findViewById(R.id.idCantidad)

            posicion = 0
            v.setOnClickListener {

                val bundle = bundleOf("id" to this.posicion)
                (actividad as MainActivity).findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_SecondFragment_to_ThirdFragment, bundle)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.posicion = listaCompra[position].id
        holder.txtnombre.text = listaCompra[position].nombre
        holder.txtCantidad.text = listaCompra[position].cantidad.toString()



    }

    override fun getItemCount(): Int {
        return listaCompra.count()
    }


}

