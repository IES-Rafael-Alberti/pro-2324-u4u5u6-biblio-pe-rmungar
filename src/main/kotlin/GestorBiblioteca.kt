package org.pebiblioteca

import kotlin.io.path.fileVisitor


class GestorBiblioteca {
    private val catalogoDeLibros: MutableList<Libro> = mutableListOf()


    //  MÉTODOS DE GESTOR BIBLIOTECA
    private fun agregarLibro(){
        println()
        val id = UtilidadesBiblioteca.generarID()
        val titulo = GestorConsola.comprobarTituloDelLibro().capitalizar()
        val autor = GestorConsola.comprobarAutorDelLibro()
        val publicacion = GestorConsola.comprobarPublicacionDelLibro()

        val libro = Libro(id, titulo, autor, publicacion)
        catalogoDeLibros.add(libro)
        GestorConsola.mostrarPorConsola("Libro añadido")
    }

    private fun eliminarLibro(){
        println()
        val idLibro = GestorConsola.comprobarIdDelLibro()
        if (idLibro != null) {
            catalogoDeLibros.find { it.mostrarID() == idLibro }.let {
                catalogoDeLibros.remove(it)
                GestorConsola.mostrarPorConsola("Libro eliminado")
            }
        }else{
            GestorConsola.mostrarPorConsola("Cancelando...")
        }
    }

    private fun prestarLibro(usuario: Usuario){
        println()
        val idLibro = GestorConsola.comprobarIdDelLibro()
        if (idLibro != null) {
            catalogoDeLibros.find { it.mostrarID() == idLibro }.let {
                it!!.modificarEstado()
                RegistroPrestamos.registrarPrestamo(it, usuario)
                GestorConsola.mostrarPorConsola("Libro prestado a ${usuario.nombre}")
                println()
            }
        }
        else {
            GestorConsola.mostrarPorConsola("Cancelando...")
            println()
        }
    }

    private fun devolverLibro(usuario: Usuario){
        println()
        val idLibro = GestorConsola.comprobarIdDelLibro()
        if (idLibro != null) {
            RegistroPrestamos.prestamosActuales.find { it.second.mostrarID() == idLibro }.let {
                it!!.second.modificarEstado()
                RegistroPrestamos.devolverPrestamo(it.second, it.first)
                GestorConsola.mostrarPorConsola("Libro devuelto por ${usuario.nombre}")
            }
        }
        else{
            GestorConsola.mostrarPorConsola("Cancelando...")
            GestorConsola.mostrarPorConsola("")
        }
    }

    private fun comprobarDisponibilidad(){
        println()
        val idLibro = GestorConsola.comprobarIdDelLibro()
        if (idLibro != null) {
            catalogoDeLibros.find { it.mostrarID() == idLibro }.let {
                GestorConsola.mostrarPorConsola("${it!!.mostrarTitulo()} se encuentra ${it.mostrarEstado().descripcion}")
            }
        }
        else{
            GestorConsola.mostrarPorConsola("Cancelando...")
        }
    }

    private fun mostrarLibros(){
        println()
        val librosDisponibles = catalogoDeLibros.filter { it.mostrarEstado() == EstadosLibros.DISPONIBLE }
        val librosOcupados = catalogoDeLibros.filter { it.mostrarEstado() == EstadosLibros.OCUPADO }
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

    //      USUARIOS Y LIBROS
    fun crearUsuario(): Usuario{
        println()
        val id = GestorConsola.pedirIdUsuario()
        val nombre = GestorConsola.pedirNombreUsuario()
        val usuario = Usuario(id, nombre)
        UtilidadesBiblioteca.listaUsuarios.add(usuario)
        return usuario
    }

    fun obtenerUsuario():Usuario{
        println()
        val id = GestorConsola.pedirId()
        UtilidadesBiblioteca.listaUsuarios.find { it.id == id }.let {
            return it!!
        }
    }

    fun obtenerLibro():Libro{
        println()
        val id = GestorConsola.pedirIdLibro()
        catalogoDeLibros.find { it.mostrarID() == id }.let {
            return it!!
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
                3 -> {
                    val usuario = obtenerUsuario()
                    prestarLibro(usuario)
                }
                4 -> {
                    val usuario = obtenerUsuario()
                    devolverLibro(usuario)
                }
                5 -> mostrarLibros()
                6 -> comprobarDisponibilidad()
                7 -> crearUsuario()
                8 -> {
                    val libro = obtenerLibro()
                    RegistroPrestamos.mostrarRegistroEspecifico(libro)
                }
                9 -> {
                    val usuario = obtenerUsuario()
                    RegistroPrestamos.mostrarRegistroUsuario(usuario)
                }
                10 -> break
            }
            println()
        }
    }
}