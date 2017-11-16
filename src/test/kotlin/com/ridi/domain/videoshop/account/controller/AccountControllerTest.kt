package com.ridi.domain.videoshop.account.controller

import com.nhaarman.mockito_kotlin.given
import com.ridi.common.dummyAccount
import com.ridi.domain.videoshop.account.dto.AddCustomerRequest
import com.ridi.domain.videoshop.account.dto.AddStaffRequest
import com.ridi.domain.videoshop.account.dto.LoginRequest
import com.ridi.domain.videoshop.account.repository.AccountRepository
import com.ridi.domain.videoshop.account.service.CustomerService
import com.ridi.domain.videoshop.account.service.StaffService
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils.postForm
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
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
    lateinit var staffService: StaffService
    @MockBean
    lateinit var customerService: CustomerService
    @MockBean
    lateinit var accountRepo: AccountRepository
    lateinit var mvc: MockMvc

    @Before
    fun setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build()
    }

    @Test
    fun accountLogin() {
        val account = dummyAccount()
        given(accountRepo.findByUsername(anyString()))
            .willReturn(listOf(account))

        val request = LoginRequest(username = account.username, password = account.password)
        mvc.perform(
            postForm("/account/login", request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
            .andDo(print())
            .andExpect(status().isFound)
    }

    @Test
    fun staffRegister() {
        val request = AddStaffRequest(username = "username", password = "password", matchPassword = "password", name = "name", phone = "phone")
        mvc.perform(
            postForm("/account/staff/register", request)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andDo(print())
            .andExpect(status().isOk)
    }
}
