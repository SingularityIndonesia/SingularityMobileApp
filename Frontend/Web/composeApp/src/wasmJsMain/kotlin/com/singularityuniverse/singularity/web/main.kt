package com.singularityuniverse.singularity.web

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
//        KoinApplication(
//            application = {
//                modules(viewModels, services, webApis, agents, dbs)
//            }
//        ) {
//            CompositionLocalProvider(LocalProjectContext provides ProjectContext) {
//                App(
//                    intent = appIntent.firstOrNull(),
//                    onHandled = {
//                        appIntent -= it
//                    }
//                )
//            }
//        }
    }
}