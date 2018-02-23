package edu.kit.informatik.junit.suite;

import org.junit.jupiter.api.*;
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
public class Test006ArgumentsNotOk {
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
     * Executes after EVERY test. All @AfterEach methods are guaranteed to run even if a Before or Test method 
     * throws an exception. The @AfterEach methods declared in superclasses will be run after those of the current class.
     */
    @AfterEach
    public void runTests() {
        // Tell the Terminal to check the outputs and to cleanup
        Terminal.flush();
        Terminal.reset();
    }
    
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
    @CsvSource({ "Standard, 18, 2", "standarD, 18, 2", ", 18, 2", "stand, 18, 2", "tors, "
            + "18, 2", "Torus, 18, 2", "toruS, 18, 2",
                 "standard, -1, 2", "standard, 0, 2", "standard, -10000000000000000, 2", 
                 "standard, 10000000000000000, 2", 
                 "standard, , 2", "standard, 16, 2", "standard, 17, 2", "standard, 17, 2", 
                 "standard, 19, 2", "standard, 21, 2",
                 "standard, 21.4, 2", "standard, 20.6, 2", "torus, 18.2, 2", 
                 "torus, 19.7, 2", "torus, 0.0, 2", "torus, 18, 10000000000000000", "torus, 18, -1", "torus, 18, 1",
                 "torus, -1, 2", "torus, 0, 2", "torus, -10000000000000000, 2", 
                 "torus, 10000000000000000, 2", "standard, 20, 2.1", "standard, 20, 2.4",
                 "torus, , 2", "torus, 16, 2", "torus, 17, 2", "torus, 17, 2", 
                 "torus, 19, 2", "torus, 21, 2", "standard, 18, 10000000000000000",
                 "standard, 18, 0", "standard, 18, ", "standard, 18, -10000000000000000",  
                 "standard, 18, -1", "standard, 18, 1",
                 "standard, 20, 5", "standard, 20, 6", "standard, 20, 3.0", "standard, 20, 20", 
                 "torus, 18, 0", "torus, 18, ", "torus, 18, -10000000000000000", 
                 "torus, 20, 5", "torus, 20, 6", "torus, 20, 2.7", "torus, 20, 20", "torus, 20, 2.1", "torus, 20, 2.4",
                 })
    public void argsTest(String arg0, String arg1, String arg2) {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {arg0, arg1, arg2});
        
    }
    
    /**
     * Tests with to less or to much elements
     */
    @Test
    public void argsTest2() {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {"standard"});
    }
    
    /**
     * Tests with to less or to much elements
     */
    @Test
    public void argsTest3() {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {"18", "2"});
    }
    
    /**
     * Tests with to less or to much elements
     */
    @Test
    public void argsTest4() {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {"standard", "20", "2", ";" });
    }
    
    /**
     * Tests with to less or to much elements
     */
    @Test
    public void argsTest5() {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {"standard", "20", "2", null });
    }
    
    /**
     * Tests with to less or to much elements
     */
    @Test
    public void argsTest6() {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {"standard", "20", "2", " " });
    }
    
    /**
     * Tests with to less or to much elements
     */
    @Test
    public void argsTest7() {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {});
    }
    
    /**
     * Tests with to less or to much elements
     */
    @Test
    public void argsTest8() {
        expectErrorWithoutInput();
     // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main();
    }


}
