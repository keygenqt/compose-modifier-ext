## Visible

Controlling element visibility based on transparency

```kotlin
fun Modifier.visible(visibility: Boolean) = then(
    alpha(if (visibility) 1f else 0f)
)
```

