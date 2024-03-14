package org.pebiblioteca

open class GestorElementos<T:ElementosBiblioteca> {
    val catalogo = Catalogo
    private fun agregarElemento(t: T){
        println()
        catalogo.catalogoDeObjetos.add(t)
        GestorConsola.mostrarPorConsola("Objeto añadido")
        println()
    }

    fun eliminarElemento(t: T){
        println()
        catalogo.catalogoDeObjetos.remove(t)
        GestorConsola.mostrarPorConsola("Objeto eliminado")
    }

    fun mostrarElementos(){
        catalogo.catalogoDeObjetos.forEach {
            println(it.toString())
        }
    }
}