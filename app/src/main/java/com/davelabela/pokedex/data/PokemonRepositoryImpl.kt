package com.davelabela.pokedex.data

import com.davelabela.pokedex.data.network.BASE_URL
import com.davelabela.pokedex.data.remote.responses.Pokemon
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

private const val POKEMON = "${BASE_URL}/pokemon/1"

class PokemonRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : PokemonRepository {

    override suspend fun getPokemon(): Resource<Pokemon> {
        return try{
            Resource.Success(httpClient.get<Pokemon> {
                url(POKEMON)
            }
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}