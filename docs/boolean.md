### ifTrue

Modifier check bool is TRUE for set params

```kotlin
inline fun Modifier.ifTrue(value: Boolean, crossinline block: Modifier.() -> Modifier) =
    then(if (value) block.invoke(this) else this)
```

### ifFalse

Modifier check bool is FALSE for set params

```kotlin
inline fun Modifier.ifFalse(value: Boolean, crossinline block: Modifier.() -> Modifier) =
    then(if (!value) block.invoke(this) else this)
```

### Usage

```kotlin
var isEnabled by remember { mutableStateOf(true) }

Text(
    text = "TEXT",
    modifier = Modifier
        .clickable {
            isEnabled = !isEnabled
        }
        .ifFalse(isEnabled) {
            padding(30.dp)
        }
        .ifTrue(isEnabled) {
            background(Color.Red)
        },
)
```




