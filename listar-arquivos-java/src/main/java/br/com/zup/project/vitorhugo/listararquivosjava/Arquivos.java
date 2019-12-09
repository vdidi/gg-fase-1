package br.com.zup.project.vitorhugo.listararquivosjava;

import java.io.File;

public class Arquivos {

    public static void listar(File file) {
        File[] arquivos = file.listFiles();
        for (File file_name : arquivos) {

            if(!file_name.isDirectory()){
                System.out.println(file_name.getName());
            }else {
                System.out.println(file_name.getName());
                listar(file_name);
            }
        }
    }
}
