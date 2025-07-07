package com.singularityuniverse.singularity.android

import App
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val deepLinkUrl = remember { mutableStateOf<String?>(null) }
            
            // Handle initial deeplink
            handleIntent(intent) { url ->
                deepLinkUrl.value = url
            }
            
            App(deepLinkUrl = deepLinkUrl.value)
        }
    }
    
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        
        handleIntent(intent) { url ->
            // Handle deeplink when app is already running
            // You might want to emit this to a ViewModel or StateFlow
            // For now, we'll handle it in the composable
        }
    }
    
    private fun handleIntent(intent: Intent, onDeepLink: (String) -> Unit) {
        val action = intent.action
        val data: Uri? = intent.data
        
        if (Intent.ACTION_VIEW == action && data != null) {
            onDeepLink(data.toString())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
