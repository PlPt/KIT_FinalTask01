package edu.kit.informatik.junit.suite;


import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.kit.informatik.junit.TestHelper.expectError;
import static edu.kit.informatik.junit.TestHelper.expectOK;

public class Test023Place {
    /**
     * Here you have to set which parameters you want to use for your test
     */
    public static String[] testArgs;

    /**
     * Executes before ALL tests
     */
    @BeforeAll
    public static void enableTerminalTestingMode() {
        Terminal.enableTestingMode();
    }
    /**
     * Executes before EVERY test
     */
    @BeforeEach
    public void reset() {
        Terminal.reset();
    }
    /**
     * Executes after EVERY test. All @After methods are guaranteed to run even if a Before or Test method
     * throws an exception. The @After methods declared in superclasses will be run after those of the current class.
     */
    @AfterEach
    public void run() {

        // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(testArgs);

        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

    /**
     * This tests if code checks validity of both squares before placing both tokens on a standard board
     * Both tokens are not to be placed if one coordinate is invalid.
     */
    @Test
    public void placeStandardTest() {
        testArgs = new String[] {"standard", "18", "2"};

        expectOK("place 6;3;6;8");
        expectError("place 6;3;2;4");
        expectOK("place 2;4;6;7");
        expectError("place 2;5;2;4");
        expectError("place 2;4;2;5");
        expectOK("place 6;4;6;5");
        expectOK("place 2;3;2;5");
        expectError("place 2;2;2;2");
        expectOK("place 6;9;6;2");
        expectOK("place 2;2;2;6");
        expectOK("place 5;5;4;4");
        expectError("place 2;7;2;3");
        expectError("place 2;3;2;7");
        expectError("place 2;7;2;7");
        expectError("place -1;2;2;7");
        Terminal.addSingleLineOutputThatIsExactly("place 2;7;6;6", "P2 wins");

    }

    /**
     * This tests if code checks validity of both squares before placing both tokens on a torus board
     * Both tokens are not to be placed if one coordinate is invalid.
     */
    @Test
    public void placeTorusTest() {
        testArgs = new String[] {"torus", "20", "2"};

        expectOK("place 26;23;26;28");
        expectError("place 46;23;-18;4");
        expectOK("place 22;4;-14;7");
        expectError("place 2;5;2;4");
        expectError("place 2;24;22;5");
        expectOK("place -14;24;66;5");
        expectOK("place 2;3;2;5");
        expectError("place -18;22;42;2");
        expectOK("place -34;49;26;22");
        expectOK("place 2;2;22;6");
        expectOK("place 5;5;4;4");
        expectError("place 22;47;22;3");
        expectError("place -18;3;-18;27");
        expectError("place 2;7;2;7");
        Terminal.addSingleLineOutputThatIsExactly("place 22;-13;-14;-24", "P2 wins");

    }

}
