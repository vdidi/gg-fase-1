package com.zup.apirest.apirest

import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController()
@RequestMapping("/kotlin")
open class PessoaController @Autowired constructor(private val database: InMemoryDatabase) {

    @GetMapping("/pessoas")
//    @ResponseStatus()
    fun listarPessoas(): ResponseEntity<List<Pessoa>> {
        val result =  database.visualizar()
        return  ResponseEntity.ok(result)
    }

    @GetMapping("/pessoas/{cpf}")
    fun listarPessoaPorCPF(@PathVariable("cpf") cpf: String): ResponseEntity<String> {
        val result = database.visualizarPorCPF(cpf)
        if (!result.isEmpty) {
            val res = JSONObject("{'cpf': '" + result.get().cpf + "', 'nome':'" + result.get().nome + "'}")
            return  ResponseEntity.ok(res.toString())
        }
        return  ResponseEntity<String>("Pessoa não encontrada", HttpStatus.NOT_FOUND)
    }

    @PostMapping("/pessoas")
    fun cadastrarPessoas(@Valid @RequestBody pessoa: PessoaRequest): ResponseEntity<String> {
        val result = database.cadastro(Pessoa(pessoa.cpf, pessoa.nome))
        if (result != null) {
            return  ResponseEntity<String>("Criado com sucesso!", HttpStatus.CREATED)
        }
        return  ResponseEntity<String>("Erro ao criar pessoa", HttpStatus.BAD_REQUEST)
    }

    @PutMapping("/pessoas/{cpf}")
    fun atualizarPessoa(@PathVariable("cpf") cpf: String, @Valid @RequestBody pessoa : PessoaRequest): ResponseEntity<String> {
        if (database.atualizar(cpf, Pessoa(pessoa.cpf, pessoa.nome))) {
            return ResponseEntity.ok("Pessoa deletada com sucesso!")
        }
        return ResponseEntity<String>("Pessoa não encontrada", HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/pessoas/{cpf}")
    fun deletarPessoa(@PathVariable("cpf") cpf: String): ResponseEntity<String>{
        if (database.deletar(cpf)) {
            return ResponseEntity.ok("Pessoa deletada com sucesso!")
        }
        return ResponseEntity<String>("Pessoa não encontrada", HttpStatus.NOT_FOUND)
    }
}