package com.example.proyectodimjosesimon
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ResetFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_reset, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.bGuardar).setOnClickListener {
            val usuario = view.findViewById<EditText>(R.id.etxtResetUsr).getText().toString()
            val contrasena = view.findViewById<EditText>(R.id.etxtPss).getText().toString()

            //Existe el usuario? -->
            // sí existe: escribe contraseña nueva y pasa al fragmet2
            //no existe: mostrar mensaje: el usuario no existe.

            val usuarios: SharedPreferences =
                (activity as MainActivity).getSharedPreferences("usuarios", Context.MODE_PRIVATE)

            val user = usuarios.getString("user", "maluser").toString()
            val editor: SharedPreferences.Editor = usuarios.edit()
            println(user )

            if (usuario == user){
                editor.putString("pass", contrasena)
                editor.commit()
                findNavController().navigate(R.id.action_resetFragment_to_SecondFragment)
            }
            else{
                Toast.makeText(activity, "Usuario no registrado.", Toast.LENGTH_LONG).show()
            }



        }

    }
}