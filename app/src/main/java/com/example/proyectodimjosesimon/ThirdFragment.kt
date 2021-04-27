package com.example.proyectodimjosesimon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ThirdFragment: Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)


        val bAdd = view.findViewById<Button>(R.id.bInsertar)
        val bModify = view.findViewById<Button>(R.id.bModificar)
        val bDelete = view.findViewById<Button>(R.id.bEliminar)

        val nombre = view.findViewById<EditText>(R.id.etProducto)
        val cantidad = view.findViewById<EditText>(R.id.etCantidad)

        val id = arguments?.getInt("id") ?: -1

        if (id == -1) {
            bModify.isEnabled = false
            bDelete.isEnabled = false
            bAdd.isEnabled = true
            activity?.setTitle("Añadir producto")

        } else {
            bModify.isEnabled = true
            bDelete.isEnabled = true
            bAdd.isEnabled = false
            activity?.setTitle("Modificar o eliminar producto")
            (activity as MainActivity).miVM.BuscarPorId(id)
            (activity as MainActivity).miVM.unProducto.observe(activity as MainActivity) {
                it?.let {
                    nombre.setText(it.nombre)
                    cantidad.setText((it.cantidad).toString())

                }
            }
        }



            view.findViewById<Button>(R.id.bInsertar).setOnClickListener {

                (activity as MainActivity).miVM.Insertar(
                        Producto(
                                nombre = nombre.text.toString(),
                                cantidad = cantidad.text.toString().toInt()
                        ))

                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }

            view.findViewById<Button>(R.id.bModificar).setOnClickListener {

                (activity as MainActivity).miVM.Actualizar(
                        Producto(id,
                                nombre.text.toString(),
                                cantidad.text.toString().toInt()
                        ))

                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }

            view.findViewById<Button>(R.id.bEliminar).setOnClickListener {
                (activity as MainActivity).miVM.Borrar(
                        Producto(id,
                                nombre.text.toString(),
                                 cantidad.text.toString().toInt()
                        ))
                findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
            }
        }
    }
