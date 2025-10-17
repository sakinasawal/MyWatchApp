package com.example.mywatchapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.component.ButtonText

@Composable
fun WearListApp(onClick: (() -> Unit)? = null){
    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Hello Wear OS",
                modifier = Modifier.padding(bottom = 12.dp))
        }
        item { ButtonText(onClick = { onClick?.invoke() })}
        item { Text("Another item")}
    }
}



@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun WearListAppPreview() {
    WearListApp()
}


