package edu.kit.informatik.junit.suite;

import static edu.kit.informatik.junit.TestHelper.*;
import org.junit.jupiter.api.*;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;

/**
 * This one tests whole torus scenarios.
 * @author Dominik Bez
 * @version 1.0
 *
 */
public class Test004CompleteTorusGames {

    /**
    *
    * Here you have to set which parameters you want to use for your test
    */
   public static String[] testArgs = {"torus", "20", "2"};
   
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
   public void completeTorusGame1() {
       expectOK("place 0;0;1;1");
       expectOK("place 4;7;8;9");
       expectError("place 6;6;4;7");
       expectOK("place 2;2;3;3");
       expectOK("place 9;17;6;9");
       expectError("place 1;1;6;8");
       expectError("place 5; 6;17;7");
       expectError("place 5 ;6;17;7");
       Terminal.addSingleLineOutputThatIsExactly("place -1;-1;-2;-2", "P1 wins");
       expectError("place 17;17;16;15");
   }
   
   /**
    * Tests to complete a torus game
    */
   @Test
   public void comleteTorusGame2() {
       expectOK("place -2;4;-3;5");
       expectOK("place 0;0;0;1");
       expectOK("place -1;3;-4;6");
       expectOK("place 0;2;0;3");
       Terminal.addSingleLineOutputThatIsExactly("place -5;7;-6;8", "P1 wins");
       expectError("place 7;3;9;9");
       Terminal.addSingleLineOutputThatIsExactly("print", "P2 P2 P2 P2 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
   }
   
   /**
    * Test to complete a torus game
    */
   @Test
   public void completeTorusGame3() {
       expectOK("place 0;0;0;1");
       expectOK("place 17;3;18;4");
       expectOK("place 5;6;7;8");
       expectOK("place 19;5;20;6");
       expectOK("place 0;15;1;18");
       Terminal.addSingleLineOutputThatIsExactly("place 22;8;21;7", "P2 wins");
   }
    
}
