package com.davelabela.pokedex.pokedex

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.davelabela.pokedex.R
import com.davelabela.pokedex.data.models.PokedexListEntry
import com.davelabela.pokedex.ui.theme.poppinFonts
import com.davelabela.pokedex.util.customPlaceholder

// TODO: Decrease code duplication with itemdex screen.
// TODO: Refactor into better code structure / different files -> Extract compose functions.

@Composable
fun PokedexScreen(
    navController: NavController,
    viewModel: PokeDexViewModel = hiltViewModel()
) {
    Surface(
        color = Color(0xffF5F6F7),
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                verticalAlignment = CenterVertically,
                modifier = Modifier.padding(4.dp)
            ) {
                IconButton(onClick = navController::popBackStack) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "Back Button",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = "PokeDex",
                    fontSize = 24.sp,
                    fontFamily = poppinFonts,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF013A63),
                    textAlign = TextAlign.Center
                )
            }
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                viewModel.searchPokemonList(it)
            }
            Spacer(modifier = Modifier.height(8.dp))
            PokemonList(navController = navController)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun PokemonList(
    navController: NavController,
    viewModel: PokeDexViewModel = hiltViewModel()
) {
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    val isSearching by remember { viewModel.isSearching }

    LazyColumn(modifier = Modifier
        .graphicsLayer { alpha = 0.99f }
        .drawWithContent {
            // TODO: Very hacky fade. Change
            val colors = listOf(Color.Transparent,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Transparent)
            drawContent()
            drawRect(brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn)
        }, contentPadding = PaddingValues(16.dp)) {
        val itemCount = if (pokemonList.size % 2 == 0) {
            pokemonList.size / 2
        } else {
            pokemonList.size / 2 + 1
        }
        items(itemCount) {
            if (it >= itemCount - 1 && !endReached && !isLoading && !isSearching) {
                LaunchedEffect(key1 = true) {
                    viewModel.loadPokemonPaginated()
                }
            }
            PokedexRow(rowIndex = it, entries = pokemonList, navController = navController)
        }
    }

    Box(
        contentAlignment = Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if (loadError.isNotEmpty()) {
            RetrySection(error = loadError) {
                viewModel.loadPokemonPaginated()
            }
        }
    }

}

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokeDexViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    Card(
        modifier = modifier
            .customPlaceholder(isLoading, RoundedCornerShape(20.dp))
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${dominantColor.toArgb()}/${entry.pokemonName}"
                )
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = dominantColor
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = "#${entry.number} ${entry.pokemonName}",
                fontSize = 16.sp,
                fontFamily = poppinFonts,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                Box(modifier = Modifier.offset(y = 0.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.pokeball),
                        contentDescription = "PokeBall",
                        tint = Color(0x13FFFFFF),
                        modifier = Modifier
                            .size(128.dp)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(entry.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = entry.pokemonName,
                        modifier = Modifier
                            .size(128.dp),
                        onSuccess = {
                            viewModel.calculateDominant(it.result.drawable, onFinish = { color ->
                                dominantColor = color
                                isLoading = false;
                            })
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokedexListEntry>,
    navController: NavController
) {
    Column {
        Row {
            PokedexEntry(
                entry = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (entries.size >= rowIndex * 2 + 2) {
                PokedexEntry(
                    entry = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}