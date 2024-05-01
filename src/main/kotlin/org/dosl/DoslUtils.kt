package org.dosl

import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonTokenStream
import java.io.FileNotFoundException
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Utility functions for dosl.
 *
 * @author karlz
 */

/**
 * Parses the dosl file from a given filename.
 *
 * @param filename the filename
 */
@Throws(IOException::class, DoslParseException::class)
fun parseDoslFile(filename: String): DoslListing {
    val charStream: CharStream = ANTLRFileStream(filename)
    val lexer = DoslLexer(charStream)
    val tokenStream = CommonTokenStream(lexer)
    val parser = DoslParser(tokenStream)
    val listener = DoslParseListener()

    parser.addParseListener(listener)
    parser.addErrorListener(DoslErrorListener())
    parser.root()
    return listener.listing
}

/**
 * Parses the dosl file from a given class and the name of a resource file that is present in the same package.
 *
 * @param loader the class from which the resource should be loaded
 * @param name the name of the resource
 */
@Throws(IOException::class, FileNotFoundException::class, DoslParseException::class)
fun parseDoslFile(loader: Class<out Any>, name: String): DoslListing {
    val url = loader.getResource(name)
    if (url != null) {
        return parseDoslFile(url.path)
    } else throw FileNotFoundException()
}


/**
 * Returns the content of a rawtext that was supplied by a context object. This is equally to the text except the first
 * and last char. For example, this function will return {@code Hello} for the String {@code "Hello"} (quotes were removed).
 *
 * @param rawtext the text of the context object
 */
internal fun content(rawtext: String): String {
    return rawtext.substring(1, rawtext.length - 1)
}
