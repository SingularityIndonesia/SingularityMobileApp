package com.singularityuniverse.singularity

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform