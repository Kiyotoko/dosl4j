package org.dosl

import java.lang.Exception

/**
 * Exception for all parsing errors that occur during the parsing of dosl files.
 *
 * @param msg the message
 * @param line the line where the exception occurred
 * @param pos the char position in the line
 * @author karlz
 */
data class DoslParseException(val msg: String, val line: Int, val pos: Int): Exception(msg) {

}