package edu.kit.informatik.junit.suite;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.kit.informatik.junit.TestHelper.*;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;

/**
 * @author Lucas Alber
 * @version 1.0
 */
public class Test009Place {
    /**
     * This is the actual test. It is possible to add more test methods.
     * 
     * Null pointer means you failed the test!
     * 
     * @param arg0 board
     * @param arg1 size
     * @param arg2 players
     */
    @ParameterizedTest
    @CsvSource({ "standard, 18, 2", "torus, 18, 2"})
    public void argsTest(String arg0, String arg1, String arg2) {
        Terminal.enableTestingMode();
        
        expectOK("place 0;0;0;1");
        expectError("palce 0;0;2;1");
        expectOK("place 2;1;2;2");
        expectError("place 3;1;0;0");
        expectOK("place 0;2;0;3");
        expectOK("place 2;3;2;4");
        Terminal.addSingleLineOutputThatIsExactly("place 0;4;0;5", "P1 wins");
        expectError("place 5;5;5;6");

        
        // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {arg0, arg1, arg2});
        
        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

}
