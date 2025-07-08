package com.singularityuniverse.singularity.android

import App
import AppIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    val appIntent = mutableStateListOf<AppIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // Handle initial deeplink
        handleIntent(intent)

        setContent {
            App(
                intent = appIntent.firstOrNull(),
                onHandled = {
                    appIntent -= it
                }
            )
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
