package org.pebiblioteca

object GestorConsola {

    private const val PedirTitulo = "Ingrese el título del libro: "
    private const val PedirAutor = "Ingrese el autor del libro: "
    private const val PedirPublicacion = "Ingrese la fecha de publicacion con el siguiente formato dd/mm/yyyy: "
    private const val PedirId = "Ingrese el ID del libro: "

//      PARA MOSTRAR POR PANTALLA   ------------------------------------------------------------------------------------
    fun mostrarPorConsola(texto:String){
        print(texto)
    }

    private fun mostrarEntradaIncorrecta(){
        println("Ingrese un valor adecuado.")
    }

    fun mostrarMenuGestorBiblioteca(){
        println("--  GESTOR BIBLIOTECA  --")
        println("1  -  AÑADIR LIBRO\n2  -  ELIMINAR LIBRO\n3  -  PRESTAR LIBRO\n4  -  DEVOLVER LIBRO\n5  -  MOSTRAR LIBROS\n6  -  COMPROBAR DISPONIBILIDAD\n<Enter> : Salir")
    }

//      PARA PEDIR Y COMPROBAR LAS ENTRADAS DEL USUARIO ----------------------------------------------------------------

    fun comprobarTituloDelLibro(): String{
        var titulo = ""
        while (titulo.isBlank() && titulo.isEmpty()) {
            mostrarPorConsola(PedirTitulo)
            titulo = readln().capitalizar()
        }
        return titulo
    }
    fun comprobarAutorDelLibro(): String{
        var autor = ""
        while (autor.isBlank() && autor.isEmpty()) {
            mostrarPorConsola(PedirAutor)
            autor = readln().capitalizar()
        }
        return autor
    }

    fun comprobarPublicacionDelLibro():String{
        var fecha:List<String> = listOf("1","1","1")
        while(fecha[0].toInt() !in 1..31 || fecha[1].toInt() !in 1..12 || fecha[2].toInt() !in 2001..2024){
            mostrarPorConsola(PedirPublicacion)
            fecha = readln().split("/")
        }
        return fecha.formatear()
    }

    fun comprobarIdDelLibro():Int{
        var id = 0
        while (id !in 100..999){
            mostrarPorConsola(PedirId)
            id = readln().toInt()
        }
        return id
    }

//      MOSTRAR LAS COLECCIONES DE LIBROS   ----------------------------------------------------------------------------

    fun mostrarMenuMostrarLibros():String{
        mostrarPorConsola("Quieres ver: \n1    -   TODOS\n2    -   DISPONIBLES\n3    -   OCUPADOS")
        println()
        mostrarPorConsola("Ingresa el número de la opción: ")
        val tipo = readln().toInt()
        while (true){
            when(tipo){
                1 -> return "todos"
                2 -> return "disponibles"
                3 -> return "ocupados"
                else -> mostrarEntradaIncorrecta()
            }
        }
    }

//      FUNCIONAMIENTO GESTOR BIBLIOTECA    ----------------------------------------------------------------------------

    fun administrarEleccion():Int{
        var eleccion = ""
        while (eleccion !in "1".."7"){
            mostrarPorConsola("Ingrese el número de la opcion: ")
            eleccion = readln()
            if (eleccion == "") return 7
        }
        return eleccion.toInt()
    }
}