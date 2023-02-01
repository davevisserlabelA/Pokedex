package com.davelabela.pokedex.repository

import com.davelabela.pokedex.data.remote.responses.Pokemon
import com.davelabela.pokedex.data.remote.responses.PokemonList
import com.davelabela.pokedex.util.Constants.BASE_URL
import com.davelabela.pokedex.util.Constants.BULBASAUR_URL
import com.davelabela.pokedex.util.Resource
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : PokemonRepository {

    override suspend fun getPokemonTest(): Resource<Pokemon> {
        return try {
            Resource.Success(httpClient.get {
                url(BULBASAUR_URL)
            }
            )
        } catch (e: Exception) {
            return Resource.Error("Unknown error occured")
        }
    }

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        return try {
            Resource.Success(httpClient.get {
                url("$BASE_URL/pokemon?limit=${limit}&offset=${offset}")
            }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error("Unknown error occured")
        }
    }


    override suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        return try {
            Resource.Success(httpClient.get {
                url("$BASE_URL/pokemon/${name}")
            }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error("Unknown error occured")
        }
    }
}