package com.example.mywatchapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.component.GenerateQrCodeDialog
import com.example.mywatchapp.model.Voucher
import com.example.mywatchapp.theme.Typography
import com.google.android.horologist.compose.layout.ScreenScaffold

@Composable
fun VoucherListScreen() {
    var isShowQrCodeDialog by remember { mutableStateOf(false) }
    var selectedVoucher by remember { mutableStateOf<Voucher?>(null) }

    // TODO : dummy data
    val vouchers = listOf(
        Voucher(id = "1", text = "Auntie Anne's \nBuy 1 Get 1 Free", image = R.drawable.voucher),
        Voucher(id = "2", text = "Krispy Kreme \nBuy 1 Get 1 Free", image = R.drawable.krispy_kreme),
        Voucher(id = "3", text = "Tealive \nBuy 1 Get 1 Free", image = R.drawable.tealive),
        Voucher(id = "4", text = "llaollao \nBuy 1 Get 1 Free", image = R.drawable.llaollao),
        Voucher(id = "5", text = "Auntie Anne's \nBuy 1 Get 1 Free", image = R.drawable.voucher),
        Voucher(id = "6", text = "Auntie Anne's \nBuy 1 Get 1 Free", image = R.drawable.voucher)
    )

    ScreenScaffold {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Voucher",
                style = Typography.h4,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 28.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(vouchers) { voucher ->
                    VoucherComponent(
                        label = voucher.text,
                        image = voucher.image,
                        onClick = {
                            selectedVoucher = voucher
                            isShowQrCodeDialog = true
                        }
                    )
                }
            }
        }
    }

    if (isShowQrCodeDialog) {
        GenerateQrCodeDialog(
            voucherId = selectedVoucher?.id ?: "",
            onDismiss = { isShowQrCodeDialog = false }
        )
    }
}

@Composable
fun VoucherComponent(
    label: String? = "Auntie Anne's \nBuy 1 Get 1 Free",
    image: Int? = R.drawable.voucher,
    onClick: (() -> Unit) = {}
) {
    Box(
        modifier = Modifier.padding(vertical = 20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_voucher),
            contentDescription = "Background Voucher",
        )

        Column(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onClick() }
                .padding(end = 40.dp, top = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(image ?: R.drawable.voucher),
                contentDescription = "Voucher Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = label ?: "",
                color = Color.Black,
                style = Typography.caption,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun VoucherListScreenPreview() {
    VoucherListScreen()
}


