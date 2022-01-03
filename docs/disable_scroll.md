Disable vertical scroll. Work with HorizontalPager

### disableVerticalScroll

Disable vertical scroll

```kotlin
fun Modifier.disableVerticalScroll() =
    this.nestedScroll(object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource) =
            available.copy(x = 0f)
    })
```

### disableHorizontalScroll

Disable horizontal scroll

```kotlin
fun Modifier.disableHorizontalScroll() =
    this.nestedScroll(object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource) =
            available.copy(y = 0f)
    })
```

