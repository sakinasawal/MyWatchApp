package com.example.mywatchapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material3.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.model.Voucher
import com.example.mywatchapp.theme.Typography
import com.google.android.horologist.compose.layout.ScreenScaffold
import kotlin.collections.forEach

@Composable
fun VoucherListScreen(navController: NavController? = null){
    val scrollState = rememberScrollState()

    val vouchers = listOf(
        Voucher(id = "1", text = "Auntie Anne's \nBuy 1 Get 1 Free", image = R.drawable.voucher),
        Voucher(id = "2", text = "Krispy Kreme \nBuy 1 Get 1 Free", image = R.drawable.krispy_kreme),
        Voucher(id = "3", text = "Tealive \nBuy 1 Get 1 Free", image = R.drawable.tealive),
        Voucher(id = "4", text = "llaollao \nBuy 1 Get 1 Free", image = R.drawable.llaollao),
        Voucher(id = "5", text = "Auntie Anne's \nBuy 1 Get 1 Free", image = R.drawable.voucher),
        Voucher(id = "6", text = "Auntie Anne's \nBuy 1 Get 1 Free", image = R.drawable.voucher)
    )

    ScreenScaffold {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp, horizontal = 10.dp)
        ){
           Text(text = "Voucher",
               style = Typography.h4,
               modifier = Modifier
                   .align(Alignment.TopCenter)
                   .padding(top = 15.dp)
           )

            Row(
                modifier = Modifier.horizontalScroll(scrollState),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                // TODO : update
                vouchers.forEach {
                    VoucherComponent(
                        label = it.text,
                        image = it.image,
                        onClick = { navController?.navigate("qr/${it.id}") }
                    )
                }
            }
        }
    }
}

@Composable
fun VoucherComponent(
    label : String ? = "Auntie Anne's \nBuy 1 Get 1 Free",
    image : Int? = R.drawable.voucher,
    onClick: (() -> Unit)? = null
){
    Box(modifier = Modifier.clickable { onClick?.invoke() },
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.ic_voucher),
            contentDescription = "Background Voucher",
            modifier = Modifier.padding(5.dp)
        )

        Column(
            modifier = Modifier.padding(end = 40.dp, top = 5.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Image(painter = painterResource(image ?: R.drawable.voucher),
                contentDescription = "Voucher Image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(4.dp))

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
fun VoucherListScreenPreview(){
    VoucherListScreen()
}


