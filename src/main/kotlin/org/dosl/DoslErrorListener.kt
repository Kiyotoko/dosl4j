package org.dosl

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException

class DoslErrorListener: BaseErrorListener() {

    override fun syntaxError(
        recognizer: org.antlr.v4.runtime.Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        System.err.printf("Syntax Error [%d:%d]: %s%n", line, charPositionInLine, msg)
    }
}