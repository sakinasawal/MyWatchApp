package com.example.mywatchapp.screen

import androidx.wear.compose.material.Button
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material3.ScreenScaffold
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.mywatchapp.R
import com.example.mywatchapp.viewmodel.ListSongViewModel

@Composable
fun WearMusicScreen(){
    val viewModel : ListSongViewModel = hiltViewModel()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val progress by viewModel.progress.collectAsState()

    ListSongApp(
        isPlaying = isPlaying,
        progress = progress,
        onPlayPause = { viewModel.playPause() }
    )
}

@Composable
fun ListSongApp(
    isPlaying : Boolean = false,
    progress : Float? = 0.10f,
    songTitle : String? = "Mama",
    artist : String? = "Ida Rahayu",
    onPlayPause : (()-> Unit)? = null,
    onNext : (()-> Unit)? = null,
    onPrevious : (()-> Unit)? = null,
    onLike : (()-> Unit)? = null,
    onVolume : (()-> Unit)? = null,
){
    ScreenScaffold{
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                if (songTitle != null) {
                    Text(songTitle, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center )
                }
                if (artist != null) {
                    Text(artist, style = MaterialTheme.typography.caption2, color = Color.Gray)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(contentAlignment = Alignment.Center){
                    CircularProgress(progress)

                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ){
                        IconButton(onClick = { onPrevious?.invoke() }) {
                            Icon(painter = painterResource(R.drawable.ic_skip_previous), contentDescription = "Previous", tint = Color.White)
                        }

                        Button(
                            onClick = { onPlayPause?.invoke() },
                            shape = CircleShape,
                            modifier = Modifier
                                .size(20.dp)
                                .shadow(4.dp, CircleShape),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFB8FF00)
                            )
                        ){
                            Icon(
                                painter = painterResource(
                                    id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                                ),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }

                        IconButton(onClick = { onNext?.invoke() }) {
                            Icon(painter = painterResource(R.drawable.ic_skip_next), contentDescription = "Next", tint = Color.White)
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { onVolume?.invoke() }) {
                        Icon(painter = painterResource(R.drawable.ic_volume_up), contentDescription = "Volume", tint = Color.White)
                    }

                    IconButton(onClick = { onLike?.invoke() }) {
                        Icon(painter = painterResource(R.drawable.ic_fav), contentDescription = "Like", tint = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun CircularProgress(progress: Float?){
    Canvas(modifier = Modifier.size(30.dp)){
        drawArc(
            color = Color.DarkGray.copy(alpha = 0.3f),
            startAngle = -90f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = Offset(0f, 0f),
            size = Size(size.width, size.height),
            style = Stroke(width = 6f)
        )

        drawArc(
            color = Color(0xFFB8FF00),
            startAngle = -90f,
            sweepAngle = 360f * (progress?: 0f),
            useCenter = false,
            topLeft = Offset(0f, 0f),
            size = Size(size.width, size.height),
            style = Stroke(width = 6f)
        )
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun ListSongAppPreview() {
    ListSongApp()
}