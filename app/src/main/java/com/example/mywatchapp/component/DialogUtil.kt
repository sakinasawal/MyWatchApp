package com.example.mywatchapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.google.android.horologist.annotations.ExperimentalHorologistApi
import com.google.android.horologist.compose.material.Confirmation

//@Composable
//fun CardDialog(){
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = Color.Black)
//            .padding(20.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//        Image(
//            modifier = Modifier.size(40.dp),
//            painter = painterResource(R.drawable.ic_done),
//            contentDescription = "Success Icon"
//        )
//
//        Text(modifier = Modifier.padding(top=10.dp),
//            text = "Card added successfully.",
//            style = Typography.body4)
//    }
//}
//
//@OptIn(ExperimentalHorologistApi::class)
//@Composable
//fun CardConfirmDialog(
//    isShowDialog : Boolean = false,
//    onDismiss: ()-> Unit = {}
//){
//    Confirmation(
//        showDialog = isShowDialog,
//        onTimeout = { onDismiss() },
//        icon = {
//            Icon(
//                painter = painterResource(R.drawable.ic_done),
//                contentDescription = "Success Icon",
//                modifier = Modifier.size(40.dp),
//                tint = Color.Unspecified
//            )
//        },
//        title = "Card added successfully"
//    )
//
//}
//
//@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
//@Composable
//fun CardDialogPreview(){
//    CardDialog()
//}