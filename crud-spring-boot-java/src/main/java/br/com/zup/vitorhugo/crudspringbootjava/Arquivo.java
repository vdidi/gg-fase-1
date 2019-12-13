package br.com.zup.vitorhugo.crudspringbootjava;

import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

public class Arquivo implements Serializable {

    private String key;
    private String value;

    public Arquivo(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Arquivo() {

    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Arquivo.class.getSimpleName() + "(", ")")
                .add("key=" + key)
                .add("value='" + value)
                .toString();
    }
}
