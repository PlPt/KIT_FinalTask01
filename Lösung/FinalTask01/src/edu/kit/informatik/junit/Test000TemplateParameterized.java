package edu.kit.informatik.junit;

//import static org.hamcrest.Matchers.startsWith;
//import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import edu.kit.informatik.junit.Wrapper;
import edu.kit.informatik.Terminal;

/**
 * @author Lucas Alber
 * @version 1.2
 */
public class Test000TemplateParameterized {
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
    @CsvSource({ "standard, 18, 2", "standard, 18, 3", "standard, 18, 4",
                 "standard, 20, 2", "standard, 20, 3", "standard, 20, 4",
                 "torus, 18, 2", "torus, 18, 3", "torus, 18, 4",
                 "torus, 20, 2", "torus, 20, 3", "torus, 20, 4"})
    public void argsTest(String arg0, String arg1, String arg2) {
        Terminal.enableTestingMode();
        
        
        // run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(new String[] {arg0, arg1, arg2});
        
        // cleanup
        Terminal.flush();
        Terminal.reset();
    }

}
