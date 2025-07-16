package model.particle

import stdextra.assertSuccess
import kotlin.test.Test
import kotlin.test.assertFails

class OtpTest {
    @Test()
    fun `valid otp`() {
        assertSuccess { Otp("123456") }
    }

    @Test()
    fun `invalid otp`() {
        assertFails { Otp("112") }
        assertFails { Otp("112 345") }
        assertFails { Otp("11211a") }
        assertFails { Otp("asdad") }
    }
}