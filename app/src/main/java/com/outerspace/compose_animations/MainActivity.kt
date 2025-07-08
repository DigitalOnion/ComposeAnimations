package com.outerspace.compose_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.outerspace.compose_animations.ui.theme.ComposeAnimationsTheme
import com.outerspace.compose_animations.ui.theme.Point
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    val mainVM: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAnimationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimateElementPosition(
                        mainVM,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AnimateElementPosition(mainVM: MainViewModel, modifier: Modifier = Modifier) {
    val smileySize = 50
    val density = LocalDensity.current
    var xMax: Int by remember { mutableIntStateOf(320) }
    var yMax: Int by remember { mutableIntStateOf(720) }

    // For A
    var btnAState = mainVM.rememberCoordinateState(0, 0)

    // For B
    var btnBState = mainVM.rememberCoordinateState(0, 0)
    val offset = mainVM.offsetState(btnBState)              // this is the only difference in between a straight move and an animation

    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp)) {
        Spacer(modifier = Modifier.fillMaxWidth().height(48.dp))
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = {
                mainVM.moveTo(btnAState, Point(Random.nextInt(0, xMax), Random.nextInt(yMax))) // Point(xMax, yMax)) //
            }, modifier = Modifier) {
                Text(
                    text = "Move elements",
                    modifier = Modifier
                )
            }
            Button(onClick = {
                mainVM.animateTo(btnBState, Point(Random.nextInt(0, xMax), Random.nextInt(0, yMax))) // Point(xMax, yMax)) //
            }, modifier = Modifier) {
                Text(
                    text = "Animate elements",
                    modifier = Modifier
                )
            }
        }
        Box(modifier = Modifier.fillMaxSize().weight(1f).border(1.dp, Color.Black).onSizeChanged {
            yMax = (it.height.toInt() / density.density.toFloat()).toInt() - smileySize
            xMax = (it.width.toInt() / density.density.toFloat()).toInt() - smileySize
                                                                                                 },
            ) {
            Image(painterResource(R.drawable.smiley), contentDescription = "Smiley", modifier = Modifier.requiredSize(smileySize.dp).offset(x = btnAState.x.dp, y = btnAState.y.dp))
            Image(painterResource(R.drawable.smiley), contentDescription = "Smiley", modifier = Modifier.requiredSize(smileySize.dp).offset(x = offset.x.dp, y = offset.y.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val mainVM = MainViewModel()
    ComposeAnimationsTheme {
        AnimateElementPosition(mainVM, Modifier)
    }
}