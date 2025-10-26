package com.example.mywatchapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.model.LoyaltyCard

@Composable
fun NfcCardInfo(
    card: LoyaltyCard? = null
) {
    Image(
        painter = painterResource(card?.image ?: R.drawable.ic_card_red),
        contentDescription = "Nfc Card",
        modifier = Modifier.clip(RoundedCornerShape(12.dp))
    )
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun NfcCardInfoPreview(){
    Box(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center)
    {
        NfcCardInfo()
    }
}

