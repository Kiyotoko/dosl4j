package org.dosl

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import java.io.File
import kotlin.io.path.isDirectory

class DoslParserTest {

    @Test
    fun readToken() {
        val url = javaClass.getResource("example.dosl")
        url?.let {
            val parser = DoslParser(File(it.file))
            parser.readToken()
            parser.readToken()
            println(parser.getPaths())
            for (path in parser.getPaths()) {
                if (!path.isDirectory()) {
                    path.toFile().bufferedReader().readLines().forEach { line -> println(line) }
                }
            }
        }
    }

    @Test
    fun readAll() {
        val url = javaClass.getResource("example.dosl")
        url?.let {
            val parser = DoslParser(File(it.file))
            parser.readAll()
            println(parser.getPaths())
        }
    }

    @Test
    fun exists() {
        val url = javaClass.getResource("example.dosl")
        url?.let {
            val parser = DoslParser(File(it.file))
            parser.readToken()
            parser.readToken()
            Assertions.assertTrue(parser.exists())
            parser.readToken()
            Assertions.assertFalse(parser.exists())
            println(parser.getPaths())
        }
    }
}