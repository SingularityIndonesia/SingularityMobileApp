package stdextra

import kotlin.test.assertTrue

fun assertSuccess(bloc: () -> Any) {
    assertTrue { runCatching { bloc.invoke() }.isSuccess }
}