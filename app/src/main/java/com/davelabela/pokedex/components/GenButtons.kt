package com.davelabela.pokedex.components

import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.davelabela.pokedex.R
import com.davelabela.pokedex.homescreen.HomeScreenViewModel
import com.davelabela.pokedex.ui.theme.poppinFonts
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun RowScope.GenButton(
    // Rowscope for .weight attribute
    genName: String,
    navController: NavController,
) {
    val context = LocalContext.current

    Card(elevation = 4.dp, shape = RoundedCornerShape(20.dp), modifier = Modifier.weight(1f)) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 20.dp))
                .fillMaxSize()
                .weight(1f)
                .background(Color.White)
                .clickable {
                    Toast
                        .makeText(context, "Test: $genName", Toast.LENGTH_SHORT)
                        .show()
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = "PokeBall",
                alpha = 0.1f,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(64.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .offset(x = 8.dp, y = 10.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    genName,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontFamily = poppinFonts,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF013A63),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Box() {

                    // Temporary images: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Temp name",
                        modifier = Modifier
                            .size(128.dp)
                            .offset(x = -32.dp, y = -12.dp)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Temp name",
                        modifier = Modifier
                            .size(128.dp)
                            .offset(x = 32.dp, y = -12.dp)
                    )
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png")
                            .crossfade(true)
                            .build(),
                        contentDescription = "Temp name",
                        modifier = Modifier
                            .size(128.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun GenButtonsList(
    generations: List<String>,
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .graphicsLayer { alpha = 0.99f }
            .drawWithContent {
                // TODO: Very hacky fade. Change
                val colors = listOf(Color.Transparent,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Black,Color.Transparent)
                drawContent()
                drawRect(brush = Brush.verticalGradient(colors), blendMode = BlendMode.DstIn)
            }
    ) {
        itemsIndexed(generations) { index, _ ->
            if (index % 2 == 0) {
                if (generations.size > index + 1) {
                    Row() {
                        GenButton(genName = generations[index], navController = navController)
                        Spacer(modifier = Modifier.padding(8.dp))
                        GenButton(genName = generations[index + 1], navController = navController)
                    }
                } else {
                    Row() {
                        GenButton(genName = generations[index], navController = navController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun GenButtonsList_Preview() {
    val navController = rememberAnimatedNavController()
    val generationList = listOf<String>(
        "Gen I",
        "Gen II",
        "Gen III",
        "Gen IV",
        "Gen V",
        "Gen VI",
        "Gen VII",
        "Gen VIII",
        "Gen IX",
    )

    GenButtonsList(generations = generationList, navController = navController)
}