package com.davelabela.pokedex.repository

import com.davelabela.pokedex.data.remote.responses.Pokemon
import com.davelabela.pokedex.data.remote.responses.PokemonList
import com.davelabela.pokedex.util.Resource

interface PokemonRepository {
    suspend fun getPokemonTest(): Resource<Pokemon>

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>

    suspend fun getPokemonInfo(name: String): Resource<Pokemon>

}