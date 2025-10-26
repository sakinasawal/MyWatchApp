package com.example.mywatchapp.screen

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.wear.compose.material3.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.navigation.NavController
import com.example.mywatchapp.R
import com.example.mywatchapp.theme.Purple
import com.example.mywatchapp.theme.Typography
import com.google.zxing.EncodeHintType
import kotlin.ranges.until
import kotlin.to

@Composable
fun GenerateQrScreen(navController: NavController? = null, voucherId: String){
    ScreenScaffold{
        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp
        val screenHeight = configuration.screenHeightDp.dp
        val boxSize = min(screenWidth, screenHeight) * 0.5f

        val qrContent = voucherId
        val qrBitmap = remember { generateQrCode(qrContent) }
        val qrImageBitmap = remember(qrBitmap) { qrBitmap.asImageBitmap() }

        Column(horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Scan QR to redeem voucher",
                style = Typography.subtitle2,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 30.dp),
            )

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
                contentAlignment = Alignment.Center
            ){
                Box(modifier = Modifier
                    .size(boxSize)
                    .background(Purple)
                    .align(Alignment.Center)
                ){
                    Image(
                        bitmap = qrImageBitmap,
                        contentDescription = "QR Code",
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center)
                    )
                }

                Image(
                    painter = painterResource(R.drawable.ic_x),
                    contentDescription = "Close Button",
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-35).dp, y = (-8).dp)
                        .clickable{ navController?.popBackStack() }
                )
            }
        }
    }
}

fun generateQrCode(content : String) : Bitmap {
    val hints = mapOf(EncodeHintType.MARGIN to 3)
    val bitMatrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, 300, 300, hints)

    val width = bitMatrix.width
    val height = bitMatrix.height
    val bmp = createBitmap(width, height, Bitmap.Config.ARGB_8888)

    for(x in 0 until width){
        for ( y in 0 until height){
            bmp[x, y] = if (bitMatrix.get(x, y)) Color.WHITE else Color.TRANSPARENT
        }
    }
    return bmp
}


@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun GenerateQRScreenPreview(){
    GenerateQrScreen(null, "1")
}