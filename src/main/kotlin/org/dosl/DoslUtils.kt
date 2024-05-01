/*
 * MIT License
 *
 * Copyright (c) 2024 Karl Zschiebsch
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
