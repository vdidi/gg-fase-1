package com.zup.apirest.apirest

import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.web.context.WebApplicationContext

@ContextConfiguration(classes = [IntegrationTestContextConfiguration::class])
abstract class BaseIntegrationTest() {
    @Autowired
    private lateinit var context: WebApplicationContext

    protected lateinit var mockMvc: MockMvc

    @get:Rule
    var restDocumentation = JUnitRestDocumentation()

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Before
    fun setUp() {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .alwaysDo<DefaultMockMvcBuilder>(JacksonResultHandlers.prepareJackson(objectMapper))
                .alwaysDo<DefaultMockMvcBuilder>(commonDocumentation())

                .apply<DefaultMockMvcBuilder>(
                        MockMvcRestDocumentation.documentationConfiguration(restDocumentation)
                                .uris()
                                .and().snippets()
                                .withDefaults(
                                        CliDocumentation.curlRequest(),
                                        HttpDocumentation.httpRequest(),
                                        HttpDocumentation.httpResponse(),
                                        AutoDocumentation.requestFields(),
                                        AutoDocumentation.responseFields(),
                                        AutoDocumentation.pathParameters(),
                                        AutoDocumentation.requestParameters(),
                                        AutoDocumentation.description(),
                                        AutoDocumentation.methodAndPath(),
                                        buildSection()
                                )
                )
                .build()
    }

    private fun commonDocumentation(): RestDocumentationResultHandler {
        return MockMvcRestDocumentation.document(
                "{class-name}/{method-name}",
                Preprocessors.preprocessRequest(
                        ResponseModifyingPreprocessors.replaceBinaryContent(),
                        ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                        Preprocessors.prettyPrint()
                ),
                Preprocessors.preprocessResponse(
                        ResponseModifyingPreprocessors.replaceBinaryContent(),
                        ResponseModifyingPreprocessors.limitJsonArrayLength(objectMapper),
                        Preprocessors.prettyPrint()
                )
        )
    }

    private fun buildSection(): SectionSnippet {
        return AutoDocumentation.sectionBuilder()
                .snippetNames(
                        SnippetRegistry.PATH_PARAMETERS,
                        SnippetRegistry.HTTP_REQUEST,
                        SnippetRegistry.REQUEST_PARAMETERS,
                        SnippetRegistry.REQUEST_FIELDS,
                        SnippetRegistry.HTTP_RESPONSE,
                        SnippetRegistry.RESPONSE_FIELDS
                )
                .skipEmpty(true)
                .build()
    }
}