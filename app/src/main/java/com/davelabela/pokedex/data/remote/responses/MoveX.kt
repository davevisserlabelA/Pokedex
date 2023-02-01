package com.davelabela.pokedex.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveX(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)