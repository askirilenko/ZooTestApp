package com.example.zootestapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zootestapp.data.AnimalModel
import com.example.zootestapp.ui.AnimalDetailsCard

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowDialog(
    onDismiss: () -> Unit,
    animal: AnimalModel){
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ){
        AnimalDetailsCard(animal = animal, hiltViewModel(), false)
    }
}