package navigation

// region HomeDestination
const val HomeDestination = "home?section={section}"

enum class HomeSection {
    Universe,
    Page1,
    Page2,
    Page3
}

fun HomeDestinationBuilder(
    section: HomeSection = HomeSection.Universe
): String {
    return HomeDestination
        .replace("{section}", section.name)
}
// endregion