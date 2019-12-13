package br.com.zup.vitorhugo.crudspringbootjava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ArquivoRunner implements CommandLineRunner {
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private final ArquivoService service;

    public ArquivoRunner(ArquivoService service) {
        this.service = service;
    }

    @Override
    public void run(String[] args) {

        if(args.length > 0){

            // COLOCAR
            if(args[0].equals("set") && args.length >= 2){
                String argument = args[1];
                Integer index = argument.indexOf(':');
                String key = argument.substring(0, index);
                String value = argument.substring(index + 1);

                LOGGER.info(() -> String.valueOf(service.colocar(new Arquivo(key, value))));
            }
            // RECUPERAR
            else if(args[0].equals("get") && args.length >= 2){
                LOGGER.info(() -> service.recuperar(args[1]));
            }
            // REMOVER
            else if(args[0].equals("delete") && args.length >= 2){
                LOGGER.info(() -> String.valueOf(service.remover(args[1])));
            }
            // LISTAR
            else if(args[0].equals("list")){
                LOGGER.info(() -> service.listar());
            } else {
                System.out.println("Argumento incorreto");
            }
        }
    }
}
