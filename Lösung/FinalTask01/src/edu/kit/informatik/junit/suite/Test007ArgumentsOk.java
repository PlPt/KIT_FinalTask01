package edu.kit.informatik.junit.suite;

//import static org.hamcrest.Matchers.startsWith;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static edu.kit.informatik.junit.TestHelper.*;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;

/**
 * @author Lucas Alber
 * @version 1.0
 */
public class Test007ArgumentsOk {  
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
    @CsvSource({ "standard, 18, 2", "standard, 18, 3", "standard, 18, 4",
                 "standard, 20, 2", "standard, 20, 3", "standard, 20, 4",
                 "torus, 18, 2", "torus, 18, 3", "torus, 18, 4",
                 "torus, 20, 2", "torus, 20, 3", "torus, 20, 4"})
    public void argsTest(String arg0, String arg1, String arg2) {
        Terminal.enableTestingMode();
        
        expectOK("place 0;0;0;1");
        
        // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {arg0, arg1, arg2});
        
        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

}
