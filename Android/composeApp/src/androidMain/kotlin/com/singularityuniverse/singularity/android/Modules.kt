package com.singularityuniverse.singularity.android

import EnvironmentProperties
import io.ktor.client.*
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import service.authentication.AuthenticationService
import service.authentication.web.AuthenticationWebApiClient
import service.authentication.web.KtorAuthenticationWebApiClient
import service.session.SessionDB
import service.session.SessionService
import service.session.web.KtorSessionWebApiClient
import service.session.web.SessionWebApiClient
import ui.pane.AccountPaneViewModel
import ui.screen.login.LoginScreenViewModel
import ui.screen.otp.OtpScreenViewModel
import utils.defaultHttpClient

val viewModels = module {
    viewModel { LoginScreenViewModel(get()) }
    viewModel { OtpScreenViewModel(get(), get()) }
    viewModel { AccountPaneViewModel() }
}

val services = module {
    single { AuthenticationService(get()) }
    single { SessionService(get(), get()) }
}

val dbs = module {
    single { SessionDB() }
}

val webApis = module {
    single<AuthenticationWebApiClient> { KtorAuthenticationWebApiClient(get()) }
    single<SessionWebApiClient> { KtorSessionWebApiClient(get()) }
}

val agents = module {
    single<HttpClient> { defaultHttpClient(EnvironmentProperties.WEB_HOST_URL) }
}
