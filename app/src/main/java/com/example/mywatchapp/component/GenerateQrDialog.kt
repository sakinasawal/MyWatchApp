package com.example.mywatchapp.component

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import androidx.core.graphics.createBitmap
import androidx.core.graphics.set
import androidx.wear.compose.material.dialog.Dialog
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.EdgeButton
import androidx.wear.compose.material3.Icon
import com.example.mywatchapp.R
import com.example.mywatchapp.theme.Purple
import com.example.mywatchapp.theme.Typography
import com.google.zxing.EncodeHintType
import kotlin.ranges.until
import kotlin.to

@Composable
fun GenerateQr(
    voucherId: String = "1",
    onDismiss : () -> Unit = {}
){
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val boxSize = min(screenWidth, screenHeight) * 0.6f
    val scrollState = rememberScrollState()

    // TODO : dummy data
    val qrContent = voucherId
    val qrBitmap = remember { generateQrCode(qrContent) }
    val qrImageBitmap = remember(qrBitmap) { qrBitmap.asImageBitmap() }

    Column(
        modifier = Modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Scan QR to redeem voucher",
            style = Typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 25.dp),
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
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
        }

        EdgeButton(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            onClick = { onDismiss() },
        ) {
            Text(
                text = "Close",
                textAlign = TextAlign.Center,
                style = Typography.button
            )
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
            bmp[x, y] = if (bitMatrix.get(x, y)) AndroidColor.WHITE else AndroidColor.TRANSPARENT
        }
    }
    return bmp
}

@Composable
fun GenerateQrCodeDialog(
    voucherId: String = "1",
    onDismiss: () -> Unit = {}
){
    Dialog(
        showDialog = true,
        onDismissRequest = { onDismiss() }
    ){
        GenerateQr(voucherId = voucherId, onDismiss = { onDismiss() })
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun GenerateQrDialogPreview(){
    GenerateQr()
}