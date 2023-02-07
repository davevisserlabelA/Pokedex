package com.davelabela.pokedex.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.davelabela.pokedex.R
import com.davelabela.pokedex.components.GenButtonsList
import com.davelabela.pokedex.components.HomeButtonList
import com.davelabela.pokedex.ui.theme.poppinFonts

@Composable
fun HomeScreen(
    navController: NavController,
) {
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

    Box( // Background color
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF5F6F7))
    ) {

        Column {
            Column( // Top Items Column
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(40.dp))
                    .padding(bottom = 16.dp)
            ) {
                Box() {
                    Image( // Pokeball Image
                        painter = painterResource(id = R.drawable.pokeball),
                        contentDescription = "PokeBall",
                        alpha = 0.1f,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .size(256.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 80.dp, y = (-100).dp) // Not proud of this
                    )
                    Column() {
                        Text(
                            text = "What Pokemon \nare you looking for?",
                            fontSize = 24.sp,
                            fontFamily = poppinFonts,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF013A63),
                            modifier = Modifier.padding(20.dp),
                        )
                        HomeButtonList(navController = navController)
                    }
                }
            }
            Column(
                modifier = Modifier.padding(top = 12.dp)
            ) { // Bottom Items Column
                Text(
                    text = "Generations",
                    fontSize = 24.sp,
                    fontFamily = poppinFonts,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF013A63),
                    modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
                )
                GenButtonsList(generations = generationList, navController = navController)
            }
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreen_Preview() {
//    HomeScreen()
//}