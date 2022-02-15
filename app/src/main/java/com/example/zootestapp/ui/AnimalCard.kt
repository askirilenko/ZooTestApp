package com.example.zootestapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.zootestapp.R
import com.example.zootestapp.ShowDialog
import com.example.zootestapp.data.AnimalModel
import com.example.zootestapp.ui.theme.Color.themeColorCardBackground
import com.example.zootestapp.ui.theme.typography
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AnimalCard(animal: AnimalModel) {
    val paddingModifier = Modifier.padding(10.dp)
    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {

        ShowDialog(
            onDismiss = {showDialog.value = false},
            animal = animal
        )
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        backgroundColor = themeColorCardBackground,
        modifier = paddingModifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = { showDialog.value = true })
    ){
        Row() {
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 5.dp, bottom = 5.dp),
                    text = stringResource(id = R.string.animal_name, animal.name),
                    style = typography.subtitle2
                )
                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    text = stringResource(id = R.string.animal_latin_name_card, animal.latinName),
                    style = typography.h2
                )
            }

            GlideImage(
                imageModel = animal.imageLink,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(all = 5.dp)
                    .weight(1f)
                    .fillMaxSize(),
                placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
                error = ImageBitmap.imageResource(R.drawable.placeholder)
            )
        }
    }
}