package br.com.zup.vitorhugo.crudspringbootjava;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ArquivoService {
    private final ArquivoRepository repository;

    public ArquivoService(ArquivoRepository repository) {
        this.repository = repository;
    }

    public boolean colocar(Arquivo arquivo) {
        try {
            repository.escrever(arquivo);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    };

    public String recuperar(String key) {
        try{
            return repository.recuperar(key);
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean remover(String key) {
        try {
            return repository.remover(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String listar() {
        try {
            return repository.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
