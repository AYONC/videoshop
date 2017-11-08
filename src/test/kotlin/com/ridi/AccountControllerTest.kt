package com.ridi

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.net.MediaType
import com.ridi.domain.account.dto.LoginRequest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    lateinit var mvc: MockMvc

    @Before
    fun setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build()
    }

    @Test
    fun accountLogin() {
        var request = LoginRequest(username = "test", password = "1234")
        var om = ObjectMapper()
        mvc.perform(
            post("/account/login")
                .contentType(MediaType.FORM_DATA.toString())
                .content(om.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.HTML_UTF_8.toString()))

    }

    @Test
    fun accountLogout() {

    }
}