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
