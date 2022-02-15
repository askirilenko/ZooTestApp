package com.example.zootestapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zootestapp.AnimalViewModel
import com.example.zootestapp.R
import com.example.zootestapp.ui.AnimalDetailsCard

@Composable
fun ShowRandomAnimalScreen(viewModel: AnimalViewModel) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        val mode = viewModel.viewModelMode.collectAsState()

        when (mode.value) {
            AnimalViewModel.Mode.Idle -> ShowCard(hiltViewModel())
            AnimalViewModel.Mode.Loading -> LoadingScreen()
        }
}

    @Composable
    fun ShowCard(viewModel: AnimalViewModel){

        val animal = viewModel.animal.collectAsState()

        animal.value?.let {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AnimalDetailsCard(it, hiltViewModel(), true)
            }
        }
    }



