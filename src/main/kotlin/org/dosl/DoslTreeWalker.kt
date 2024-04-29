package org.dosl

import org.antlr.v4.runtime.ANTLRFileStream
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CommonTokenStream

class DoslTreeWalker {
    fun walk(filename: String): DoslListing {
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
}