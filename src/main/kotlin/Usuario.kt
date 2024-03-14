package org.pebiblioteca

import kotlin.random.Random

open class Usuario(var id: Int, val nombre: String) {

    val librosPrestados = mutableListOf<Libro>()

    fun agregarPrestamo(libro: Libro){
        librosPrestados.add(libro)
    }

    fun eliminarPrestamo(libro: Libro){
        librosPrestados.remove(libro)
    }

    fun mostrarPrestamos(){
        println("Registro de presamos de: $nombre")
        librosPrestados.forEach { println(it.toString()) }
    }
}