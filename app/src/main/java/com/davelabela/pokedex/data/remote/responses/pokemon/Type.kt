package com.davelabela.pokedex.data.remote.responses.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Type(
    @SerialName("slot")
    val slot: Int,
    @SerialName("type")
    val type: TypeX
)