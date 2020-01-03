package com.zup.apirest.apirest.api

import com.zup.apirest.apirest.*
import org.json.JSONObject
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@RunWith(SpringRunner::class)
@WebMvcTest(PessoaController::class)
class PessoaControllerTest : BaseIntegrationTest(){

    @Autowired
    private lateinit var mockMvc : MockMvc

    @Mock
    lateinit var database : InMemoryDatabase

    lateinit var pessoa : Pessoa

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        pessoa = Pessoa("12312312301", "Vitor Hugo Ramos")
    }

    @Test
    fun `should return all persons in the database`() {

        Mockito.`when`(database.visualizar()).thenReturn(listOf(pessoa))

        mockMvc.perform(MockMvcRequestBuilders.get("/kotlin/pessoas")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value("12312312301"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Vitor Hugo Ramos"))

    }

    @Test
    fun `should return an specific person by its CPF`() {

    }

    @Test
    fun `should save the new person in database, if the person didn't already exist`() {

    }

    @Test
    fun `should update specific person by its CPF, if the person exists`() {

    }

    @Test
    fun `should delete the person by its CPF`() {

    }

}