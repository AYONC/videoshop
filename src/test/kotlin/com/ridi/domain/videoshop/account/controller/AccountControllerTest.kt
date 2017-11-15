package com.ridi.domain.videoshop.account.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.net.MediaType
import com.ridi.domain.videoshop.account.dto.AddAccountRequest
import com.ridi.domain.videoshop.account.dto.LoginRequest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
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
        val request = LoginRequest(username = "test", password = "1234")
        val om = ObjectMapper()
        mvc.perform(
            post("/account/login")
                .contentType(MediaType.FORM_DATA.toString())
                .content(om.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isFound)

    }

    @Test
    fun accountRegister() {
        val request = AddAccountRequest(username = "username", password = "password", matchPassword = "password", name = "name", phone = "phone")
        val om = ObjectMapper()
        mvc.perform(
            post("/account/register")
                .contentType(MediaType.FORM_DATA.toString())
                .content(om.writeValueAsString(request)))
            .andDo(print())
            .andExpect(status().isOk)
    }
}
