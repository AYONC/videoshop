package com.ridi.domain.videoshop.account.controller

import com.ridi.domain.videoshop.account.dto.AddAccountRequest
import com.ridi.domain.videoshop.account.dto.LoginRequest
import com.ridi.domain.videoshop.account.service.AccountService
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils.postForm
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
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

    @MockBean
    lateinit var accountService: AccountService

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
        mvc.perform(
            postForm("/account/login", request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
            .andDo(print())
            .andExpect(status().isFound)
    }

    @Test
    fun accountRegister() {
        val request = AddAccountRequest(username = "username", password = "password", matchPassword = "password", name = "name", phone = "phone")
        mvc.perform(
            postForm("/account/register", request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andDo(print())
            .andExpect(status().isOk)
    }
}
