package org.pebiblioteca

class Revista(override val uuid: String, override val titulo: String, override val publicacion: String, override var estado: EstadosLibros = EstadosLibros.DISPONIBLE):ElementosBiblioteca(), Prestable {
    override fun toString(): String {
        return "$uuid - $titulo prublicada el ${publicacion}, se encuentra ${estado.descripcion}"
    }


    override fun prestar(usuario: Usuario) {
        modificarEstado()
    }

    override fun devolver(usuario: Usuario) {
        modificarEstado()
    }
}