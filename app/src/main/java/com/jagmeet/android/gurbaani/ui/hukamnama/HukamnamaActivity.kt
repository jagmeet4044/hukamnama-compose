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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.*
import com.jagmeet.android.gurbaani.model.HukamnamaDetail
import com.jagmeet.android.gurbaani.ui.theme.GurbaaniTheme
import kotlinx.coroutines.launch

class HukamnamaActivity : ComponentActivity() {
    private val viewModel: HukamnamaViewModel by viewModels()
    lateinit var hukamNama: MutableState<HukamnamaDetail>

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hukamNama = viewModel.hukamNama
        setContent {
            GurbaaniTheme {
                Surface(color = MaterialTheme.colors.background) {
                    // showHukamNamaPunjabi(hukamNama)
                    MainScreen()
                }
            }
        }
        viewModel.onTriggerAction(HukamnamaViewModel.HukamnamaActions.GetHukamnama)
    }

    @ExperimentalMaterialApi
    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun MainScreen() {
        val tabs = listOf(TabItem.Punjabi, TabItem.English)
        val pagerState = rememberPagerState()
        Scaffold() {
            Column {
                TabsContent(tabs = tabs, pagerState = pagerState, modifier = Modifier.weight(1f))
                Tabs(tabs = tabs, pagerState = pagerState)
            }
        }
    }

    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @Composable
    fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
        val scope = rememberCoroutineScope()
        // OR ScrollableTabRow()
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
                // OR Tab()
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
                is TabItem.Punjabi -> showHukamNamaScreen(hukamNama.value.punjabi)
                is TabItem.English -> showHukamNamaScreen(hukamNama.value.english)
                is TabItem.Hindi -> {}
            }
        }
    }
}