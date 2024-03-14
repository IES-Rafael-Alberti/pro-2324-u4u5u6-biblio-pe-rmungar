package org.pebiblioteca

interface IGestorPrestamos {
    fun registrarPrestamo(objeto: ElementosBiblioteca, usuario: Usuario)

    fun devolverPrestamo(objeto: ElementosBiblioteca, usuario: Usuario)

    fun mostrarHistorial()

    fun mostrarRegistroEspecifico(objeto: ElementosBiblioteca)

    fun mostrarRegistroUsuario(usuario: Usuario)
}