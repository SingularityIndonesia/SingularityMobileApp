package com.singularityuniverse.singularity.android

import com.singularityuniverse.singularity.android.utils.defaultHttpClient
import io.ktor.client.*
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import service.authentication.AuthenticationService
import service.authentication.web.AuthenticationWebApi
import service.authentication.web.KtorAuthenticationWebApi
import ui.pane.AccountPaneViewModel
import ui.screen.login.LoginScreenViewModel
import ui.screen.otp.OtpScreenViewModel

val viewModels = module {
    viewModel { LoginScreenViewModel(get()) }
    viewModel { OtpScreenViewModel(get()) }
    viewModel { AccountPaneViewModel() }
}

val services = module {
    single { AuthenticationService(get()) }
}

val webApis = module {
    single<AuthenticationWebApi> { KtorAuthenticationWebApi(get()) }
}

val agents = module {
    single<HttpClient> { defaultHttpClient() }
}
