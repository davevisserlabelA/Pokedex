package com.davelabela.pokedex.data.remote.responses.items


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)