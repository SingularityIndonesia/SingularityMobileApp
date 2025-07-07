package utils

import kotlin.test.Test
import kotlin.test.assertTrue

class NameTest {
    @Test
    fun `test nickname`() {
        val name = "Dahyun"
        assertTrue { name.initialName() == "D" }

        val name2 = "Cute Dahyun"
        assertTrue { name2.initialName() == "CD" }
    }
}