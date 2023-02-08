package com.davelabela.pokedex.data.remote.responses.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationI(
    @SerialName("red-blue")
    val redBlue: RedBlue?,
    @SerialName("yellow")
    val yellow: Yellow?
)