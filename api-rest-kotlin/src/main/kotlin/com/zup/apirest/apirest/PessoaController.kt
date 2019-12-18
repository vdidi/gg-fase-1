package com.zup.apirest.apirest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController("kotlin")
@RequestMapping("/kotlin")
class PessoaController @Autowired constructor(private val database: InMemoryDatabase) {

    @GetMapping("/pessoa")
    fun listarPessoas(): List<Pessoa> {
        return database.visualizar()
    }

    @GetMapping("/pessoa/{cpf}")
    fun listarPessoaPorCPF(@PathVariable("cpf") cpf: String): Optional<Pessoa> {
        return database.vasualizarPorCPF(cpf)
    }

    @PostMapping("/pessoa")
    @ResponseStatus( code = HttpStatus.CREATED, reason = "Criado com sucesso!" )
    fun cadastrarPessoas(@Valid @RequestBody pessoa: PessoaRequest): Pessoa {
        return database.cadastro(Pessoa(pessoa.cpf, pessoa.nome))
    }

    @PutMapping("/pessoa/{cpf}")
    fun atualizarPessoa(@PathVariable("cpf") cpf: String, @Valid @RequestBody pessoa : PessoaRequest): Pessoa {
        return database.atualizar(cpf, Pessoa(pessoa.cpf, pessoa.nome))
    }

    @DeleteMapping("/pessoa/{cpf}")
    fun deletarPessoa(@PathVariable("cpf") cpf: String){
        database.deletar(cpf)
    }
}