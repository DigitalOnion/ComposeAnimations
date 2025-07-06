package com.outerspace.compose_animations

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue

@Stable     // @Stable tells Compose that this object might change, and when it changes the Compose runtime will be notified
class CoordinatesState (
    x: Int,
    y: Int,
) {
    var x by mutableIntStateOf(x)
    var y by mutableIntStateOf(y)

    fun moveTo(moveToX: Int, moveToY:Int) {
        x = moveToX
        y = moveToY
    }
}

