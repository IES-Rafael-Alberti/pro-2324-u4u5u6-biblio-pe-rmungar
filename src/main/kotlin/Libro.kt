package org.pebiblioteca

import java.util.Date

data class Libro(private val uuid:String, private val titulo:String, private val autor:String, private val publicacion: String, private var estado:EstadosLibros = EstadosLibros.DISPONIBLE){

    override fun toString(): String {
        return "$uuid - $titulo escrito por $autor el ${publicacion}, se encuentra ${estado.descripcion}"
    }

    fun mostrarID():String{
        return uuid
    }
    fun mostrarEstado():EstadosLibros{
        return estado
    }
    fun mostrarTitulo():String{
        return titulo
    }
    fun modificarEstado(){
        if (estado == EstadosLibros.DISPONIBLE) estado = EstadosLibros.OCUPADO
        else estado = EstadosLibros.DISPONIBLE
    }
}
