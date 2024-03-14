package org.pebiblioteca


data class Libro(val uuid:Int, val titulo:String, val autor:String, val publicacion: String, var estado:EstadosLibros = EstadosLibros.DISPONIBLE){

    override fun toString(): String {
        return "$uuid -> $titulo, escrito por $autor el $publicacion, se encuentra ${estado.descripcion}"
    }
}
