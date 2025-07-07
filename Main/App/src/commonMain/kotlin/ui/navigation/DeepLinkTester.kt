package ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Just launch the preview to test deeplinks
 */
@Preview
@Composable
fun DeepLinkTester() {
    val uriHandler = LocalUriHandler.current
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "DeepLink Test",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        
        Button(
            onClick = {
                uriHandler.openUri("singularityapp://about")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Launch About DeepLink")
        }
    }
}
