package br.com.zup.project.vitorhugo.listararquivosjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ListarArquivosJavaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ListarArquivosJavaApplication.class, args);

		File path = new File("./diretorios");

		Arquivos a = new Arquivos();

		a.listar(path);

	}

}
