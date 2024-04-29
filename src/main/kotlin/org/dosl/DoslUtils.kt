package org.dosl

import java.io.File
import java.nio.file.Path

fun parseDoslFile(filename: String): DoslListing {
    return DoslTreeWalker().walk(filename)
}

fun content(rawtext: String): String {
    return rawtext.substring(1, rawtext.length - 1)
}

fun String.toFile(): File {
    return File(this)
}

fun String.toPath(): Path {
    return Path.of(this)
}