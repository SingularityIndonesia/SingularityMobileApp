package model.particle

import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

class EmailTest {
    @Test()
    fun `valid email`() {
        val email = runCatching { Email("some.email@somehwere.com") }
        assertTrue { email.isSuccess }
    }

    @Test()
    fun `invalid email`() {
        assertFails { Email("ssdsfsfsf") }
    }
}