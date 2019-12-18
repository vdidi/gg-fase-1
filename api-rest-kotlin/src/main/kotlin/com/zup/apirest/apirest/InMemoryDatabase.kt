package com.zup.apirest.apirest

import org.springframework.stereotype.Component

import java.util.*
import kotlin.collections.ArrayList

@Component
class InMemoryDatabase {

    fun visualizar(): List<Pessoa> {
        return ArrayList(database.values)
    }

    fun vasualizarPorCPF(cpf: String): Optional<Pessoa> {
        return Optional.ofNullable(database[cpf])
    }

    fun cadastro(pessoa: Pessoa): Pessoa {
        database.put(pessoa.cpf, pessoa)
        return pessoa
    }

    fun atualizar(cpf: String, pessoa: Pessoa): Pessoa {
        if (database.get(pessoa.cpf) != null) {
            database.put(pessoa.cpf, pessoa)
        }
        println(pessoa)
        return pessoa
    }

    fun deletar(id: String): Unit? {
        if (database[id] != null) {
            database.remove(id)
        }
        return null
    }

    companion object {

        private val database = HashMap<String, Pessoa>()

        init {
            database["15447424011"] = Pessoa("15447424011", "João")
            database["57561822090"] = Pessoa("57561822090", "Pedro")
            database["32613535032"] = Pessoa("32613535032", "Maria")
        }
    }
}
