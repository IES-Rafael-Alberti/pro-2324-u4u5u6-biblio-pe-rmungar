package org.pebiblioteca

//  SE HA CAMBIADO EL TIPO A OBJETO
object RegistroPrestamos {
    val prestamosActuales = mutableListOf<Pair<Usuario, Libro>>()
    val historialPrestamos = mutableListOf<Pair<Usuario, Libro>>()


    fun registrarPrestamo(libro: Libro, usuario: Usuario){
        prestamosActuales.add(Pair(usuario, libro))
        historialPrestamos.add(Pair(usuario, libro))
    }

    fun devolverPrestamo(libro: Libro, usuario: Usuario){
        prestamosActuales.remove(Pair(usuario,libro))
    }

    fun mostrarRegistroEspecifico(libro: Libro){
        historialPrestamos.forEach {
            if (it.second == libro){
                println("${libro.mostrarTitulo()} prestado a ${it.first.nombre}")
            }
        }
    }

    fun mostrarRegistroUsuario(usuario: Usuario){
        historialPrestamos.forEach {
            if (it.first == usuario){
                println("Tomó prestado: ${it.second.mostrarTitulo()}")
            }
        }
    }
}