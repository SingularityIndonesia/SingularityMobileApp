package ui.navigation

// region HomeDestination
const val HomeDestination = "home?section={section}"

enum class HomeSection {
    Colors,
    Memories,
    Search,
    Account
}

fun HomeDestinationBuilder(
    section: HomeSection = HomeSection.Colors
): String {
    return HomeDestination
        .replace("{section}", section.name)
}
// endregion