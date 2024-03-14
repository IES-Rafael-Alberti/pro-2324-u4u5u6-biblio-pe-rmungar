package org.pebiblioteca


class GestorBiblioteca(val registro: RegistroPrestamos, val catalogo: Catalogo) {


    //  MÉTODOS DE GESTOR BIBLIOTECA
    private fun agregarElemento(){
        println()
        val tipoDeElemento = GestorConsola.pedirTipoDeElemento()
        val id = UtilidadesBiblioteca.generarID()
        val titulo = GestorConsola.comprobarTitulo().capitalizar()
        val autor = GestorConsola.comprobarAutor()
        val publicacion = GestorConsola.comprobarPublicacion()

        when(tipoDeElemento){
            "L" -> {
                val libro = Libro(id, titulo, autor, publicacion)
                catalogo.catalogoDeObjetos.add(libro)
            }
            "R" -> {
                val revista = Revista(id, titulo, publicacion)
                catalogo.catalogoDeObjetos.add(revista)
            }
            "D" -> {
                val dvd = DVD(id, titulo, publicacion)
                catalogo.catalogoDeObjetos.add(dvd)
            }
        }
        GestorConsola.mostrarPorConsola("Objeto añadido")
        println()
    }

    private fun eliminarLibro(){
        println()
        val idLibro = GestorConsola.comprobarIdDelElemento()
        if (idLibro != null) {
            catalogo.catalogoDeObjetos.find { it.mostrarID() == idLibro }.let {
                catalogo.catalogoDeObjetos.remove(it)
                GestorConsola.mostrarPorConsola("Objeto eliminado")

            }
        }else{
            GestorConsola.mostrarPorConsola("Cancelando...")
        }
        println()
    }

    private fun prestarObjeto(usuario: Usuario){
        println()
        val idLibro = GestorConsola.comprobarIdDelElemento()
        if (idLibro != null) {
            catalogo.catalogoDeObjetos.find { it.mostrarID() == idLibro }.let {
                it!!.modificarEstado()
                registro.registrarPrestamo(it, usuario)
                GestorConsola.mostrarPorConsola("Objeto prestado a ${usuario.nombre}")
                println()
            }
        }
        else {
            GestorConsola.mostrarPorConsola("Cancelando...")
            println()
        }
    }

    private fun devolverObjeto(usuario: Usuario){
        println()
        val idObjeto = GestorConsola.comprobarIdDelElemento()
        if (idObjeto != null) {
            registro.prestamosActuales.find { it.second.mostrarID() == idObjeto }.let {
                if (it!!.second is Libro || it.second is Revista) {
                    it.second.modificarEstado()
                    registro.devolverPrestamo(it.second, it.first)
                    GestorConsola.mostrarPorConsola("Objeto devuelto por ${usuario.nombre}")
                }
                else{
                    GestorConsola.mostrarPorConsola("Este objeto no se puede devolver")
                    println()
                }
            }
        }
        else{
            GestorConsola.mostrarPorConsola("Cancelando...")
            GestorConsola.mostrarPorConsola("")
        }
    }

    private fun comprobarDisponibilidad(){
        println()
        val idObjeto = GestorConsola.comprobarIdDelElemento()
        if (idObjeto != null) {
            catalogo.catalogoDeObjetos.find { it.mostrarID() == idObjeto }.let {
                GestorConsola.mostrarPorConsola("${it!!.mostrarTitulo()} se encuentra ${it.mostrarEstado().descripcion}")
            }
        }
        else{
            GestorConsola.mostrarPorConsola("Cancelando...")
        }
    }

    private fun mostrarObjetos(){
        println()
        val librosDisponibles = catalogo.catalogoDeObjetos.filter { it.mostrarEstado() == EstadosLibros.DISPONIBLE }
        val librosOcupados = catalogo.catalogoDeObjetos.filter { it.mostrarEstado() == EstadosLibros.OCUPADO }
        val filtro = GestorConsola.mostrarMenuMostrarElementos()
        when (filtro){
            "todos" -> {
                catalogo.catalogoDeObjetos.forEach {
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

    fun obtenerLibro():ElementosBiblioteca{
        println()
        val id = GestorConsola.pedirIdElemento()
        catalogo.catalogoDeObjetos.find { it.mostrarID() == id }.let {
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
                1 -> agregarElemento()
                2 -> eliminarLibro()
                3 -> {
                    val usuario = obtenerUsuario()
                    prestarObjeto(usuario)
                }
                4 -> {
                    val usuario = obtenerUsuario()
                    devolverObjeto(usuario)
                }
                5 -> mostrarObjetos()
                6 -> comprobarDisponibilidad()
                7 -> crearUsuario()
                8 -> {
                    val libro = obtenerLibro()
                    registro.mostrarRegistroEspecifico(libro)
                }
                9 -> {
                    val usuario = obtenerUsuario()
                    registro.mostrarRegistroUsuario(usuario)
                }
                10 -> break
            }
            println()
        }
    }
}