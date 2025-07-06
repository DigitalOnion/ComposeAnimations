package com.outerspace.compose_animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.outerspace.compose_animations.ui.theme.ComposeAnimationsTheme
import com.outerspace.compose_animations.ui.theme.Point

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

    var btnAState = mainVM.rememberBtnAState(0, 0)

    Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp)) {
        Spacer(modifier = Modifier.fillMaxWidth().height(64.dp))
        Button(onClick = { mainVM.buttonOnClick(btnAState, Point(100, 100))
                         }, modifier = Modifier) {
            Text(
                text = "Animate elements",
                modifier = Modifier
            )
        }
        Box(modifier = Modifier.fillMaxSize().weight(1f).border(1.dp, Color.Black)) {
            Text(text = "A", modifier = Modifier.offset(x = btnAState.x.dp, y = btnAState.y.dp))
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