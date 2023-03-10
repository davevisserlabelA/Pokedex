package com.davelabela.pokedex.data.remote.responses.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FireredLeafgreen(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_shiny")
    val backShiny: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_shiny")
    val frontShiny: String?
)