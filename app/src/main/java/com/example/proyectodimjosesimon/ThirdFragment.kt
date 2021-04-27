package com.example.proyectodimjosesimon

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ThirdFragment : Fragment() {

    lateinit var kg: RadioButton;
    lateinit var g: RadioButton;
    lateinit var paquete: RadioButton;

    lateinit var unidad: String;

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

        kg = view.findViewById<RadioButton>(R.id.rbKg)
        g = view.findViewById<RadioButton>(R.id.rbG)
        paquete = view.findViewById<RadioButton>(R.id.rbPaquetes)

        val seleccion = view.findViewById<RadioGroup>(R.id.RGMedida).getTag()

        unidad = unidadSeleccionada()

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
            unidad = unidadSeleccionada()

            (activity as MainActivity).miVM.Insertar(
                Producto(
                    nombre = nombre.text.toString(),
                    cantidad = cantidad.text.toString().toInt(),
                    tipoUnidad = unidad
                )
            )

            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.bModificar).setOnClickListener {
            unidad = unidadSeleccionada()
            (activity as MainActivity).miVM.Actualizar(
                Producto(
                    id,
                    nombre.text.toString(),
                    cantidad.text.toString().toInt(),
                    tipoUnidad = unidad
                )
            )

            println("unidad" + unidad.toString())

            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.bEliminar).setOnClickListener {
            unidad = unidadSeleccionada()
            (activity as MainActivity).miVM.Borrar(
                Producto(
                    id,
                    nombre.text.toString(),
                    cantidad.text.toString().toInt(),
                    tipoUnidad = unidad

                )
            )
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }
    }

    fun unidadSeleccionada(): String {
        var id: String = ""
        if (kg.isChecked) {
             id= "Kg"
        } else if (g.isChecked) {
            id=  "Gr"
        } else if (paquete.isChecked) {
            id= "Pack"
        }
        return id
    }
}
