package com.example.mywatchapp.component

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
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun ButtonText(
    modifier : Modifier = Modifier,
    text : String = "Press Me",
    onClick: (() -> Unit)? = null
){
    Button(
        onClick = { onClick?.invoke() },
        modifier = modifier.fillMaxWidth()
    ){
        Text(text = text)
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewWearTextButton() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        ButtonText()
    }
}