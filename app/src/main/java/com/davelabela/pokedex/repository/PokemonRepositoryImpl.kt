package com.davelabela.pokedex.repository

import com.davelabela.pokedex.data.remote.responses.generation.Generation
import com.davelabela.pokedex.data.remote.responses.generation.GenerationList
import com.davelabela.pokedex.data.remote.responses.items.Item
import com.davelabela.pokedex.data.remote.responses.items.ItemList
import com.davelabela.pokedex.data.remote.responses.pokemon.Pokemon
import com.davelabela.pokedex.data.remote.responses.pokemon.PokemonList
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

    override suspend fun getGenerationInfo(id: Int): Resource<Generation> {
        return try {
            Resource.Success(httpClient.get {
                url("$BASE_URL/generation/${id}")
            }
            )
        } catch (e: Exception) {
            return Resource.Error("Unknown error occured")
        }
    }

    override suspend fun getGenerationList(): Resource<GenerationList> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemList(limit: Int, offset: Int): Resource<ItemList> {
        return try {
            Resource.Success(httpClient.get {
                url("$BASE_URL/item?limit=${limit}&offset=${offset}")
            }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error("Unknown error occured")
        }
    }

    override suspend fun getItemInfo(name: String): Resource<Item> {
        return try {
            Resource.Success(httpClient.get {
                url("$BASE_URL/item/${name}")
            }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.Error("Unknown error occured")
        }
    }
}