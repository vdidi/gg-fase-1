package com.zup.vitorhugo.listararquivoskotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class ListarArquivosKotlinApplication

fun main(args: Array<String>) {

	runApplication<ListarArquivosKotlinApplication>(*args)

	var diretorio = File("./diretorios")

	val files = Arquivos()

	files.listar(diretorio)
}
