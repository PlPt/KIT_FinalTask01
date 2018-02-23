package edu.kit.informatik.junit;

import static org.hamcrest.Matchers.*;
import edu.kit.informatik.Terminal;

/**
 * Test Helper for the Terminal Test Class Based on Common Outputs
 *
 * @version 2.1
 * @author Lucas Alber
 */

public class TestHelper {
    /**
     * Tests against a certain input and expects "OK"
     * @param input to test
     */
    public static void expectOK(final String input) {
        Terminal.addSingleLineOutputThatIsExactly(input, "OK");
    }

    /**
     * Tests again a certain input that has to be incorrect!
     * Expects a Output starting with "Error, "
     * @param input to test
     */
    public static void expectError(final String input) {
        Terminal.addSingleLineOutputThatMatches(input, startsWith("Error, "));
    }
    
    /**
     * Tests again a Error Message
     * Expects a Output starting with "Error, "
     */
    public static void expectErrorWithoutInput() {
        expectError(null);
    }
    
}

