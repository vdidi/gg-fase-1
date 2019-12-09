package com.zup.vitorhugo.listartopicosredditjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@SpringBootApplication
public class ListarTopicosRedditJavaApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ListarTopicosRedditJavaApplication.class, args);

		String resposta = "";

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.reddit.com/r/programming/"))
				.build();

		HttpResponse<String> response =
				client.send(request, HttpResponse.BodyHandlers.ofString());

		resposta = response.body();

		TransformBody transformBody = new TransformBody();
		
		List<String> titulos = transformBody.transform(resposta);

		System.out.println("Elementos não ordenados:");
		System.out.println(titulos);

		// Transforma List para Array de string
		String[] arr = titulos.toArray(new String[0]);

		mergeSort(arr);

		System.out.println("Elementos ordenados usando o algoritmo de ordenação Merge Sort:");
		System.out.println(Arrays.toString(arr));

	}

//	Método de ordenação
//	private static void sortElements (List<String> elementos) {
//		Collections.sort(elementos);
//
//		elementos.forEach(elt -> System.out.println(elt));
//	}

	public static void mergeSort(String[] elementos) {
		if (elementos.length >= 2) {
			String[] l = new String[elementos.length / 2];
			String[] r = new String[elementos.length - elementos.length / 2];

			for (int i = 0; i < l.length; i++) {
				l[i] = elementos[i];
			}

			for (int i = 0; i < r.length; i++) {
				r[i] = elementos[i + elementos.length / 2];
			}

			mergeSort(l);
			mergeSort(r);
			merge(elementos, l, r);
		}
	}

	public static void merge(String[] elementos, String[] l, String[] r) {
		int a = 0;
		int b = 0;
		for (int i = 0; i < elementos.length; i++) {
			if (b >= r.length || (a < l.length && l[a].compareToIgnoreCase(r[b]) < 0)) {
				elementos[i] = l[a];
				a++;
			} else {
				elementos[i] = r[b];
				b++;
			}
		}
	}
	

}
