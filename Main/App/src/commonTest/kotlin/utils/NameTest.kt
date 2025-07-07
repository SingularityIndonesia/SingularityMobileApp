package utils

import kotlin.test.Test
import kotlin.test.assertSame

class NameTest {
    @Test
    fun `test nickname`() {
        val name = "Dahyun"
        assertSame("D", name.initialName())

        val name2 = "Cute Dahyun"
        assertSame("CD", name2.initialName())
    }
}