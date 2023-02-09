package com.davelabela.pokedex.data.remote.responses.items


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlavorTextEntry(
    @SerialName("language")
    val language: Language,
    @SerialName("text")
    val text: String,
    @SerialName("version_group")
    val versionGroup: VersionGroup
)