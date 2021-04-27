package com.example.proyectodimjosesimon

import androidx.room.*
import kotlinx.coroutines.flow.Flow
@Dao
interface DAOProducto {


    @Query("select * from tabla_productos order by nombre asc")
    fun MostrarTodas(): Flow<List<Producto>>
    @Query("select * from tabla_productos where id like :id")
    fun BuscarPorId(id: Int): Flow<Producto>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun Insertar(prod: Producto)

    @Update()
    suspend fun Actualizar(prod: Producto)

    @Delete()
    suspend fun Borrar(prod: Producto)

    @Query("delete from tabla_productos")
    suspend fun BorrarTodo()
}