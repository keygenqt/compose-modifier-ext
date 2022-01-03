Disable vertical scroll. Work with [HorizontalPager](https://google.github.io/accompanist/pager/)

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

### Usage

```kotlin
HorizontalPager(
    modifier = Modifier.disableHorizontalScroll(),
    state = pages.state,
    count = pages.count
) { page ->
    when (page) {
        pages.step1 -> OnboardingStep1()
        pages.step2 -> OnboardingStep2()
        pages.step3 -> OnboardingStep3()
        pages.step4 -> OnboardingStep4()
        pages.step5 -> OnboardingStep5()
    }
}
```