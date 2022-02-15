package com.example.zootestapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zootestapp.AnimalViewModel
import com.example.zootestapp.R
import com.example.zootestapp.data.AnimalModel
import com.example.zootestapp.ui.AnimalCard


@Composable
fun ShowRandomAnimalListScreen(viewModel: AnimalViewModel) {

    val animals = viewModel.animalList.collectAsState()

    animals.value?.let {
        ShowAnimalCards(animals = it, hiltViewModel())
    }
}

@Composable
fun ShowAnimalCards(animals: List<AnimalModel>, viewModel: AnimalViewModel) {
    val listState = rememberLazyListState()
    val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastIndex + listState.firstVisibleItemIndex

    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )

    LazyColumn(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top,
        state = listState
    ) {
        items(animals) { animal ->
                AnimalCard(animal)
        }

        if ( lastVisibleItemIndex== animals.size - 1 )
            viewModel.getAnimalList()
    }
}


