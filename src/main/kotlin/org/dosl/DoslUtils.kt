package org.dosl

import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonTokenStream

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

fun content(rawtext: String): String {
    return rawtext.substring(1, rawtext.length - 1)
}
