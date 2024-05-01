package org.dosl

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException

/**
 * Default error listener for dosl parsing.
 *
 * @author karlz
 */
class DoslErrorListener: BaseErrorListener() {

    override fun syntaxError(
        recognizer: org.antlr.v4.runtime.Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        throw DoslParseException(msg.orEmpty(), line, charPositionInLine)
    }
}