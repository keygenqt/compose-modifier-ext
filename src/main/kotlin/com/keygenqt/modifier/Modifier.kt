/*
 * Copyright 2022 Vitaliy Zarubin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.keygenqt.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

/**
 * Collapse list item animation
 */
fun Modifier.graphicsCollapse(
    state: LazyListState
) = this.composed {
    var scrolledY by remember { mutableStateOf(0f) }
    var previousOffset by remember { mutableStateOf(0) }
    graphicsLayer {
        scrolledY += state.firstVisibleItemScrollOffset - previousOffset
        translationY = scrolledY * 0.5f
        previousOffset = state.firstVisibleItemScrollOffset
    }
}

/**
 * No ripple clickable
 */
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit) = composed {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    clickable(
        indication = null,
        interactionSource = interactionSource
    ) {
        onClick()
    }
}

/**
 * Interception of a click
 */
fun Modifier.interceptionClickable(): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {}
}

/**
 * Disable vertical scroll
 */
fun Modifier.disableVerticalScroll() =
    this.nestedScroll(object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource) =
            available.copy(x = 0f)
    })

/**
 * Disable horizontal scroll
 */
fun Modifier.disableHorizontalScroll() =
    this.nestedScroll(object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource) =
            available.copy(y = 0f)
    })

/**
 * Modifier check bool is FALSE for set params
 */
inline fun Modifier.ifFalse(value: Boolean, crossinline block: Modifier.() -> Modifier) =
    then(if (!value) block.invoke(this) else this)

/**
 * Modifier check bool is TRUE for set params
 */
inline fun Modifier.ifTrue(value: Boolean, crossinline block: Modifier.() -> Modifier) =
    then(if (value) block.invoke(this) else this)

/**
 * Remove excess indent Navigation Bars in ime padding
 */
fun Modifier.imePaddingWithOutNavigationBars(): Modifier = composed {
    this.padding(
        PaddingValues(
            bottom = with(LocalDensity.current) {
                WindowInsets.ime.getBottom(this).toDp() - 50.dp
            }.coerceAtLeast(0.dp)
        )
    )
}
