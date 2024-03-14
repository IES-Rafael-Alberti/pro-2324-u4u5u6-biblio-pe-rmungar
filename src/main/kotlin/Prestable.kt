package org.pebiblioteca

interface Prestable {
    fun prestar(usuario: Usuario)
    fun devolver(usuario: Usuario)
}