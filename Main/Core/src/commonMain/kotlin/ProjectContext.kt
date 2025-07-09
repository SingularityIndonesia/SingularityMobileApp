import androidx.compose.runtime.staticCompositionLocalOf

data class ProjectContext(
    val versionName: String = "NULL",
    val versionID: String = "NULL",
    val hostName: String = "somewhere.whatever.com",
    val webHostUrl: String = "https://somewhere.whatever.com/"
)

val LocalProjectContext = staticCompositionLocalOf { ProjectContext() }