package org.pebiblioteca

class DVD(override val uuid: String, override val titulo: String, override val publicacion: String, override var estado: EstadosLibros = EstadosLibros.OCUPADO):ElementosBiblioteca() {

    override fun toString(): String {
        return "$uuid - $titulo, DVD estrenado el ${publicacion}, no es posible su préstamo"
    }
}