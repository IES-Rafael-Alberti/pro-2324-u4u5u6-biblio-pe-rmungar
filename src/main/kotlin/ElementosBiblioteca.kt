package org.pebiblioteca

abstract class ElementosBiblioteca {
    abstract val uuid: String
    abstract val titulo: String
    abstract var estado: EstadosLibros
    abstract val publicacion: String

    fun mostrarTitulo():String{
        return titulo
    }
    fun mostrarID():String{
        return uuid
    }
    fun mostrarEstado():EstadosLibros{
        return estado
    }
    fun modificarEstado(){
        if (estado == EstadosLibros.DISPONIBLE) estado = EstadosLibros.OCUPADO
        else estado = EstadosLibros.DISPONIBLE
    }
}