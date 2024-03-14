package org.pebiblioteca

//  SE HA CAMBIADO EL TIPO A OBJETO
class RegistroPrestamos: IGestorPrestamos {
    val prestamosActuales = mutableListOf<Pair<Usuario, ElementosBiblioteca>>()
    val historialPrestamos = mutableListOf<Pair<Usuario, ElementosBiblioteca>>()


    override fun registrarPrestamo(objeto: ElementosBiblioteca, usuario: Usuario){
        prestamosActuales.add(Pair(usuario, objeto))
        historialPrestamos.add(Pair(usuario, objeto))
    }

    override fun devolverPrestamo(objeto: ElementosBiblioteca, usuario: Usuario){
        prestamosActuales.remove(Pair(usuario,objeto))
    }

    override fun mostrarHistorial(){
        historialPrestamos.forEach { println("${it.first.nombre} tomó prestado: ${it.second.mostrarTitulo()}") }
    }

    override fun mostrarRegistroEspecifico(objeto: ElementosBiblioteca){
        historialPrestamos.forEach {
            if (it.second == objeto){
                println("${objeto.mostrarTitulo()} prestado a ${it.first.nombre}")
            }
        }
    }

    override fun mostrarRegistroUsuario(usuario: Usuario){
        historialPrestamos.forEach {
            if (it.first == usuario){
                println("Tomó prestado: ${it.second.mostrarTitulo()}")
            }
        }
    }
}