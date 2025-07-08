package com.outerspace.compose_animations

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.outerspace.compose_animations.ui.theme.Point

class MainViewModel: ViewModel() {
    @Composable
    fun rememberBtnAState(x: Int, y: Int): CoordinatesState {
        return remember {
            CoordinatesState(x = x, y = y)
        }
    }

    fun moveTo(btnAState: CoordinatesState, point: Point) {
        Log.d("MOVE TO", "move to x: ${point.x}, ${point.y}")
        btnAState.moveTo(point)
    }
}