package com.davelabela.pokedex.data.remote.responses.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)