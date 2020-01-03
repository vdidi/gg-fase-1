package com.zup.apirest.apirest

import org.springframework.stereotype.Component

import java.util.*
import kotlin.collections.ArrayList

@Component
class InMemoryDatabase {

    fun visualizar(): List<Pessoa> {
        return ArrayList(database.values)
    }

    fun visualizarPorCPF(cpf: String): Optional<Pessoa> {
        return Optional.ofNullable(database[cpf])
    }

    fun cadastro(pessoa: Pessoa): Pessoa {
        database.put(pessoa.cpf, pessoa)
        return pessoa
    }

    fun atualizar(cpf: String, pessoa: Pessoa): Boolean {
        if (database.get(pessoa.cpf) != null) {
            database.put(pessoa.cpf, pessoa)
            return true
        }
        return false
    }

    fun deletar(id: String): Boolean {
        if (database[id] != null) {
            database.remove(id)
            return true
        }
        return false
    }

    companion object {

        private val database = HashMap<String, Pessoa>()

    }
}
