### Rodando o programa

Gere os pacotes necessários para o programa funcionar:

```sh
$ mvn package
$ java -jar target/springboot.jar
```

Para rodar o programa é necessário rodar os seguintes comandos:

- Gravar dados:

```sh
$ java -jar target/springboot.jar set <chave>:<valor>
```

- Recuperar dado:

```sh
$ java -jar target/springboot.jar get <chave>
```

- Excluir dado:

```sh
$ java -jar target/springboot.jar delete <chave>
```

- Listas dados:

```sh
$ java -jar target/springboot.jar list
```