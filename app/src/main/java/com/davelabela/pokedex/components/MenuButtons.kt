package com.davelabela.pokedex.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun RowScope.HomeButton(
    name: String,
    color: Color,
    weight: Float
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .shadow(elevation = 0.dp, shape = RoundedCornerShape(size = 20.dp))
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .background(Brush.linearGradient(listOf(color.copy(0.5f), color)))
//            .shadow(10.dp, shape = RoundedCornerShape(size = 20.dp), clip = true)
            .weight(weight)
            .clickable {
                Toast
                    .makeText(context, "Test: ${name}", Toast.LENGTH_SHORT)
                    .show()
            }
    ) {
        Text(
            text = name,
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = poppinFonts,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
        )
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
            HomeButton(name = "Pokedex", color = Color(0xff65BD9D), weight = 1f)
            Spacer(modifier = Modifier.padding(8.dp))
            HomeButton(name = "Moves", color = Color(0xffBD8F65), weight = 1f)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeButton(name = "Abilities", color = Color(0xff65A3BD), weight = 1f)
            Spacer(modifier = Modifier.padding(8.dp))
            HomeButton(name = "Items", color = Color(0xffB6BD65), weight = 1f)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeButton(name = "Locations", color = Color(0xff461158), weight = 1f)
            Spacer(modifier = Modifier.padding(8.dp))
            HomeButton(name = "Type Charts", color = Color(0xffBDA465), weight = 1f)
        }
    }
}

@Preview
@Composable
fun HomeButtonList_Preview() {
    HomeButtonList()
}