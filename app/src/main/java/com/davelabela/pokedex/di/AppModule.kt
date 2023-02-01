package com.davelabela.pokedex.di

import com.davelabela.pokedex.data.PokemonRepository
import com.davelabela.pokedex.data.PokemonRepositoryImpl
import com.davelabela.pokedex.data.network.PokeHTTPClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getHttpClient(httpClient: PokeHTTPClient): HttpClient = httpClient.getHttpClient()

    @Provides
    fun getPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository = impl

}