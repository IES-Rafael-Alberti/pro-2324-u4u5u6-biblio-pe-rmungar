package org.pebiblioteca

import kotlin.random.Random


class GestorBiblioteca {
    val catalogoDeLibros: MutableList<Libro> = mutableListOf()
    val registorDePrestamos: MutableList<Libro> = mutableListOf()
    private val listaIDs: MutableList<Int> = mutableListOf()

    //  GENERAR IDS PARA LOS LIBROS
    private fun generarID():Int{
        var id = Random.nextInt(100,999)
        while (!comprobarID(id)){
            id = Random.nextInt(100,999)
        }
        return id
    }
    private fun comprobarID(id:Int):Boolean{
        if (listaIDs.contains(id)) return false
        else {
            listaIDs.add(id)
            return true
        }
    }
    //  MÉTODOS DE GESTOR BIBLIOTECA
    fun agregarLibro(){
        val id = generarID()
        val titulo = GestorConsola.comprobarTituloDelLibro().capitalizar()
        val autor = GestorConsola.comprobarAutorDelLibro()
        val publicacion = GestorConsola.comprobarPublicacionDelLibro()

        val libro = Libro(id, titulo, autor, publicacion)
        catalogoDeLibros.add(libro)
        GestorConsola.mostrarPorConsola("Libro añadido")
    }

    fun eliminarLibro(){
        val idLibro = GestorConsola.comprobarIdDelLibro()
        catalogoDeLibros.find { it.uuid == idLibro }.let {
            catalogoDeLibros.remove(it)
            GestorConsola.mostrarPorConsola("Libro eliminado")
        }
    }

    fun prestarLibro(){
        val idLibro = GestorConsola.comprobarIdDelLibro()
        catalogoDeLibros.find { it.uuid == idLibro }.let {
            it!!.estado = EstadosLibros.OCUPADO
            registorDePrestamos.add(it)
            GestorConsola.mostrarPorConsola("Libro prestado")
        }
    }

    fun devolverLibro(){
        val idLibro = GestorConsola.comprobarIdDelLibro()
        registorDePrestamos.find { it.uuid == idLibro }.let {
            it!!.estado = EstadosLibros.DISPONIBLE
            registorDePrestamos.remove(it)
            GestorConsola.mostrarPorConsola("Libro devuelto")
        }
    }

    fun comprobarDisponibilidad(){
        val idLibro = GestorConsola.comprobarIdDelLibro()
        catalogoDeLibros.find { it.uuid == idLibro }.let {
            GestorConsola.mostrarPorConsola("${it!!.titulo} se encuentra ${it.estado.descripcion}")
        }
    }

    fun mostrarLibros(){
        val librosDisponibles = catalogoDeLibros.filter { it.estado == EstadosLibros.DISPONIBLE }
        val librosOcupados = catalogoDeLibros.filter { it.estado == EstadosLibros.OCUPADO }
        val filtro = GestorConsola.mostrarMenuMostrarLibros()
        when (filtro){
            "todos" -> {
                catalogoDeLibros.forEach {
                    GestorConsola.mostrarPorConsola(it.toString())
                }
            }
            "disponibles" -> {
                librosDisponibles.forEach {
                    GestorConsola.mostrarPorConsola(it.toString())
                }
            }
            else -> {
                librosOcupados.forEach {
                    GestorConsola.mostrarPorConsola(it.toString())
                }
            }
        }
    }

    //      FUNCIONAMIENTO

    fun funcionar() {
        while (true) {
            println()
            GestorConsola.mostrarMenuGestorBiblioteca()
            val eleccion = GestorConsola.administrarEleccion()
            when (eleccion) {
                1 -> agregarLibro()
                2 -> eliminarLibro()
                3 -> prestarLibro()
                4 -> devolverLibro()
                5 -> mostrarLibros()
                6 -> break
            }
        }
    }
}