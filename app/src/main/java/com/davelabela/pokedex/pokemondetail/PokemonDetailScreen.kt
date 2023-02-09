package com.davelabela.pokedex.pokemondetail

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.davelabela.pokedex.R
import com.davelabela.pokedex.data.remote.responses.pokemon.Pokemon
import com.davelabela.pokedex.data.remote.responses.pokemon.Type
import com.davelabela.pokedex.ui.theme.poppinFonts
import com.davelabela.pokedex.util.Resource
import com.davelabela.pokedex.util.parseStatToAbbr
import com.davelabela.pokedex.util.parseStatToColor
import java.lang.Math.round
import java.util.*

//TODO: Change all padding values to constants

@Composable
fun PokemonDetailScreen(
    dominantColor: Color,
    pokemonName: String,
    navController: NavController,
    topPadding: Dp = 80.dp,
    pokemonImageSize: Dp = 300.dp,
    viewModel: PokemonDetailViewModel = hiltViewModel()
) {
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
            .systemBarsPadding()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.pokeball),
            contentDescription = "PokeBall",
            tint = Color(0x13FFFFFF),
            modifier = Modifier
                .size(256.dp)
                .align(alignment = Alignment.CenterEnd)
                .offset(x = 48.dp, y = -(topPadding) * 2)
        )
        Column {
            PokemonDetailTopSection(
                navController = navController,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
            )
            PokemonDetailStateWrapper(
                pokemonInfo = pokemonInfo,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topPadding + pokemonImageSize / 3f,
                    )
                    .shadow(10.dp, RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(Color.White)
                    .padding(32.dp),
                loadingModifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topPadding + pokemonImageSize / 3f,
                        start = 32.dp,
                        end = 32.dp,
                        bottom = 32.dp
                    )
            )
        }
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (pokemonInfo is Resource.Success) {
                if (pokemonInfo.data?.id!! >= 0 && pokemonInfo.data.id <= 649) {

                    val context = LocalContext.current
                    val imageLoader = ImageLoader.Builder(context)
                        .components() {
                            if (SDK_INT >= 28) {
                                add(ImageDecoderDecoder.Factory())
                            } else {
                                add(GifDecoder.Factory())
                            }
                        }
                        .build()

                    pokemonInfo.data.sprites.let {
                        AsyncImage(
                            imageLoader = imageLoader,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.versions.generationV?.blackWhite?.animated?.frontDefault)
                                .crossfade(true)
                                .build(),
                            contentDescription = pokemonInfo.data.name,
                            modifier = Modifier
                                .size(pokemonImageSize)
                                .padding(32.dp)
                                .offset(y = topPadding * 2)
                        )
                    }
                } else {
                    pokemonInfo.data.sprites.let {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(it.frontDefault)
                                .crossfade(true)
                                .build(),
                            contentDescription = pokemonInfo.data.name,
                            modifier = Modifier
                                .size(pokemonImageSize)
                                .offset(y = topPadding * 2)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonDetailTopSection(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = navController::popBackStack) {
        Icon(
            painter = painterResource(id = R.drawable.arrow_back),
            tint = Color.White,
            contentDescription = "Back Button",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun PokemonDetailStateWrapper(
    pokemonInfo: Resource<Pokemon>,
    modifier: Modifier = Modifier,
    loadingModifier: Modifier = Modifier
) {
    when (pokemonInfo) {
        is Resource.Success -> {
            PokemonDetailSection(
                pokemonInfo = pokemonInfo.data!!,
                modifier = modifier
                    .offset(y = (-20).dp)
            )
        }
        is Resource.Error -> {
            Text(
                text = pokemonInfo.message!!,
                color = Color.Red,
                modifier = modifier
            )
        }
        is Resource.Loading -> {
            //TODO: Change to shimmer animation
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = loadingModifier
            )
        }
    }
}

@Composable
fun PokemonDetailSection(
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    text = pokemonInfo.name.capitalize(Locale.ROOT),
                    fontSize = 32.sp,
                    fontFamily = poppinFonts,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
                PokemonTypeSection(types = pokemonInfo.types)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "#${pokemonInfo.id}",
                fontSize = 20.sp,
                fontFamily = poppinFonts,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.End
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .offset(y = 100.dp)
                .verticalScroll(scrollState)
        ) {
            PokemonDetailDataSection(
                pokemonWeight = pokemonInfo.weight,
                pokemonHeight = pokemonInfo.height
            )
            PokemonBaseStats(pokemonInfo = pokemonInfo)
        }
    }
}

@Composable
fun PokemonTypeSection(types: List<Type>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (type in types) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White.copy(0.2f))
            ) {
                Text(
                    text = type.type.name.capitalize(Locale.ROOT),
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun PokemonDetailDataSection(
    pokemonWeight: Int,
    pokemonHeight: Int,
    sectionHeight: Dp = 80.dp
) {
    val pokemonWeightInKg = remember {
        round(pokemonWeight * 100f) / 1000f
    }
    val pokemonHeightInMeters = remember {
        round(pokemonHeight * 100f) / 1000f
    }
    Column {
        Text( //TODO: Change color to theme
            text = "Details:",
            fontFamily = poppinFonts,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            PokemonDetailDataItem( //TODO: Change color to theme
                dataValue = pokemonWeightInKg,
                dataUnit = "kg",
                dataIcon = painterResource(id = R.drawable.ic_weight),
                modifier = Modifier.weight(1f)
            )
            Spacer(
                modifier = Modifier
                    .size(1.dp, sectionHeight)
                    .background(Color.LightGray)
            )
            PokemonDetailDataItem( //TODO: Change color to theme
                dataValue = pokemonHeightInMeters,
                dataUnit = "m",
                dataIcon = painterResource(id = R.drawable.ic_height),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun PokemonDetailDataItem(
    dataValue: Float,
    dataUnit: String,
    dataIcon: Painter,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(painter = dataIcon, contentDescription = null, tint = MaterialTheme.colors.onSurface)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$dataValue$dataUnit",
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
fun PokemonStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(2f),
            color = Color.Black.copy(0.4f)
        )
        Text(
            text = (curPercent.value * statMaxValue).toInt().toString(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .weight(6f)
                .height(height)
                .clip(CircleShape)
                .background(
                    if (isSystemInDarkTheme()) {
                        Color(0xFF505050)
                    } else {
                        Color.LightGray
                    }
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(curPercent.value)
                    .clip(CircleShape)
                    .background(statColor)
                    .padding(horizontal = 2.dp)
            ) {
            }
        }
    }
}

@Composable
fun PokemonBaseStats(
    pokemonInfo: Pokemon,
    animDelayPerItem: Int = 100
) {
    val maxBaseStat = remember {
        pokemonInfo.stats.maxOf { it.baseStat }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text( //TODO: Change color to theme
            text = "Base stats:",
            fontFamily = poppinFonts,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
        for (i in pokemonInfo.stats.indices) {
            val stat = pokemonInfo.stats[i]
            PokemonStat(
                statName = parseStatToAbbr(stat),
                statValue = stat.baseStat,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat),
                animDelay = i * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun PokemonDetailScreen_Preview() {
    val navController = rememberNavController()

    PokemonDetailScreen(
        dominantColor = Color.Red,
        pokemonName = "Bulbasaur",
        navController = navController
    )

}