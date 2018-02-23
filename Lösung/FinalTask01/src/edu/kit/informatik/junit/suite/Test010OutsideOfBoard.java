package edu.kit.informatik.junit.suite;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.kit.informatik.Terminal;
import static edu.kit.informatik.junit.TestHelper.*;
import edu.kit.informatik.junit.Wrapper;

/**
 * This is a JUnit test. 
 * Unofficial naming convention: TestXXXName[UpdatedXX].java Where XXX are the highest
 * number not used by an other author. Please check this before uploading. [] are optional if 
 * you want to provide an update. Do not delete the old version because maybe someone used it in a test suite.
 * 
 * @author Lucas Alber
 * @version 1.0
 */
class Test010OutsideOfBoard {
    /**
     *
     * Here you have to set which parameters you want to use for your test
     */
    public static String[] testArgs; // TODO Set arguments to test
    
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
    public void runTests() {
        
        // Run and terminate
        Terminal.addNoOutput("quit");
        Wrapper.main(testArgs);
        
        // Tell the Terminal to check the outputs and to cleanup
        Terminal.flush();
        Terminal.reset();
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void rowprintTest() {
        testArgs = new String[] {"standard", "18", "2"};
        expectError("rowprint 18");
        expectError("rowprint 19");
        expectError("rowprint -1");
        expectError("rowprint ;");
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void rowprintTest2() {
        testArgs = new String[] {"standard", "20", "2"};
        Terminal.addSingleLineOutputThatMatches("rowprint 18", Matchers.containsString("**"));
        Terminal.addSingleLineOutputThatMatches("rowprint 19", Matchers.containsString("**"));
        expectError("rowprint 20");
        expectError("rowprint -1");
        expectError("rowprint ;");
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void colprintTest() {
        testArgs = new String[] {"standard", "18", "2"};
        expectError("colprint 18");
        expectError("colprint 19");
        expectError("colprint -1");
        expectError("colprint ;");
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void colprintTest2() {
        testArgs = new String[] {"standard", "20", "2"};
        Terminal.addSingleLineOutputThatMatches("colprint 18", Matchers.containsString("**"));
        Terminal.addSingleLineOutputThatMatches("colprint 19", Matchers.containsString("**"));
        expectError("colprint 20");
        expectError("colprint -1");
        expectError("colprint ;");
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void stateTest1() {
        testArgs = new String[] {"standard", "18", "2"};
        expectError("state 18;18");
        expectError("state 18;17");
        expectError("state 17;18");
        expectError("state -1;2");
        expectError("state  ; ");
        expectError("state ; ");
        expectError("state ;");
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void stateTest2() {
        testArgs = new String[] {"standard", "20", "2"};
        Terminal.addSingleLineOutputThatIsExactly("state 18;18", "**");
        Terminal.addSingleLineOutputThatIsExactly("state 19;19", "**");
        expectError("state 20;20");
        expectError("state 19;20");
        expectError("state 20;19");
        expectError("state -1;2");
        expectError("state  ; ");
        expectError("state ; ");
        expectError("state ;");
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @Test
    public void stateTest3() {
        testArgs = new String[] {"torus", "18", "2"};
        Terminal.addSingleLineOutputThatIsExactly("state 18;18", "**");
        Terminal.addSingleLineOutputThatIsExactly("state 19;19", "**");
        Terminal.addSingleLineOutputThatIsExactly("state 20;20", "**");
        Terminal.addSingleLineOutputThatIsExactly("state 19;20", "**");
        Terminal.addSingleLineOutputThatIsExactly("state 20;19", "**");
        Terminal.addSingleLineOutputThatIsExactly("state -1;2", "**");
        Terminal.addSingleLineOutputThatIsExactly("state -1;-22", "**");
        expectError("place 18;0;0;0");
        expectError("place 36;0;0;0");
        expectError("place 18;18;0;0");
        expectError("place 36;36;0;0");
        expectError("place 0;18;0;0");
        expectError("place 0;36;0;0");
        expectError("place 0;0;18;0");
        expectError("place 0;0;36;0");
        expectError("place 0;0;18;18");
        expectError("place 0;0;36;36");
        expectError("place 0;0;0;18");
        expectError("place 0;0;0;36");
        expectError("state  ; ");
        expectError("state ; ");
        expectError("state ;");
        expectOK("place 4;19;3;19");
        expectError("place 4;19;5;6");
        expectError("place 22;1;5;5");
        Terminal.addSingleLineOutputThatIsExactly("state 4;1", "P1");
    }
    
    
}
