package org.pebiblioteca


class Libro( override val uuid:String, override val titulo:String, private val autor:String, override val publicacion: String, override var estado:EstadosLibros = EstadosLibros.DISPONIBLE):ElementosBiblioteca(), Prestable {

    override fun toString(): String {
        return "$uuid - $titulo escrito por $autor el ${publicacion}, se encuentra ${estado.descripcion}"
    }


    override fun prestar(usuario: Usuario) {
        modificarEstado()
    }

    override fun devolver(usuario: Usuario) {
        modificarEstado()
    }
}
