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

package org.dosl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

/**
 * Test for all java methods.
 *
 * @author karlz
 */
class TestJava {
    @Test
    void parse() {
        Assertions.assertDoesNotThrow(() -> {
            URL url = getClass().getResource("index.dosl");
            if (url != null) {
                var listing = DoslUtilsKt.parseDoslFile(url.getPath());
                Assertions.assertFalse(listing.getPaths().isEmpty());

                for (var entry : listing.getLabelsToPaths().entrySet()) {
                    System.out.printf("@%s: %s%n", entry.getKey(), entry.getValue());
                }

                for (var entry : listing.getPathsToLabels().entrySet()) {
                    System.out.printf("@%s: %s%n", entry.getKey(), entry.getValue());
                }
            } else {
                Assertions.fail();
            }
        });
    }

    @Test
    void resource() {
        try {
            var listing = DoslUtilsKt.parseDoslFile(getClass(), "index.dosl");
            Assertions.assertFalse(listing.getPathsToLabels().isEmpty());
        } catch (IOException|DoslParseException ex) {
            Assertions.fail(ex);
        }
    }

    @Test
    void performance() {
        for (int i = 0; i < 10; i++)
            Assertions.assertDoesNotThrow(() -> DoslUtilsKt.parseDoslFile(getClass(), "performance.dosl"));
    }

    @Test
    void error() {
        Assertions.assertThrows(DoslParseException.class, () -> DoslUtilsKt.parseDoslFile(getClass(), "error.dosl"));
    }
}
