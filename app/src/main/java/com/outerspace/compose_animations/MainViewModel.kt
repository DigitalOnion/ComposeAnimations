package com.outerspace.compose_animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.outerspace.compose_animations.ui.theme.Point

class MainViewModel: ViewModel() {
    @Composable
    fun rememberBtnAState(x: Int, y: Int): CoordinatesState {
        return remember {
            CoordinatesState(x = x, y = y)
        }
    }

    fun buttonOnClick(btnAState: CoordinatesState, point: Point) {
        btnAState.x = point.x
        btnAState.y = point.y
    }
}