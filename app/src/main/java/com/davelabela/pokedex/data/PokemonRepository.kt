package com.davelabela.pokedex.data

import com.davelabela.pokedex.data.remote.responses.Pokemon

interface PokemonRepository {
    suspend fun getPokemon(): Resource<Pokemon>
}