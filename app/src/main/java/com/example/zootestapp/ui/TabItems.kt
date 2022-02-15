package com.example.zootestapp.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zootestapp.R
import com.example.zootestapp.ui.screens.ShowRandomAnimalListScreen
import com.example.zootestapp.ui.screens.ShowRandomAnimalScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(@StringRes var title: Int, var screen: ComposableFun) {
    object Card : TabItem(R.string.tab_card, { ShowRandomAnimalScreen(hiltViewModel()) })
    object List : TabItem(R.string.tab_list, { ShowRandomAnimalListScreen(hiltViewModel())})
}