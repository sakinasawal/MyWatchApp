package com.example.mywatchapp.screen

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Text
import androidx.wear.compose.material3.EdgeButton
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.component.AddCardBox
import com.example.mywatchapp.component.CardConfirmDialog
import com.example.mywatchapp.component.NfcCardInfo
import com.example.mywatchapp.component.NfcScanDialogCustom
import com.example.mywatchapp.model.LoyaltyCard
import com.example.mywatchapp.theme.Typography
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
    var isShowSuccessDialog by remember { mutableStateOf(false) }
    var isShowScanCardDialog by remember { mutableStateOf(false) }

    val dummyCardList = remember {
        listOf(
            LoyaltyCard(
                id = "1",
                cardId = "CARD-001",
                userNo = "1001",
                status = "Active",
                source = "NRewards",
                activatedAt = "2023-10-10",
                image = R.drawable.ic_cc
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
                image = R.drawable.ic_cc
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
                    items(cardList.value.size) { index ->
                        val currentCard = cardList.value[index]
                        // TODO : display list card
                        Box(modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                            .combinedClickable(
                                onClick = {
                                    selectedCard = currentCard
                                    isShowScanCardDialog = true
                                },
                                onLongClick = {
                                    navController?.navigate("voucher")
                                },
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            )
                        ){ NfcCardInfo(card = currentCard, cardTypeRes = currentCard.image) }
                    }
                }

                item {
                    Box(modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)){
                        AddCardBox{
                            // TODO : add card
                            isShowSuccessDialog = true
                        }
                    }
                }

                item {
                    EdgeButton(
                        onClick = { },
                        content = {
                            Text("Logout", style = Typography.h5, textAlign = TextAlign.Center)
                        }
                    )
                }
            }

            if (isShowSuccessDialog){
                Box(modifier = Modifier
                ){
                    CardConfirmDialog(
                        isShowDialog = isShowSuccessDialog,
                        onDismiss = { isShowSuccessDialog = false }
                    )
                }
            }

            if(isShowScanCardDialog && selectedCard != null){
                Box(modifier = Modifier.fillMaxSize()
                ){
                    NfcScanDialogCustom(
                        isShowDialog = isShowScanCardDialog,
                        onDismiss = {
                            isShowScanCardDialog = false
                            selectedCard = null
                        },
                        card = selectedCard
                    )
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