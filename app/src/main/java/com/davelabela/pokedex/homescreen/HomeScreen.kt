package com.davelabela.pokedex.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davelabela.pokedex.R
import com.davelabela.pokedex.components.HomeButtonList
import com.davelabela.pokedex.ui.theme.poppinFonts

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xffF5F6F7))
    ) {
        Box(
            modifier = Modifier
                .size(256.dp)
                .align(Alignment.TopEnd)
                .offset(x = 80.dp, y = -100.dp)
        )
        {
            Image(
                painter = painterResource(id = R.drawable.pokeball_light),
                contentDescription = "PokeBall",
                alpha = 0.2f,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(){
                Text(
                    text = "What Pokemon \nare you looking for?",
                    fontSize = 24.sp,
                    fontFamily = poppinFonts,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF013A63),
                    modifier = Modifier.padding(20.dp),
                )
                HomeButtonList()
                Text(
                    text = "Generations",
                    fontSize = 24.sp,
                    fontFamily = poppinFonts,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF013A63),
                    modifier = Modifier.padding(20.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen_Preview() {
    HomeScreen()
}