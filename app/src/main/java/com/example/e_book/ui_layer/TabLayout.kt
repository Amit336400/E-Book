package com.example.e_book.ui_layer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun tabLayout() {
    val pageCount = rememberPagerState(pageCount = { 2 })

    Column(modifier = Modifier.fillMaxSize()) {
        Tabs(pageCount)
        screens(pageCount)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun screens(pageCount: PagerState) {
    HorizontalPager(state = pageCount) {
        when (it) {
            0 -> {
                CategoryScreen()
            }

            1 -> {
                Book()
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pageCount: PagerState) {

    val tabsitems = arrayOf(
        tabItemes("Category", Icons.Default.AccountBox),
        tabItemes("Book", Icons.Default.CheckCircle)
    )

    TabRow(selectedTabIndex = pageCount.currentPage) {
        val customCoroutine = rememberCoroutineScope()
        tabsitems.forEachIndexed { index, tabItemes ->
            Tab(
                selected = pageCount.currentPage == index,
                onClick = {
                    customCoroutine.launch {
                        pageCount.animateScrollToPage(index)

                    }
                }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = tabItemes.Icon, contentDescription = null)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = tabItemes.title)
                }

            }


        }


    }

}


data class tabItemes(
    val title: String,
    val Icon: ImageVector,
)
