sealed class AppIntent {
    open val requireLoggedIn: Boolean = false

    class DeepLinkNavigate(val deepLinkUri: String) : AppIntent()
}