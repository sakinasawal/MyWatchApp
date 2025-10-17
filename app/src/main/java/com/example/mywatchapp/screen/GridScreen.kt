package com.example.mywatchapp.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.pager.HorizontalPager
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.component.ToggleIconBtn
import androidx.wear.compose.foundation.pager.rememberPagerState
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition

@Composable
fun WearHomeScreen(startPage: Int = 0){
    val pages = listOf("home", "grid", "music")
    val pagerState = rememberPagerState(initialPage = startPage, pageCount = {pages.size})

    Scaffold(
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = {}
    ){
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                when(page){
                    0 -> WearListApp()
                    1 -> WearGridScreen()
                    2 -> WearMusicScreen()
                }
            }
        }
    }
}

@Composable
fun WearGridScreen() {
    var selected by remember { mutableStateOf(setOf<String>()) }

    val buttons = listOf(
        "Home" to R.drawable.ic_home,
        "Music" to R.drawable.ic_music,
        "Heart" to R.drawable.ic_fav,
        "Volume" to R.drawable.ic_volume_up,
        "Settings" to R.drawable.ic_settings,
        "Clock" to R.drawable.ic_clock,
        "App" to R.drawable.ic_app
    )
    Scaffold(
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        positionIndicator = {}
    ){
        ScalingLazyColumn{
            item {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.title3,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

            item {
                val configuration = LocalConfiguration.current
                val screenWidthDp = configuration.screenWidthDp
                val buttonSize = (screenWidthDp / 3.5).dp

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    buttons.forEach { (label, icon) ->
                        ToggleIconBtn(
                            iconRes = icon,
                            isSelected = selected.contains(label),
                            onToggle = { isOn ->
                                selected = if (isOn) selected + label else selected - label
                            },
                            modifier = Modifier.size(buttonSize)
                        )
                    }
                }
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun GridScreenPreview() {
    WearGridScreen()
}