package com.singularityuniverse.singularity.android

import App
import AppIntent
import LocalProjectContext
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
import com.pluto.Pluto
import com.pluto.plugins.datastore.pref.PlutoDatastorePreferencesPlugin
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.preferences.PlutoSharePreferencesPlugin
import org.koin.compose.KoinApplication
import ui.navigation.Route
import utils.ContextProvider
import utils.MediaPicker

class MainActivity : ComponentActivity() {

    private val appIntent = mutableStateListOf<AppIntent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        // setup pluto
        Pluto.Installer(this.application)
            .addPlugin(PlutoNetworkPlugin())
            .addPlugin(PlutoDatastorePreferencesPlugin())
            .addPlugin(PlutoSharePreferencesPlugin())
            .addPlugin(PlutoExceptionsPlugin())
            .install()

        // setup context provider
        ContextProvider.initialize(this)

        // setup media picker
        MediaPicker.setupMediaPickerLauncher(this)

        // setup route
        Route.set(ProjectContext)

        // Handle initial deeplink
        handleIntent(intent)

        setContent {
            KoinApplication(
                application = {
                    modules(viewModels, services, webApis, agents, dbs)
                }
            ) {
                CompositionLocalProvider(LocalProjectContext provides ProjectContext) {
                    App(
                        intent = appIntent.firstOrNull(),
                        onHandled = {
                            appIntent -= it
                        }
                    )
                }
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
