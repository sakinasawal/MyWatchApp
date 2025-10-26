package com.example.mywatchapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.component.NfcCardInfo
import com.example.mywatchapp.model.LoyaltyCard
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.rememberResponsiveColumnState

@OptIn(ExperimentalHorologistApi::class)
@Composable
fun NfcListCardScreen(
    navController: NavController? = null){
    val columnState = rememberResponsiveColumnState()
    var selectedCard by remember { mutableStateOf<LoyaltyCard?>(null) }

    val dummyCardList = remember {
        listOf(
            LoyaltyCard(
                id = "1",
                cardId = "CARD-001",
                userNo = "1001",
                status = "Active",
                source = "NRewards",
                activatedAt = "2023-10-10",
                image = R.drawable.ic_card_red
            ),
            LoyaltyCard(
                id = "2",
                cardId = "CARD-002",
                userNo = "1002",
                status = "Inactive",
                source = "NRewards",
                activatedAt = "2023-11-01",
                image = R.drawable.ic_card_blue
            ),
            LoyaltyCard(
                id = "3",
                cardId = "CARD-003",
                userNo = "1002",
                status = "Inactive",
                source = "NRewards",
                activatedAt = "2023-11-01",
                image = R.drawable.ic_card_green
            ),
            LoyaltyCard(
                id = "4",
                cardId = "CARD-004",
                userNo = "1002",
                status = "Inactive",
                source = "NRewards",
                activatedAt = "2023-11-01",
                image = R.drawable.ic_card_red
            ),
        )
    }

    val cardList = remember { mutableStateOf(dummyCardList) }

    ScreenScaffold(scrollState = columnState,
        positionIndicator = { PositionIndicator(scalingLazyListState = columnState.state) }
    ) {
        Box{
            ScalingLazyColumn(
                columnState = columnState,
                modifier = Modifier.fillMaxSize()
            ){
                if (cardList.value.isEmpty()) {
                    item {
                        Text(
                            text = "No cards available",
                            modifier = Modifier.padding(vertical = 20.dp)
                        )
                    }
                } else {
                    items(cardList.value?.size ?: 0) { index ->
                        val currentCard = cardList.value?.get(index)
                        if (currentCard?.id != selectedCard?.id) {
                            // TODO : display list card
                            Box(modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                                .combinedClickable(
                                    onClick = {
                                        selectedCard = currentCard
                                        navController?.navigate("nfc_scan/${currentCard?.cardId}")
                                    },
                                    onLongClick = {
                                        navController?.navigate("voucher")
                                    },
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                            ){ NfcCardInfo(card = currentCard) }
                        }
                    }
                }
            }
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun NfcListCardScreenPreview(){
    NfcListCardScreen()
}