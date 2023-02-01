package com.davelabela.pokedex.data

import com.davelabela.pokedex.data.remote.responses.Pokemon
import com.davelabela.pokedex.data.remote.responses.PokemonList

interface PokemonRepository {
    suspend fun getPokemonTest(): Resource<Pokemon>

    suspend fun getPokemonList() : Resource<PokemonList>

    suspend fun getPokemonInfo(name : String) : Resource<Pokemon>

}