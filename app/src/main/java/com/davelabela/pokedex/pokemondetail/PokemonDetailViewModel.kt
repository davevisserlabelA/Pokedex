package com.davelabela.pokedex.pokemondetail

import androidx.lifecycle.ViewModel
import com.davelabela.pokedex.data.remote.responses.Pokemon
import com.davelabela.pokedex.repository.PokemonRepository
import com.davelabela.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonInfo(pokemonName)
    }
}