package com.ridi.domain.videoshop.account.dto

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.node.MissingNode
import com.ridi.domain.videoshop.account.model.Account
import java.io.IOException


object AccountDeserializer : JsonDeserializer<Account>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): Account {
        val mapper = jp.codec as ObjectMapper
        val jsonNode = mapper.readTree<JsonNode>(jp)
        val password = readJsonNode(jsonNode, "password")
        return Account(
            id = readJsonNode(jsonNode, "id").asLong(),
            username = readJsonNode(jsonNode, "username").asText(),
            name = readJsonNode(jsonNode, "name").asText(),
            password = password.asText(""),
            phone = readJsonNode(jsonNode, "phone").asText(),
            isUsing2FA = readJsonNode(jsonNode, "isUsing2fa").asBoolean()
        )
    }

    private fun readJsonNode(jsonNode: JsonNode, field: String) =
        if (jsonNode.has(field)) jsonNode.get(field) else MissingNode.getInstance()
}

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
@JsonDeserialize(using = AccountDeserializer::class)
@JsonAutoDetect(
    fieldVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.NONE,
    isGetterVisibility = JsonAutoDetect.Visibility.NONE
)
@JsonIgnoreProperties(ignoreUnknown = true)
internal abstract class AccountMix
