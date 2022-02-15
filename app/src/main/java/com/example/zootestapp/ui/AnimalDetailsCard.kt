package com.example.zootestapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.zootestapp.AnimalViewModel
import com.example.zootestapp.R
import com.example.zootestapp.data.AnimalModel
import com.example.zootestapp.ui.theme.Color.*
import com.example.zootestapp.ui.theme.typography
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun AnimalDetailsCard(animal: AnimalModel, viewModel: AnimalViewModel, isShowButton: Boolean) {
    val scrollState = rememberScrollState()
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp,
        backgroundColor = themeColorCardBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .verticalScroll(scrollState)
    ) {
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Text(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = stringResource(id = R.string.animal_name, animal.name),
                    style = typography.h1
                )

                Text(
                    text = stringResource(id = R.string.animal_latin_name, animal.latinName),
                    style = typography.h2
                )
            }
            GlideImage(
                imageModel = animal.imageLink,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                placeHolder = ImageBitmap.imageResource(R.drawable.placeholder)
            )


                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = stringResource(id = R.string.animal_type, animal.type)
                    )

                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = stringResource(
                            id = R.string.animal_active_time,
                            animal.activeTime
                        )
                    )

                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = stringResource(
                            id = R.string.animal_length,
                            animal.lengthMin,
                            animal.lengthMax
                        )
                    )

                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = stringResource(
                            id = R.string.animal_weight,
                            animal.weightMin,
                            animal.weightMax
                        )
                    )

                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = stringResource(id = R.string.animal_lifespan, animal.lifespan)
                    )

                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = stringResource(id = R.string.animal_habitat, animal.habitat)
                    )

                    Text(
                        modifier = Modifier.padding(all = 8.dp),
                        text = stringResource(id = R.string.animal_diet, animal.diet)
                    )

                    Text(
                        modifier = Modifier.padding(all = 8.dp).padding(bottom = 10.dp),
                        text = stringResource(id = R.string.animal_geo_range, animal.geoRange)
                    )

                }

            if (isShowButton)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = themeColorWhite,
                        backgroundColor = themeColorButtonCard
                    ),
                    onClick = { viewModel.getAnimal() }) {
                      Text(text = stringResource(id = R.string.button_other_animal))
                    }
            }
        }
    }
}

