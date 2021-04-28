package com.example.proyectodimjosesimon

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    val database by lazy{BaseDatos.getDatabase(this)}
    val repositorio by lazy { Repositorio(database.miDAO()) }
    val miVM: ProductoVM by viewModels(){
        ProductoViewModelFactory(repositorio)
    }

    private var user: String = ""
    private var pass: String = ""

    override fun onPause() {
        super.onPause()
        val usuarios: SharedPreferences = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = usuarios.edit()
        editor.putString("user", user)
        editor.putString("pass", pass)
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        val usuarios: SharedPreferences = getSharedPreferences("usuarios", Context.MODE_PRIVATE)
        user = usuarios.getString("user", user).toString()
        pass = usuarios.getString("pass", pass).toString()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setTitle("A la compra!")

    }






}