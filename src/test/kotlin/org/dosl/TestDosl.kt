package org.dosl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestDosl {
    @Test
    fun parse() {
        Assertions.assertDoesNotThrow {
            javaClass.getResource("index.dosl")!!.let {
                val listing = parseDoslFile(it.path)
                Assertions.assertFalse(listing.paths.isEmpty())

                for (entry in listing.labels) {
                    println("@${entry.key}: ${entry.value}")
                }
            }
        }
    }
}