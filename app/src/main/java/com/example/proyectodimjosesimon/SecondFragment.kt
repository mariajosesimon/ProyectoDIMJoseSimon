package com.example.proyectodimjosesimon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    lateinit var miRecyclerView: RecyclerView

    lateinit var toolbar: Toolbar

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_second, container, false)
       // toolbar = view?.findViewById<Toolbar>(R.id.toolbar)!!

     //   toolbar.inflateMenu(R.menu.menu_main)

        var misProductos: List<Producto> = listOf()
        (activity as MainActivity).miVM.listaCompra.observe(activity as MainActivity) { Producto ->
            Producto?.let {
                miRecyclerView = rootView.findViewById<RecyclerView>(R.id.tarjetitas)
                miRecyclerView.layoutManager = LinearLayoutManager(activity)
                miRecyclerView.adapter = Adaptador(it, activity as MainActivity)

            }

        }
        rootView.findViewById<FloatingActionButton>(R.id.FloatingAdd).setOnClickListener() {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
        activity?.setTitle("Lista de la compra")

      //  (activity as AppCompatActivity).setSupportActionBar(toolbar)
        setHasOptionsMenu(true)


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<FloatingActionButton>(R.id.FloatingAdd).setOnClickListener() {
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.Carniceria -> {
                Toast.makeText(activity as MainActivity, "has pulsado carniceria", Toast.LENGTH_SHORT).show()
            }
            R.id.Pescaderia -> {
                Toast.makeText(activity as MainActivity, "has pulsado pescaderia", Toast.LENGTH_SHORT).show()
            }
            R.id.Bolleria -> {
                Toast.makeText(activity as MainActivity, "has pulsado informacion", Toast.LENGTH_SHORT).show()
            }
            R.id.Fruteria -> {
                Toast.makeText(activity as MainActivity, "has pulsado informacion", Toast.LENGTH_SHORT).show()
            }
            R.id.Hogar -> {
                Toast.makeText(activity as MainActivity, "has pulsado informacion", Toast.LENGTH_SHORT).show()
            }
            R.id.Otros -> {
                Toast.makeText(activity as MainActivity, "has pulsado informacion", Toast.LENGTH_SHORT).show()
            }

        }
        return true
    }
}