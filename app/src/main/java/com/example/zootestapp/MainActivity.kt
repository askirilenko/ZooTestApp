package com.example.zootestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.example.zootestapp.ui.TabItem
import com.example.zootestapp.ui.theme.Color.themeColorTabBackground
import com.example.zootestapp.ui.theme.Color.themeColorWhite
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen()
        }
    }

    @OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
    @Composable
    fun MainScreen() {
        val tabs = listOf(
            TabItem.Card,
            TabItem.List
        )
        val pagerState = rememberPagerState()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            content = {
                Column(
                    verticalArrangement = Arrangement.Top
                )
                {
                    Tabs(tabs = tabs, pagerState = pagerState)
                    TabsContent(tabs = tabs, pagerState = pagerState)
                }
            }
        )
    }

    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @Composable
    fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
        val scope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = themeColorTabBackground,
            contentColor = themeColorWhite,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            tabs.forEachIndexed { index, tab ->
                LeadingIconTab (
                    icon = { },
                    text = { Text(stringResource(tab.title).uppercase()) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
        HorizontalPager(
            state = pagerState,
            count = tabs.size
        ) { page ->
            tabs[page].screen()
        }
    }
}