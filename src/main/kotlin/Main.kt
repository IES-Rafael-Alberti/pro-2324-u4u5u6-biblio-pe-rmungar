package org.pebiblioteca

/**
 * Función para capitalizar la primera letra de cada palabra en una cadena.
 * @return La cadena con la primera letra de cada palabra capitalizada.
 */
fun String.capitalizar(): String {
    // Convierte la cadena en una lista de caracteres.
    val texto = this.toList()
    var palabraCapitalizada = ""
    var posicion = 0
    // Itera sobre cada caracter en la lista.
    for (caracter in texto) {
        // Si es el primer caracter de la cadena, lo capitaliza.
        if (caracter == texto[0]) {
            palabraCapitalizada += caracter.toString().uppercase()
        } else if (texto[posicion - 1] == ' ') {
            // Si el caracter anterior es un espacio, capitaliza el caracter actual.
            palabraCapitalizada += caracter.toString().uppercase()
        } else {
            // Si no, convierte el caracter actual en minúscula.
            palabraCapitalizada += caracter.lowercase()
        }
        posicion++
    }
    return palabraCapitalizada
}

fun List<Any>.formatear():String{
    var cont = 0
    var listaFormateada = ""
    repeat(this.size){
        if (cont == this.size-1) {
            listaFormateada += this[cont]
        }
        else{
            listaFormateada += "${this[cont]}/"
        }
        cont++
    }
    return listaFormateada
}

fun main() {
    try {
        val gestor = GestorBiblioteca()
        gestor.funcionar()
    }
    catch (e:Exception) {
        println(e.message)
    }
}