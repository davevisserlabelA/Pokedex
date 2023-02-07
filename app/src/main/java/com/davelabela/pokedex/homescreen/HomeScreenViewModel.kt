package com.davelabela.pokedex.homescreen

import androidx.lifecycle.ViewModel
import com.davelabela.pokedex.data.remote.responses.generation.Generation
import com.davelabela.pokedex.data.remote.responses.generation.GenerationList
import com.davelabela.pokedex.data.remote.responses.pokemon.Pokemon
import com.davelabela.pokedex.repository.PokemonRepository
import com.davelabela.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel(){

    suspend fun getGenerationInfo(id: Int): Resource<Generation> {
        return repository.getGenerationInfo(id = id)
    }

    suspend fun getGenerationList(): Resource<GenerationList>{
        return repository.getGenerationList()
    }
}