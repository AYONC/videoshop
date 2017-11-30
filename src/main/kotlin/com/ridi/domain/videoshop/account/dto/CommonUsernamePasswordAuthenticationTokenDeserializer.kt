package com.ridi.domain.videoshop.account.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.node.MissingNode
import com.ridi.common.security.CommonUsernamePasswordAuthenticationToken
import com.ridi.domain.videoshop.account.model.Account
import org.springframework.security.core.GrantedAuthority
import java.io.IOException


object CommonUsernamePasswordAuthenticationTokenDeserializer : JsonDeserializer<CommonUsernamePasswordAuthenticationToken>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): CommonUsernamePasswordAuthenticationToken {
        val mapper = jp.codec as ObjectMapper
        val jsonNode = mapper.readTree<JsonNode>(jp)
        val authenticated = readJsonNode(jsonNode, "authenticated").asBoolean()
        val principalNode = readJsonNode(jsonNode, "principal")
        val principal = mapper.readValue<Any>(principalNode.toString(), object : TypeReference<Account>() {})

        val credentials = readJsonNode(jsonNode, "credentials").asText()
        val authorities = mapper.readValue<List<GrantedAuthority>>(
            readJsonNode(jsonNode, "authorities").toString(), object : TypeReference<List<GrantedAuthority>>() {
        })

        val token: CommonUsernamePasswordAuthenticationToken?
        token = CommonUsernamePasswordAuthenticationToken(
            principal = principal,
            credentials = credentials,
            authorities = if (authenticated) authorities else null
        )
        token.let { it.details = readJsonNode(jsonNode, "details") }
        return token
    }

    private fun readJsonNode(jsonNode: JsonNode, field: String) =
        if (jsonNode.has(field)) jsonNode.get(field) else MissingNode.getInstance()
}


@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
@JsonDeserialize(using = CommonUsernamePasswordAuthenticationTokenDeserializer::class)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
internal abstract class CommonUsernamePasswordAuthenticationTokenMix
