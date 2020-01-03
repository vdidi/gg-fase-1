package com.zup.apirest.apirest

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InMemoryDatabaseTest(

){
    @Mock lateinit var database: InMemoryDatabase

    @InjectMocks lateinit var controller: PessoaController

    lateinit var pessoa : Pessoa

    @Before
    fun setUp() {
        pessoa = Pessoa("12312312301", "Vitor Hugo")
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `should return all persons in the database`() {
        val pessoa = Pessoa("12312312301", "vitor")
        val pessoa1 = Pessoa("12312312301", "João")
        val pessoa2 = Pessoa("12312312301", "Maria")
        var list_pessoa: MutableList<Pessoa> = arrayListOf()

        list_pessoa?.add(pessoa)
        list_pessoa?.add(pessoa1)
        list_pessoa?.add(pessoa2)

        Mockito.`when`(database.visualizar()).thenReturn(list_pessoa)
    }

    @Test
    fun `should return an specific person by its CPF`() {
        val returnOptionalPessoa = Optional.of(pessoa)

        Mockito.`when`(database.visualizarPorCPF(pessoa.cpf)).thenReturn(returnOptionalPessoa)
    }

    @Test
    fun `should save the new person in database, if the person didn't already exist`() {
        val returnOptionalPessoa = Optional.of(pessoa)

        Mockito.`when`(database.cadastro(pessoa)).thenReturn(pessoa)

        // Testa se pessoa existe
        Mockito.`when`(database.visualizarPorCPF(pessoa.cpf)).thenReturn(returnOptionalPessoa)
    }

    @Test
    fun `should update specific person by its CPF, if the person exists`() {
        val newPessoa = Pessoa("12312312301", "Vitor Hugo")
        val returnOptionalPessoa = Optional.of(newPessoa)

        Mockito.`when`(database.visualizarPorCPF(pessoa.cpf)).thenReturn(returnOptionalPessoa)
        Mockito.`when`(database.atualizar(pessoa.cpf, newPessoa)).thenReturn(true)
    }

    @Test
    fun `should delete the person by its CPF`() {
        Mockito.`when`(database.cadastro(pessoa)).thenReturn(pessoa)
        Mockito.`when`(database.deletar(pessoa.cpf)).thenReturn(true)
    }

}