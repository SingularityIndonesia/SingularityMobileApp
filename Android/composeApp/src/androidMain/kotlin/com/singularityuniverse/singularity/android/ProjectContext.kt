package com.singularityuniverse.singularity.android

import EnvironmentProperties
import ProjectContext

internal val ProjectContext = ProjectContext(
    versionName = EnvironmentProperties.VERSION_NAME,
    versionCode = EnvironmentProperties.VERSION_CODE,
    hostName = EnvironmentProperties.HOSTNAME,
    webHostUrl = EnvironmentProperties.WEB_HOST_URL,
    deepLinkHostName = EnvironmentProperties.DEEPLINK_HOSTNAME,
    deepLinkHostUrl = EnvironmentProperties.DEEPLINK_HOST_URL,
)