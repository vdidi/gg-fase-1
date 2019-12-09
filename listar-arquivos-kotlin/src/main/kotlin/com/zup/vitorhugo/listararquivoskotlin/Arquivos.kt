package com.zup.vitorhugo.listararquivoskotlin

import java.io.File

class Arquivos () {

    fun listar(file: File) {
        file.walkTopDown().forEach { println(it) }
    }

}