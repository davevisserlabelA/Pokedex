package com.davelabela.pokedex.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davelabela.pokedex.data.remote.responses.Pokemon
import com.davelabela.pokedex.repository.PokemonRepository
import com.davelabela.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PokemonRepository) : ViewModel() {
    private val _pokemon = MutableStateFlow<Resource<Pokemon>?>(null)
    val pokemon: StateFlow<Resource<Pokemon>?> = _pokemon

    init {
        viewModelScope.launch {
            _pokemon.value = repository.getPokemonInfo("charmander")
        }
    }
}