package com.davelabela.pokedex.repository

import com.davelabela.pokedex.data.remote.responses.generation.Generation
import com.davelabela.pokedex.data.remote.responses.generation.GenerationList
import com.davelabela.pokedex.data.remote.responses.pokemon.Pokemon
import com.davelabela.pokedex.data.remote.responses.pokemon.PokemonList
import com.davelabela.pokedex.util.Resource

interface PokemonRepository {
    suspend fun getPokemonTest(): Resource<Pokemon>

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>

    suspend fun getPokemonInfo(name: String): Resource<Pokemon>

    suspend fun getGenerationInfo(id: Int): Resource<Generation>

    suspend fun getGenerationList(): Resource<GenerationList>

}