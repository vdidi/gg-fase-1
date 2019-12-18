package com.zup.apirest.apirest

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PessoaTest(

    ){
    @Mock lateinit var database: InMemoryDatabase

    @InjectMocks lateinit var controller: PessoaController

    @Before
    fun beforeEach() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test get all person`() {
        val pessoa = Pessoa("12312312301", "vitor")
        val pessoa1 = Pessoa("12312312301", "João")
        val pessoa2 = Pessoa("12312312301", "Maria")
        var list_pessoa: MutableList<Pessoa> = arrayListOf()
        list_pessoa?.add(pessoa)
        list_pessoa?.add(pessoa1)
        list_pessoa?.add(pessoa2)

        Mockito.`when`(database.visualizar()).thenReturn(list_pessoa)

        val response = controller.listarPessoas()
        println(response)
        Assert.assertEquals(list_pessoa[0], response[0])
        Assert.assertEquals(list_pessoa[1], response[1])
        Assert.assertEquals(list_pessoa[2], response[2])
    }

    @Test
    fun `test get person by CPF`() {
        val pessoa = Pessoa("12312312301", "vitor")
        val returnOptionalPessoa = Optional.of(pessoa)

        Mockito.`when`(database.vasualizarPorCPF(pessoa.cpf)).thenReturn(returnOptionalPessoa)

        val response = controller.listarPessoaPorCPF(pessoa.cpf)

        Assert.assertEquals(response.get().cpf,pessoa.cpf)
    }

    @Test
    fun `test save new person`() {
        val pessoa = Pessoa("12312312301", "vitor")
        val returnOptionalPessoa = Optional.of(pessoa)

        Mockito.`when`(database.cadastro(pessoa)).thenReturn(pessoa)
        // Testa se pessoa existe
        Mockito.`when`(database.vasualizarPorCPF(pessoa.cpf)).thenReturn(returnOptionalPessoa)
    }

    @Test
    fun `test update person`() {
        val pessoa = Pessoa("12312312301", "vitor")
        val newPessoa = Pessoa("12312312301", "Vitor Hugo Ramos")

        Mockito.`when`(database.cadastro(pessoa)).thenReturn(pessoa)
        // Testa se pessoa foi atualizada
        Mockito.`when`(database.atualizar(pessoa.cpf, newPessoa)).thenReturn(newPessoa)
    }

    @Test
    fun `test delete person`() {
        val pessoa = Pessoa("12312312301", "vitor")

        Mockito.`when`(database.cadastro(pessoa)).thenReturn(pessoa)
        Mockito.`when`(database.deletar(pessoa.cpf)).thenReturn(null)
    }

}