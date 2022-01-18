package com.heinika.pokeg.module.mypokemon.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.heinika.pokeg.R
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.utils.getPokemonImageUrl


@ExperimentalFoundationApi
@Composable
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
fun MyPokemonScreen(){
    TeamNumberCardPreview()
}


@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Preview
@Composable
private fun TeamNumberCardPreview(){

    LazyVerticalGrid(cells = GridCells.Adaptive(90.dp)){
        items(600){
            TeamNumberCard(it + 1,Modifier){}
        }
    }
}




@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun TeamNumberCard(
    id: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier.size(90.dp,120.dp)
    ) {
        Card(modifier = Modifier
            .fillMaxWidth(0.95f)
            .align(Alignment.TopEnd)
            .padding(6.dp),
            shape = RoundedCornerShape(4.dp),
            onClick = { onClick() }) {
            Image(
                painter = painterResource(id = R.drawable.team_card_bg),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = ResUtils.getNameById(id, "", LocalContext.current), color = Color.Black,
                    style = TextStyle(fontSize = 8.sp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 2.dp),
                    maxLines = 1
                )

                Image(
                    painter = painterResource(id = R.drawable.golden_pokeball),
                    contentDescription = "",
                    Modifier
                        .size(12.dp)
                        .padding(end = 2.dp)
                )
            }
        }

        Image(
            painter = rememberImagePainter(data = getPokemonImageUrl(id, "")),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth(0.98f)
        )
    }
}