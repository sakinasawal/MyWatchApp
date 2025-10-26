package com.example.mywatchapp.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mywatchapp.R
import com.example.mywatchapp.theme.Typography

@Composable
fun NfcScanScreen(navController: NavController? = null, cardId : String? = ""){
    ScreenScaffold{

        BackHandler {
            navController?.navigate("nfc_list") {
                popUpTo("nfc_list") { inclusive = true }
            }
        }

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Tap card to earn points\n${cardId}",
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

                Image(
                    painter = painterResource(R.drawable.ic_card_red),
                    contentDescription = "Nfc Card",
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .graphicsLayer(rotationZ = 90f)
                        .clip(RoundedCornerShape(12.dp))
                )
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun NfcScanScreenPreview(){
    NfcScanScreen()
}