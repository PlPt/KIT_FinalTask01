package edu.kit.informatik.junit.suite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.TestHelper;
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
class Test008Parsing {
    /**
     *
     * Here you have to set which parameters you want to use for your test
     */
    public static String[] testArgs = {"standard", "18", "2"}; // TODO Set arguments to test
    
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
    @ParameterizedTest
    @CsvSource({ "standard, 18, 2", "torus, 18, 4"})
    public void testPlace(String arg0, String arg1, String arg2) {
        TestHelper.expectError("place 100000000000000000000;0;1;0");
        TestHelper.expectError("place 0;1000000000000000000000;0;1;0");
        TestHelper.expectError("place 0;0;10000000000000000000000;1;0");
        TestHelper.expectError("place 0;0;1;-1000000000000000000000000");
        TestHelper.expectError("place -100000000000000000000;0;1;0");
        TestHelper.expectError("place 0;-1000000000000000000000;0;1;0");
        TestHelper.expectError("place 0;0;-10000000000000000000000;1;0");
        TestHelper.expectError("place 0;0;1;-1000000000000000000000000");
        TestHelper.expectError("place 0;0;1;1b");
        TestHelper.expectError("place 0;0;b1;1");
        TestHelper.expectError("place 0;b0;1;1");
        TestHelper.expectError("place 0b;0;1;1");
        TestHelper.expectError("place 0;a;1;1");
        TestHelper.expectError("place 0;a;;1");
        
        testArgs = new String[] {arg0, arg1, arg2};
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @ParameterizedTest
    @CsvSource({ "standard, 18, 2", "torus, 18, 4"})
    public void testState(String arg0, String arg1, String arg2) {
        TestHelper.expectError("state 10000000000000000000;1");
        TestHelper.expectError("state 1;10000000000000000000");
        TestHelper.expectError("state 1;-1000000000000000000");
        TestHelper.expectError("state -100000000000000000;-1");
        TestHelper.expectError("state b;1");
        TestHelper.expectError("state 0;b");
        TestHelper.expectError("state 0; ");
        TestHelper.expectError("state 0;");
        
        testArgs = new String[] {arg0, arg1, arg2};
    }
    
    /**
     * This is the actual test. It is possible to add more test methods.
     */
    @ParameterizedTest
    @CsvSource({ "standard, 18, 2", "torus, 18, 4"})
    public void testColRowprint(String arg0, String arg1, String arg2) {
        TestHelper.expectError("rowprint 100000000000000000000");
        TestHelper.expectError("rowprint -10000000000000000000");
        TestHelper.expectError("colprint 100000000000000000000");
        TestHelper.expectError("colprint -10000000000000000000");
        TestHelper.expectError("rowprint a");
        TestHelper.expectError("colprint b");
        TestHelper.expectError("rowprint ");
        TestHelper.expectError("colprint ");
        TestHelper.expectError("rowprint  ");
        TestHelper.expectError("colprint  ");
        TestHelper.expectError("rowprint");
        TestHelper.expectError("colprint");
        
        testArgs = new String[] {arg0, arg1, arg2};
    }



}
