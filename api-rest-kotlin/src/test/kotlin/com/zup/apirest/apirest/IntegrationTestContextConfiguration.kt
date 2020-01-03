package com.zup.apirest.apirest

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan

@EnableAutoConfiguration
@ComponentScan("com.zup.apirest.apirest.*")
class IntegrationTestContextConfiguration () {}