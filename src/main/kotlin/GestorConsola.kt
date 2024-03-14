package org.pebiblioteca

object GestorConsola {

    private val pedirTitulo = "Ingrese el título del objeto: "
    private val pedirAutor = "Ingrese el autor del objeto: "
    private val pedirPublicacion = "Ingrese la fecha de publicacion con el siguiente formato dd/mm/yyyy: "
    private val pedirId = "Ingrese el ID del objeto: "

//      PARA MOSTRAR POR PANTALLA   ------------------------------------------------------------------------------------
    fun mostrarPorConsola(texto:String){
        print(texto)
    }

    fun mostrarEntradaIncorrecta(){
        println("Ingrese un valor adecuado.")
    }

    fun mostrarMenuGestorBiblioteca(){
        println("--  GESTOR BIBLIOTECA  --")
        println("1  -  AÑADIR ELEMENTO\n2  -  ELIMINAR ELEMENTO\n3  -  PRESTAR ELEMENTO\n4  -  DEVOLVER ELEMENTO\n5  -  MOSTRAR ELEMENTO\n6  -  COMPROBAR DISPONIBILIDAD\n7  -  CREAR USUARIO\n8  -  MOSTRAR REGISTRO DE UN ELEMENTO\n9  -  MOSTRAR REGISTRO DE UN USUARIO\n<Enter> : Salir")
    }

//      PARA PEDIR Y COMPROBAR LAS ENTRADAS DEL USUARIO ----------------------------------------------------------------

    fun comprobarTitulo(): String{
        var titulo = ""
        while (titulo.isBlank() && titulo.isEmpty()) {
            mostrarPorConsola(pedirTitulo)
            titulo = readln().capitalizar()
        }
        return titulo
    }
    fun comprobarAutor(): String{
        var autor = ""
        while (autor.isBlank() && autor.isEmpty()) {
            mostrarPorConsola(pedirAutor)
            autor = readln().capitalizar()
        }
        return autor
    }

    fun comprobarPublicacion():String{
        var fecha:List<String> = listOf("1","1","1")
        while(fecha[0].toInt() !in 1..31 || fecha[1].toInt() !in 1..12 || fecha[2].toInt() !in 2001..2024){
            mostrarPorConsola(pedirPublicacion)
            fecha = readln().split("/")
        }
        return fecha.formatear()
    }

    fun comprobarIdDelElemento():String?{
        var id:String
        mostrarPorConsola(pedirId)
        id = readln()
        if (UtilidadesBiblioteca.listaIDs.isEmpty()){
            println("No hay objetos que prestar")
            return null
        }
        else{
            while (id !in UtilidadesBiblioteca.listaIDs){
                mostrarPorConsola(pedirId)
                id = readln()
                if (id == ""){
                    return null
                }
            }
            return id
        }
    }

//      MOSTRAR LAS COLECCIONES DE ELEMENTOS   -------------------------------------------------------------------------

    fun mostrarMenuMostrarElementos():String{
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
        val opciones = listOf("1","2","3","4","5","6","7","8","9","10")
        var eleccion = ""
        while (eleccion !in opciones){
            mostrarPorConsola("Ingrese el número de la opcion: ")
            eleccion = readln()
            if (eleccion == "") return 10
        }
        return eleccion.toInt()
    }

//      DATOS DEL USUARIO   --------------------------------------------------------------------------------------------

    fun pedirNombreUsuario():String{
        val nombre = comprobarNombre()
        return  nombre
    }

    fun pedirIdUsuario(): Int{
        val id = comprobarIdUsuario()
        return id
    }

    fun pedirId(): Int{
        mostrarPorConsola("Ingrese el id del usuario: ")
        val id = readln().toInt()
        return id
    }

    fun pedirIdElemento():String{
        mostrarPorConsola("Ingrese el id del objeto: ")
        val id = readln()
        return id
    }

    fun comprobarNombre():String {
        var nombre = ""
        while (nombre.isBlank() && nombre.isEmpty()) {
            mostrarPorConsola("Ingrese el nombre del usuario: ")
            nombre = readln().capitalizar()
        }
        return nombre
    }

    fun comprobarIdUsuario():Int{
        while (true){
            mostrarPorConsola("Ingrese el ID para el usuario: ")
            var id = readln()
            while(id !in ("100".."999") || UtilidadesBiblioteca.idsUsuarioEnUso.contains(id.toInt()) ){
                mostrarPorConsola("Ingrese el ID para el usuario: ")
                id = readln()
            }
            UtilidadesBiblioteca.idsUsuarioEnUso.add(id.toInt())
            return id.toInt()
        }
    }


//      ELEMENTOS   ----------------------------------------------------------------------------------------------------
    fun pedirTipoDeElemento():String{
        while (true){
            println()
            println("Quieres que sea: \n1  -  LIBRO\n2  -  REVISTA\n3  -  DVD")
            println("Ingresa el numero de la opción deseada: ")
            val tipo = readln()
            when(tipo){
                "1" -> return "L"
                "2" -> return "R"
                "3" -> return "D"
                else -> mostrarEntradaIncorrecta()
            }
        }
    }
}