package org.dosl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.ArrayList

class DoslTest {
    @Test
    fun parse() {
        Assertions.assertDoesNotThrow {
            javaClass.getResource("index.dosl")!!.let {
                val listing = parseDoslFile(it.path)
                Assertions.assertFalse(listing.toCollection(ArrayList()).isEmpty())

                for (name in listing) {
                    println(name)
                }

                println(listing["network"])
                println(listing["project"])
            }
        }
    }
}