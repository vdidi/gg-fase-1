package com.zup.vitorhugo.listartopicosredditkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@SpringBootApplication
class ListarTopicosRedditKotlinApplication

fun main(args: Array<String>) {
	runApplication<ListarTopicosRedditKotlinApplication>(*args)

	val client = HttpClient.newBuilder().build();
	val request = HttpRequest.newBuilder()
			.uri(URI.create("https://www.reddit.com/r/programming/"))
			.build();
	val response = client.send(request, HttpResponse.BodyHandlers.ofString());

	val transformBody = TransformBody()

	val html : MutableList<String> = transformBody.transform(response.body())

	println("Lista não ordenada:")
	println(html)

	val newList = mergeSort(html)

	println("Lista ordenada:")
	println(newList)

	// Ordena com uma função
	//println(html.sort())
}

fun mergeSort (elt: List<String>): List<String> {
	if(elt.size <= 1){
		return elt
	}

	val meio = elt.size / 2
	var esq = elt.subList(0, meio)
	var dir = elt.subList(meio, elt.size)

	return merge(mergeSort(esq), mergeSort(dir))
}

fun merge(esquerda: List<String>, direita: List<String>): List<String> {
	var indexEsq = 0
	var indexDir = 0
	var listaOrdenada: MutableList<String> = mutableListOf();

	while (indexEsq < esquerda.count() && indexDir < direita.count()) {
		if(esquerda[indexEsq] <= direita[indexDir]){
			listaOrdenada.add(esquerda[indexEsq])
			indexEsq++
		}else{
			listaOrdenada.add(direita[indexDir])
			indexDir++
		}
	}

	while (indexEsq < esquerda.size) {
		listaOrdenada.add(esquerda[indexEsq])
		indexEsq++
	}

	while (indexDir < direita.size) {
		listaOrdenada.add(direita[indexDir])
		indexDir++
	}

	return listaOrdenada
}