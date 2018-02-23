package edu.kit.informatik.junit.suite;

import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;

/**
 * Test for quit
 * 
 * @author Micha Hanselmann
 * @author Lucas Alber
 */
public class Test002Quit {
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
    public void invalid() {
        Terminal.addSingleLineOutputThatMatches("quita", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit-", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit ", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches(" quit", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit ;;", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("quit quit", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("\tquit", startsWith("Error,"));
        // Terminal.addSingleLineOutputThatMatches("quit", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("QUIT", startsWith("Error,"));
        Terminal.addSingleLineOutputThatMatches("Quit", startsWith("Error,"));
        
    }

}
