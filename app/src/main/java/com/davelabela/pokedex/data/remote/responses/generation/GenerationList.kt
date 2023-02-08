package com.davelabela.pokedex.data.remote.responses.generation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationList(
    @SerialName("count")
    val count: Int,
    @SerialName("results")
    val results: List<Result>
)