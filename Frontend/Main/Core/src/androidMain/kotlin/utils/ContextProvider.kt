package utils

import android.content.Context

object ContextProvider {
    private var applicationContext: Context? = null

    fun initialize(context: Context) {
        applicationContext = context.applicationContext
    }

    fun getContext(): Context {
        return applicationContext ?: throw IllegalStateException(
            "ContextProvider not initialized. Call ContextProvider.initialize(context) in your Application class."
        )
    }
}