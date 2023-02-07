package com.davelabela.pokedex.data.remote.responses.generation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VersionGroup(
    @SerialName("name")
    val name: String?,
    @SerialName("url")
    val url: String?
)