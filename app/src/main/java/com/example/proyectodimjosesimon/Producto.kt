package com.example.proyectodimjosesimon

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName="tabla_productos")
data class Producto(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @NotNull @ColumnInfo(name = "nombre") var nombre: String = "",
        @ColumnInfo(name = "cantidad") var cantidad: Int,
        @ColumnInfo(name = "tipounidad") var tipoUnidad: String = ""



){}
