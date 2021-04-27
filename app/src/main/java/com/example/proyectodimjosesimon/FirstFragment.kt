package com.example.proyectodimjosesimon

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.bLogin).setOnClickListener {
            //Aqui hay que chequear si es correcto o no,
            // revisar el xml o json a ver si aparecen los datos.
            //si es correcto hay que mandar el navegador
            // si es incorrecto hay que sacar un mensaje de "mal contraseña o usuario"


            //Almaceno en un string los datos de usuario y contraseña del primer fragmento.

            var usuario: String = ""
            var contrasena: String = ""
            usuario = view.findViewById<EditText>(R.id.etxtUsr).getText().toString()
            contrasena = view.findViewById<EditText>(R.id.etxtPss).getText().toString()

            // Abrir el sharedpreferences para ver si el user+pass coincide.

            val usuariosCheck: SharedPreferences =
                (activity as MainActivity).getSharedPreferences("usuarios", Context.MODE_PRIVATE)
            val usr: String = usuariosCheck.getString("user", "maluser").toString()
            val pas: String = usuariosCheck.getString("pass", "malpass").toString()

            println(usr + " -> " + pas)
            println(usuario + " ->" + contrasena)

            val editor: SharedPreferences.Editor = usuariosCheck.edit()

            if (usuario != "" && contrasena != "") {
                if (usr == "maluser" && pas == "malpass" || usr == "" && pas =="") {
                    editor.clear()
                    editor.putString("user", usuario)
                    editor.putString("pass", contrasena)
                    editor.apply()
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                } else if (usuario == usr && contrasena == pas) {

                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

                } else {

                    Toast.makeText(activity, "Usuario no  registrado.", Toast.LENGTH_LONG).show()

                }
            } else {
                Toast.makeText(activity, "Algo ha ido mal", Toast.LENGTH_LONG).show()
            }


        }

        /* ******************************AQUI FALTA LLAMAR AL RESET FRAGMENT*****************************
        view.findViewById<Button>(R.id.bReset).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_ResetFragment)
        }*/

    }
}