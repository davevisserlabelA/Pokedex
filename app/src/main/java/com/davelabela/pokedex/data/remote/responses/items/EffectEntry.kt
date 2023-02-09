package com.davelabela.pokedex.data.remote.responses.items


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EffectEntry(
    @SerialName("effect")
    val effect: String,
    @SerialName("language")
    val language: Language,
    @SerialName("short_effect")
    val shortEffect: String
)