package model.particle

import stdextra.assertSuccess
import kotlin.test.Test
import kotlin.test.assertFails
import kotlin.test.assertTrue

class EmailTest {
    @Test()
    fun `valid email`() {
        assertSuccess { Email("some.email@somehwere.com") }
    }

    @Test()
    fun `invalid email`() {
        assertFails { Email("ssdsfsfsf") }
    }
}