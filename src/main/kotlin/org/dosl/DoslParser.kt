package org.dosl

import java.io.File
import java.io.FileReader
import java.nio.file.Path
import kotlin.io.path.exists

/**
 * Parses the content of a file.
 */
class DoslParser(file: File): FileReader(file) {
    companion object {
        const val EOL = '\n'.code // End of line
        const val COM = '#'.code // Comment
        const val EOF = -1  // Enf of file
    }

    private val paths = ArrayList<Path>()

    private var build: String = ""

    /**
     * Reads the next token. Can be a comment, a path or an empty line.
     */
    fun readToken() {
        var char = read()
        while (char != EOL && char != EOF) {
            if (char == COM) {
                do {
                    char = read()
                } while (char != EOL && char != EOF)
            } else {
                build += char.toChar()
            }
            char = read()
        }
        addBuild()
    }

    /**
     * Reads all tokens of the file.
     */
    fun readAll() {
        var char = read()
        while (char != EOF) {
            while (char != EOL) {
                if (char == COM) {
                    do { // Skip chars
                        char = read()
                    } while (char != EOL && char != EOF)
                } else {
                    build += char.toChar()
                }
                char = read()
            }
            addBuild()
        }
    }

    /**
     * Add the current build to the paths.
     */
    private fun addBuild() {
        if (build.isNotBlank()) {
            paths.add(Path.of(build))
            build = ""
        }
    }

    /**
     * Checks all parsed paths. Returns true if all paths exist, false otherwise.
     */
    fun exists(): Boolean {
        for (path in paths) {
            if (!path.exists()) {
                return false
            }
        }
        return true
    }

    /**
     * Returns all parsed paths.
     */
    fun getPaths() = paths
}