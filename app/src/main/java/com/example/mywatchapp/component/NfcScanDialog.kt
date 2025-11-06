package com.example.mywatchapp.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Dialog
import androidx.wear.tooling.preview.devices.WearDevices
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mywatchapp.R
import com.example.mywatchapp.model.LoyaltyCard
import com.example.mywatchapp.theme.Typography

@Composable
fun NfcScanDialog(card: LoyaltyCard? = null, onClick: (()-> Unit)? = {}){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier
            .clickable(onClick = { onClick?.invoke() })
            .padding(horizontal = 20.dp)
            .background(color = Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Tap card to earn points",
            style = Typography.body4,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 25.dp)
        )

        Box(contentAlignment = Alignment.Center
        ){
            LottieAnimation(
                composition = rememberLottieComposition(
                    LottieCompositionSpec.RawRes(R.raw.blue_round_pulse)).value,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.size(screenWidth * 1.0f)
            )

            Box(modifier = Modifier
                .fillMaxWidth(0.8f)
                .graphicsLayer(rotationZ = 90f)
            ){
                NfcCardInfo(card = card, cardTypeRes = card?.image ?: R.drawable.ic_card_red)
            }
        }
    }
}

@Composable
fun NfcScanDialogCustom(
    isShowDialog: Boolean = false,
    onDismiss: () -> Unit = {},
    card: LoyaltyCard? = null
){
    if(isShowDialog){
        Dialog(
            showDialog = true,
            onDismissRequest = { onDismiss() },
        ) {
            NfcScanDialog(card = card, onClick = { onDismiss() })
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun NfcScanDialogPreview(){
    NfcScanDialog()
}