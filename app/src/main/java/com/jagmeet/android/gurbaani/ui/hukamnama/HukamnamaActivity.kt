package com.jagmeet.android.gurbaani.ui.hukamnama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.*
import com.jagmeet.android.gurbaani.ui.theme.GurbaaniTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HukamnamaActivity : ComponentActivity() {

    private val viewModel: HukamnamaViewModel by viewModels()


    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GurbaaniTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }

    }

    @ExperimentalMaterialApi
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MainScreen() {
        val tabs = listOf(TabItem.Punjabi, TabItem.English)
        val pagerState = rememberPagerState()
        Scaffold() {
            Column {
                TabsContent(
                    tabs = tabs,
                    pagerState = pagerState,
                    modifier = Modifier.weight(1f)
                )
                Tabs(tabs = tabs, pagerState = pagerState)
            }
        }
    }

    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @Composable
    fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
        val scope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }) {
            tabs.forEachIndexed { index, tab ->
                LeadingIconTab(
                    icon = { Icons.Default.Done },
                    text = { Text(tab.title) },
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
    fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, modifier: Modifier) {
        HorizontalPager(state = pagerState, count = tabs.size, modifier = modifier) { page ->
            when (tabs[page]) {
                is TabItem.Punjabi -> {
                    viewModel.hukamNamaState.hukamnama?.punjabi?.let { showHukamNamaScreen(hukamnama = it) }
                }
                is TabItem.English -> {
                    viewModel.hukamNamaState.hukamnama?.english?.let { showHukamNamaScreen(hukamnama = it) }
                }
                is TabItem.Hindi -> {}
            }
        }
    }
}