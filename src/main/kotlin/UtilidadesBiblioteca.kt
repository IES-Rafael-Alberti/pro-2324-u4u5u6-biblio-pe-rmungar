package org.pebiblioteca

import kotlin.random.Random

class UtilidadesBiblioteca {
    companion object{
        val listaIDs: MutableList<String> = mutableListOf() //Lista para almacenar los ids ya generados
        fun generarID():String{
            var id:Any = Random.nextInt(100,999)
            val letrasid = generarLetrasID()
            id = "$id-$letrasid"
            while (!comprobarID(id.toString())){
                id = Random.nextInt(100,999)
            }
            return id.toString()
        }
        fun comprobarID(id:String):Boolean{
            if (listaIDs.contains(id)) return false
            else {
                listaIDs.add(id)
                return true
            }
        }
        fun generarLetrasID():String{
            val letras = listOf("A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z")
            var letrasGeneradas = ""
            repeat(3){
                letrasGeneradas += letras.random()
            }
            return  letrasGeneradas
        }
    }
}