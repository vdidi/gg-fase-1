package br.com.zup.vitorhugo.crudspringbootjava;

import java.io.IOException;

public interface ArquivoRepository {
    boolean escrever(Arquivo arquivo) throws IOException;

    String recuperar(String key_arg) throws IOException, ClassNotFoundException;

    boolean remover(String key_arg);

    String listar() throws IOException;
}
