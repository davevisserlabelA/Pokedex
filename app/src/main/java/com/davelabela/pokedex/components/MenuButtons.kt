package com.davelabela.pokedex.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davelabela.pokedex.ui.theme.poppinFonts

@Composable
fun HomeButton(
    name: String,
    color: Color,
//    onClick: () -> Unit = {}
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .shadow(10.dp, shape = RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable{ Toast.makeText(context, "Test: ${name}", Toast.LENGTH_SHORT ).show()}
    ) {
        Row(
            modifier = Modifier
                .background(Brush.linearGradient(listOf(color.copy(0.7f), color)))
        ) {
            Text(
                text = name,
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = poppinFonts,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}


@Composable
fun HomeButtonList(
) {
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeButton(name = "Pokedex", color = Color(0xff65BD9D))
            HomeButton(name = "Moves", color = Color(0xffBD8F65))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeButton(name = "Abilities", color = Color(0xff65A3BD))
            HomeButton(name = "Items", color = Color(0xffB6BD65))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeButton(name = "Locations", color = Color(0xff461158))
            HomeButton(name = "Type Charts", color = Color(0xffBDA465))
        }
    }
}

@Preview
@Composable
fun HomeButtonList_Preview() {
    HomeButtonList()
}