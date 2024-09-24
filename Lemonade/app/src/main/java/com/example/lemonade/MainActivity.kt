package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
}
@Composable
fun LemonApp(modifier: Modifier = Modifier){
    var click_max = (1..3).random()
    var click_count = 0
    var progress by remember { mutableStateOf(1) }
    val instruction = when (progress){
        1 -> R.string.p1ins
        2 -> R.string.p2ins
        3 -> R.string.p3ins
        4 -> R.string.p4ins
        else -> R.string.p1ins
    }
    val description = when (progress){
        1 -> R.string.p1des
        2 -> R.string.p2des
        3 -> R.string.p3des
        4 -> R.string.p4des
        else -> R.string.p1des
    }
    val image = when (progress) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    Box(modifier = Modifier.fillMaxWidth() .background(Color(240, 177, 2))) {
        Text(text = "Lemonade",
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp, bottom = 10.dp)
                .align(Alignment.TopCenter)
        )
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                if (progress == 1) {
                    progress += 1
                    click_max = (1..3).random()
                } else if (progress == 2) {
                    if (click_count >= click_max) {
                        progress += 1
                    } else {
                        click_count += 1
                    }
                } else if (progress == 3) {
                    click_count = 0
                    progress += 1
                } else if (progress == 4) {
                    progress = 1
                } else {
                    println("progress: $progress")
                    println("Should Not Be Here...")
                }
            },
                colors = ButtonDefaults.buttonColors(Color.Transparent)) {
                Image(
                    painter = painterResource(image),
                    contentDescription = stringResource(id = description),
                    Modifier.background(Color(105,215,216))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = instruction), fontSize = 24.sp)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}}