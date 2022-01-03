Handling clicks

### noRippleClickable

No ripple clickable

```kotlin
inline fun Modifier.noRippleClickable(crossinline onClick: () -> Unit) = composed {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    clickable(
        indication = null,
        interactionSource = interactionSource
    ) {
        onClick()
    }
}
```

### interceptionClickable

Interception of a click

```kotlin
fun Modifier.interceptionClickable(): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {}
}
```

