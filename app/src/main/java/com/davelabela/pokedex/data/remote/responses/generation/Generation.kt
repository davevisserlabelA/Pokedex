package com.davelabela.pokedex.data.remote.responses.generation


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Generation(
    @SerialName("abilities")
    val abilities: List<String>,
    @SerialName("id")
    val id: Int,
    @SerialName("main_region")
    val mainRegion: MainRegion,
    @SerialName("moves")
    val moves: List<Move?>,
    @SerialName("name")
    val name: String,
    @SerialName("names")
    val names: List<Name>,
    @SerialName("pokemon_species")
    val pokemonSpecies: List<PokemonSpecy>,
    @SerialName("types")
    val types: List<Type>,
    @SerialName("version_groups")
    val versionGroups: List<VersionGroup>
)