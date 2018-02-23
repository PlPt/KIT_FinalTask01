package edu.kit.informatik.junit.suite;

//import static org.hamcrest.Matchers.startsWith;
//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static edu.kit.informatik.junit.TestHelper.*;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;

/**
 * @author Lucas Alber
 * @author Dominik Bez
 * @version 1.0
 */
public class Test001BeispielInteraktion {
    /**
     * Here you have to set which parameters you want to use for your test
     */
    public static String[] testArgs = {"standard", "18", "2"};
    
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
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void assignmentInteraction() {
        expectOK("place 6;3;6;8");
        expectOK("place 3;2;1;7");
        expectOK("place 6;4;6;7");
        expectOK("reset");
        expectOK("place 6;3;6;8");
        expectOK("place 3;2;1;7");
        expectOK("place 6;4;6;7");
        expectOK("place 6;9;6;2");
        Terminal.addSingleLineOutputThatIsExactly("place 6;5;6;6", "P1 wins");
        Terminal.addSingleLineOutputThatIsExactly("rowprint 6", "** ** P2 P1 P1 P1 P1 P1 P1 "
                + "P2 ** ** ** ** ** ** ** **");
    }

}
