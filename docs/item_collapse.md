Collapse list item animation

```kotlin
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
```

### Usage

```kotlin
val state: LazyListState = rememberLazyListState(),

LazyColumn(
    state = state
) {
    item {
        Column(
            modifier = Modifier.graphicsCollapse(lazyListState)
        ) {

        }
    }
}
```