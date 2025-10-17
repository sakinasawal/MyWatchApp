package com.example.mywatchapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import androidx.wear.compose.material.*

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

@Composable
fun ChipWithIcon(
    modifier : Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    label : String? = "Music",
    iconRes : Int? = R.drawable.ic_music
){
    Chip(
        onClick = { onClick?.invoke() },
        label = { Text( text = label ?: "")},
        icon = {
            Icon(
                painter = painterResource(id = iconRes!!),
                contentDescription = "$label Icon"
            )
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ToggleIconBtn(
    modifier : Modifier = Modifier,
    label : String? = null,
    iconRes: Int? = R.drawable.ic_home,
    isSelected : Boolean = false,
    onToggle : ((Boolean) -> Unit)? = null
){
    val backgroundColor = if (isSelected){
        MaterialTheme.colors.primary.copy(alpha = 0.4f)
    } else {
        MaterialTheme.colors.surface.copy(alpha = 0.2f)
    }

    Surface(
        modifier = modifier
            .size(80.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onToggle!!(!isSelected)},
        color = backgroundColor,
        contentColor = MaterialTheme.colors.onSurface,
        tonalElevation = if (isSelected) 6.dp else 2.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            iconRes?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = label,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            if (label != null){
                Text(
                    text = label,
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewWearTextButton() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ButtonText()
        ChipWithIcon()
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            ToggleIconBtn(modifier = Modifier)
            ToggleIconBtn(modifier = Modifier, iconRes = R.drawable.ic_fav, label = "Heart")
        }
    }
}

