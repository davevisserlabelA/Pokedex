package com.davelabela.pokedex.data.remote.responses


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVii(
    @SerialName("icons")
    val icons: Icons?,
    @SerialName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon?
)