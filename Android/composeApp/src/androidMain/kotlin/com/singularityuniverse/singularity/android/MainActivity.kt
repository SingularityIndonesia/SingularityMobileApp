package com.singularityuniverse.singularity.android

import App
import AppIntent
import LocalProjectContext
import ProjectContext
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import ui.navigation.Route

class MainActivity : ComponentActivity() {

    // fixme: provide proper project context
    val projectContext = ProjectContext()
    val appIntent = mutableStateListOf<AppIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Handle initial deeplink
        handleIntent(intent)
        Route.set(projectContext)

        setContent {
            CompositionLocalProvider(LocalProjectContext provides projectContext) {
                App(
                    intent = appIntent.firstOrNull(),
                    onHandled = {
                        appIntent -= it
                    }
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        val action = intent.action
        val data: Uri? = intent.data

        if (Intent.ACTION_VIEW == action && data != null) {
            appIntent += AppIntent.DeepLinkNavigate(data.toString())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
