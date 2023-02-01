package com.davelabela.pokedex.data

import com.davelabela.pokedex.data.network.BASE_URL
import com.davelabela.pokedex.data.remote.responses.Pokemon
import com.davelabela.pokedex.data.remote.responses.PokemonList
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

private const val POKEMON = "${BASE_URL}/pokemon/1"

class PokemonRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : PokemonRepository {

    override suspend fun getPokemonTest(): Resource<Pokemon> {
        return try{
            Resource.Success(httpClient.get {
                url(POKEMON)
            }
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getPokemonList(): Resource<PokemonList> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonInfo(name: String): Resource<Pokemon> {
        return try{
            Resource.Success(httpClient.get {
                url("${BASE_URL}/pokemon/${name}")
            }
            )
        } catch (e: Exception){
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}