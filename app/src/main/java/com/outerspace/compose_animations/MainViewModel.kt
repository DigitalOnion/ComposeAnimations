package com.outerspace.compose_animations

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.ViewModel
import com.outerspace.compose_animations.ui.theme.Point

class MainViewModel: ViewModel() {
    @Composable
    fun rememberCoordinateState(x: Int, y: Int): CoordinatesState {
        return remember {
            CoordinatesState(x = x, y = y)
        }
    }

    @Composable
    fun offsetState(coordinatesState: CoordinatesState): IntOffset {
        val state by animateIntOffsetAsState(
            targetValue = IntOffset(coordinatesState.x, coordinatesState.y),
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
        )
        return state
    }

    fun moveTo(btnState: CoordinatesState, point: Point) {
        Log.d("MOVE TO", "move to x: ${point.x}, ${point.y}")
        btnState.moveTo(point)
    }

    @Stable
    fun animateTo(btnState: CoordinatesState, point: Point) {
        Log.d("ANIMATE TO", "animate to x: ${point.x}, ${point.y}")
        btnState.moveTo(point)
    }
}