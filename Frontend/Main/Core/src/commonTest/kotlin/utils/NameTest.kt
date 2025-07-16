package utils

import stdextra.shouldBe
import kotlin.test.Test
import kotlin.test.assertTrue

class NameTest {
    @Test
    fun `test nickname`() {
        "Dahyun".initialName() shouldBe "D"
        "Cute Dahyun".initialName() shouldBe "CD"
    }
}