package com.davelabela.pokedex.data.remote.responses.items


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Item(
    @SerialName("attributes")
    val attributes: List<Attribute>,
    @SerialName("category")
    val category: Category,
    @SerialName("cost")
    val cost: Int,
    @SerialName("effect_entries")
    val effectEntries: List<EffectEntry>,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntry>,
    @SerialName("game_indices")
    val gameIndices: List<GameIndice>,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("names")
    val names: List<Name>,
    @SerialName("sprites")
    val sprites: Sprites
)