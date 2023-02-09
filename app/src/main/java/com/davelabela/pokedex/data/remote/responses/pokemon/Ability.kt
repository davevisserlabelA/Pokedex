package com.davelabela.pokedex.data.remote.responses.pokemon


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    @SerialName("ability")
    val ability: AbilityX?,
    @SerialName("is_hidden")
    val isHidden: Boolean?,
    @SerialName("slot")
    val slot: Int?
)