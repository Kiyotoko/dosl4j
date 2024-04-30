package org.dosl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;

class TestJava {
    @Test
    void parse() {
        Assertions.assertDoesNotThrow(() -> {
            URL url = getClass().getResource("index.dosl");
            if (url != null) {
                var listing = DoslUtilsKt.parseDoslFile(url.getPath());
                Assertions.assertFalse(listing.getPaths().isEmpty());

                for (var entry : listing.getLabels().entrySet()) {
                    System.out.printf("@%s: %s%n", entry.getKey(), entry.getValue());
                }
            } else {
                Assertions.fail();
            }
        });
    }
}
