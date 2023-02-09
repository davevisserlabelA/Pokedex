package com.davelabela.pokedex.itemdex

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.davelabela.pokedex.data.models.ItemdexListEntry
import com.davelabela.pokedex.data.models.PokedexListEntry
import com.davelabela.pokedex.repository.PokemonRepository
import com.davelabela.pokedex.util.Constants.PAGE_SIZE
import com.davelabela.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ItemDexViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private var curPage = 0

    var itemList = mutableStateOf<List<ItemdexListEntry>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    private var cachedItemList = listOf<ItemdexListEntry>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadItemsPaginated()
    }

    fun searchItemList(query: String) {
        val listToSearch = if (isSearchStarting) {
            itemList.value
        } else {
            cachedItemList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                itemList.value = cachedItemList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                it.itemName.contains(query.trim(), ignoreCase = true) ||
                        it.itemName == query.trim()
            }
            if (isSearchStarting) {
                cachedItemList = itemList.value
                isSearchStarting = false
            }
            itemList.value = results
            isSearching.value = true
        }
    }

    fun loadItemsPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getItemList(PAGE_SIZE, curPage * PAGE_SIZE)
            when (result) {
                is Resource.Success -> {
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count
                    val itemdexEntries = result.data.results.mapIndexed { _, entry ->
                        val url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/items/${entry.name}.png"
                        ItemdexListEntry(entry.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        }, url)
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    itemList.value += itemdexEntries
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    fun calculateDominant(drawable: Drawable, onFinish: (Color) -> Unit) {

        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}