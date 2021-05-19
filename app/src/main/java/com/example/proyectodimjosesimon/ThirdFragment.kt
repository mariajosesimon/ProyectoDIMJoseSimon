package com.example.proyectodimjosesimon

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ThirdFragment : Fragment() {

    lateinit var kg: RadioButton;
    lateinit var gr: RadioButton;
    lateinit var paquete: RadioButton;


    val listaCategoriasImagenes = listOf<Int>(
        R.drawable.bolleria,
        R.drawable.carniceria,
        R.drawable.fruteria,
        R.drawable.hogar,
        R.drawable.panaderia,
        R.drawable.pescaderia,
        R.drawable.otros
    )

    lateinit var unidad: String;
    var categoriaEscogida = 1

    //@SuppressLint("ResourceType")
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

        val orientation = this.resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(activity as MainActivity, "estar en portrait", Toast.LENGTH_SHORT)
                .show()
         //   (activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.tres_vertical,ThirdFragment()).commit()



        } else {
            Toast.makeText(activity as MainActivity, "estar en landscape", Toast.LENGTH_SHORT)
                .show()
       //     (activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.tres_horizontal,ThirdFragment()).commit()

        }

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setHasOptionsMenu(true)

        val bAdd = view.findViewById<Button>(R.id.bInsertar)
        val bModify = view.findViewById<Button>(R.id.bModificar)
        val bDelete = view.findViewById<Button>(R.id.bEliminar)

        val nombre = view.findViewById<EditText>(R.id.etProducto)
        val cantidad = view.findViewById<EditText>(R.id.etCantidad)
        val categoria = view.findViewById<Spinner>(R.id.spCategoria)
        val imagenV = view.findViewById<ImageView>(R.id.imagenIV)
        val spinner = view.findViewById<Spinner>(R.id.spCategoria)

        kg = view.findViewById<RadioButton>(R.id.rbKg)
        gr = view.findViewById<RadioButton>(R.id.rbG)
        paquete = view.findViewById<RadioButton>(R.id.rbPaquetes)

        unidad = unidadSeleccionada()
        val listaSpinner =
            resources.getStringArray(R.array.imagenesCategorias) //Cargo el listado de Layout/values/imagenes
        val adap = ArrayAdapter(
            (activity as MainActivity),
            android.R.layout.simple_spinner_item,
            listaSpinner
        ) // cargo el spinner con la lista anterior
        spinner?.adapter = adap

        spinner?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categoriaEscogida = position

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("no no no ")
            }

        }

        val id = arguments?.getInt("id") ?: -1

        if (id == -1) {
            bModify.isVisible = false
            bDelete.isVisible = false
            bAdd.isEnabled = true
            activity?.setTitle("Añadir producto")

        } else {
            bModify.isEnabled = true
            bDelete.isEnabled = true
            bAdd.isVisible = false
            activity?.setTitle("Modificar o eliminar producto")
            (activity as MainActivity).miVM.BuscarPorId(id)
            (activity as MainActivity).miVM.unProducto.observe(activity as MainActivity) {
                it?.let {
                    nombre.setText(it.nombre)
                    cantidad.setText((it.cantidad).toString())
                    unidad = it.tipoUnidad
                    if (unidad == "Kg") {
                        kg.isChecked = true
                    } else if (unidad == "Gr") {
                        gr.isChecked = true
                    } else if (unidad == "Pack") {
                        paquete.isChecked = true
                    }

                    categoria.setSelection(it.categoria)
                    imagenV.setImageResource(it.imagen)


                }
            }
        }


        view.findViewById<Button>(R.id.bInsertar).setOnClickListener {
            unidad = unidadSeleccionada()

            (activity as MainActivity).miVM.Insertar(
                Producto(
                    nombre = nombre.text.toString(),
                    cantidad = cantidad.text.toString().toInt(),
                    tipoUnidad = unidad,
                    categoria = categoriaEscogida,
                    imagen = listaCategoriasImagenes[categoriaEscogida]
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
                    tipoUnidad = unidad,
                    categoria = categoriaEscogida,
                    imagen = listaCategoriasImagenes[categoriaEscogida]

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
                    tipoUnidad = unidad,
                    categoria = categoriaEscogida,
                    imagen = listaCategoriasImagenes[categoriaEscogida]

                )
            )
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }
    }

    fun unidadSeleccionada(): String {
        var id: String = ""
        if (kg.isChecked) {
            id = "Kg"
        } else if (gr.isChecked) {
            id = "Gr"
        } else if (paquete.isChecked) {
            id = "Pack"
        }
        return id
    }



}



