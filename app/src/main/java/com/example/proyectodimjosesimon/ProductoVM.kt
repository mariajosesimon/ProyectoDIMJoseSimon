package com.example.proyectodimjosesimon

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ProductoVM(private val miRepositorio: Repositorio) : ViewModel() {

    val listaCompra: LiveData<List<Producto>> = miRepositorio.listaProductos.asLiveData() // para ver el listado observable
    lateinit var unProducto: LiveData<Producto> //cuando necesito ver un dato recuperado. observable


    fun Insertar(unProducto: Producto) = viewModelScope.launch { miRepositorio.Insertar(unProducto) }

    //viewModelScope --> se necesita lanzar desde una corrutina
    fun BuscarPorId(id: Int) = viewModelScope.launch {
        unProducto = miRepositorio.BuscarPorId(id).asLiveData()
    }


    fun Borrar(unProducto: Producto) = viewModelScope.launch {
        miRepositorio.Borrar(unProducto)
    }

    fun Actualizar(unProducto: Producto) = viewModelScope.launch {
        miRepositorio.Actualizar(unProducto)
    }


}
class ProductoViewModelFactory(private val repository: Repositorio): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductoVM::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProductoVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}