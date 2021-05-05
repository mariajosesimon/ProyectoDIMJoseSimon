package com.example.proyectodimjosesimon

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repositorio(val miDAO: DAOProducto) {
    val listaProductos: Flow<List<Producto>> = miDAO.MostrarTodas()

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun Insertar(peli: Producto){
        miDAO.Insertar(peli)
    }

    fun BuscarPorId(id: Int): Flow<Producto> {
        return miDAO.BuscarPorId(id)
    }

    fun MostrarTodas(): Flow<List<Producto>> {
        return miDAO.MostrarTodas()
    }


    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun Borrar(peli: Producto){
        miDAO.Borrar(peli)
    }

    @Suppress("RedudantSuspendModifier")
    @WorkerThread
    suspend fun Actualizar(peli: Producto){
        miDAO.Actualizar(peli)
    }

    fun BuscarPorCat(categoria: Int): Flow<List<Producto>> {
        return miDAO.BuscarPorCat(categoria)
    }


}