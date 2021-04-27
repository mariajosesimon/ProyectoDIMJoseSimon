package com.example.proyectodimjosesimon

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Producto::class), version = 1, exportSchema = false)
abstract class BaseDatos: RoomDatabase() {

    abstract fun miDAO(): DAOProducto

    /* Además, incluye un método abstracto que tenga 0 argumentos y muestre la clase del
     DAO y un compagnion object, que es un objeto común a todas las instancias de esa
     clase. Dentro va a estar la instancia de base de datos y una función getDatabase que lo
     que hace es prevenir tener multiples instancias de la base de datos. Para ello mira si
     esta creada la instancia y si no es así la crea.*/

    companion object{
        @Volatile
        private var INSTANCE: BaseDatos?=null

        fun getDatabase(context: Context):BaseDatos{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDatos::class.java,
                    "producto_database"

                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}