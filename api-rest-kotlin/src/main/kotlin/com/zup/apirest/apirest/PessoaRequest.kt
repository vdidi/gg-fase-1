package com.zup.apirest.apirest

import javax.validation.constraints.NotBlank

data class PessoaRequest(@field:NotBlank var cpf: String, @field:NotBlank var nome: String)