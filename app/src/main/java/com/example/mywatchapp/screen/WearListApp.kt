package com.example.mywatchapp.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.wear.compose.foundation.lazy.items
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.component.ChipWithIcon
import com.example.mywatchapp.model.ListMenu
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun WearListApp(navController: NavController? = null){
    val columnState = rememberResponsiveColumnState()

    val dummy = listOf(
        ListMenu(id = "001", text = "App Grid", image = R.drawable.ic_app),
        ListMenu(id = "002", text = "Music", image = R.drawable.ic_music),
        ListMenu(id = "003", text = "Card", image = R.drawable.ic_card),
    )

    ScreenScaffold(scrollState = columnState,
        positionIndicator = { PositionIndicator(scalingLazyListState = columnState.state) }
    ) {
        ScalingLazyColumn(
            columnState = columnState,
            modifier = Modifier.fillMaxSize()
        ) {
            item { Text(text = "Hello Wear OS") }

            items(dummy) { it ->
                ChipWithIcon(
                    label = it.text,
                    iconRes = it.image,
                    onClick = {
                        when (it.id) {
                            "001" -> navController?.navigate("grid")
                            "002" -> navController?.navigate("music")
                            "003" -> navController?.navigate("nfc_list")
                        }
                    }
                )
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun WearListAppPreview() {
    WearListApp()
}


