package edu.kit.informatik.junit.suite;

import org.junit.jupiter.api.*;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;
import static edu.kit.informatik.junit.TestHelper.*;

/**
 * This one tests the state command
 * @author Dominik Bez
 *
 */
public class Test011State {

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
   public void invalidStateStandard() {
       testArgs = new String[] {"standard", "20", "4"};
       expectError("State 0;0");
       expectError(" state 6;7");
       expectError("statE 4;7");
       expectError("state  4;8");
       expectError("state 5; 9");
       expectError("state4;8");
       expectError("state-7;1");
       expectError("state -1;5");
       expectError("state 20;5");
       expectError("state 90;4");
       expectError("state 5;-8");
       expectError("state 4;21");
       expectError("\nstate 5;8");
       expectError("state 6;2\n");
       expectError("\tstate 12;15");
       expectError("state 8;19\t");
       expectError("STATE 14;4");
       expectError("0state 8;13");
       expectError("sstate 9;18");
       expectError("state 0;20");
       expectError("state -20;0");
   }
   
   @Test
   public void invalidStateTorus() {
       testArgs = new String[] {"torus", "18", "3"};
       expectError("state 20,5;4");
       expectError("state 20.5;4");
       expectError("state 5;6;7");
   }
   
   @Test
   public void stateTestStandard() {
       testArgs = new String[] {"standard", "18", "3"};
       Terminal.addSingleLineOutputThatIsExactly("state 0;0", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 6;3", "**");
       
       expectOK("place 6;9;14;16");
       expectOK("place 8;5;2;16");
       expectOK("place 17;17;15;15");
       expectOK("place 6;8;3;8");
       Terminal.addSingleLineOutputThatIsExactly("state 6;9", "P1");
       Terminal.addSingleLineOutputThatIsExactly("state 14;16", "P1");
       Terminal.addSingleLineOutputThatIsExactly("state 8;5", "P2");
       Terminal.addSingleLineOutputThatIsExactly("state 2;16", "P2");
       Terminal.addSingleLineOutputThatIsExactly("state 17;17", "P3");
       Terminal.addSingleLineOutputThatIsExactly("state 15;15", "P3");
       Terminal.addSingleLineOutputThatIsExactly("state 6;8", "P1");
       Terminal.addSingleLineOutputThatIsExactly("state 3;8", "P1");
       
       expectOK("reset");
       Terminal.addSingleLineOutputThatIsExactly("state 6;9", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 14;16", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 8;5", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 2;16", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 17;17", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 15;15", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 6;8", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 3;8", "**");
   }
   
   @Test
   public void stateTestTorus() {
       testArgs = new String[] {"torus", "20", "4"};
       Terminal.addSingleLineOutputThatIsExactly("state 0;0", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 6;3", "**");
       Terminal.addSingleLineOutputThatIsExactly("state -7;5", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 6735;30", "**");
       
       expectOK("place 4;7;8;15");
       expectOK("place 19;16;0;7");
       expectOK("place -4;-8;52;-1");
       expectOK("place 98;66;-4;-1");
       expectOK("place 22;-100;500;100020");
       Terminal.addSingleLineOutputThatIsExactly("state 24;-13", "P1");
       Terminal.addSingleLineOutputThatIsExactly("state -12;-5", "P1");
       Terminal.addSingleLineOutputThatIsExactly("state -1;36", "P2");
       Terminal.addSingleLineOutputThatIsExactly("state 20;7", "P2");
       Terminal.addSingleLineOutputThatIsExactly("state 16;12", "P3");
       Terminal.addSingleLineOutputThatIsExactly("state 12;19", "P3");
       Terminal.addSingleLineOutputThatIsExactly("state 18;6", "P4");
       Terminal.addSingleLineOutputThatIsExactly("state 16;19", "P4");
       Terminal.addSingleLineOutputThatIsExactly("state 2;0", "P1");
       Terminal.addSingleLineOutputThatIsExactly("state 0;0", "P1");
       
       expectOK("reset");
       Terminal.addSingleLineOutputThatIsExactly("state 24;-13", "**");
       Terminal.addSingleLineOutputThatIsExactly("state -12;-5", "**");
       Terminal.addSingleLineOutputThatIsExactly("state -1;36", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 20;7", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 16;12", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 12;19", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 18;6", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 16;19", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 2;0", "**");
       Terminal.addSingleLineOutputThatIsExactly("state 0;0", "**");
   }
    
}
