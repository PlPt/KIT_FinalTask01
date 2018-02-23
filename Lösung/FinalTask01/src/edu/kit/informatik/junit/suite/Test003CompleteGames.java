package edu.kit.informatik.junit.suite;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;

import static edu.kit.informatik.junit.TestHelper.*;

import org.junit.jupiter.api.*;

/**
 * This one tests whole scenarios.
 * @author Dominik Bez
 * @version 1.0
 *
 */
public class Test003CompleteGames {

    /**
    *
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
   public void completeGame1() {
       expectOK("place 0;0;1;1");
       expectOK("place 4;7;8;9");
       expectError("place 6;6;4;7");
       expectOK("place 2;2;3;3");
       expectOK("place 9;17;6;9");
       expectError("place 1;1;6;8");
       expectError("place 18;0;15;4");
       Terminal.addSingleLineOutputThatIsExactly("place 4;4;5;5", "P1 wins");
       expectError("place 17;17;16;15");
   }
   
   /**
    * Test if it completes the game
    */
   @Test
   public void completeGame2() {
       expectError("place 2;7;2;7");
       expectOK("place 2;7;4;5");
       Terminal.addSingleLineOutputThatIsExactly("state 2;7", "P1");
       expectOK("place 5;5;16;3");
       expectOK("place 2;13;6;6");
       expectOK("place 2;17;2;0");
       Terminal.addSingleLineOutputThatIsExactly("rowprint 2", "P2 ** ** ** ** ** ** P1 ** ** ** ** ** P1 ** ** ** P2");
       expectOK("place 2;8;2;9");
       Terminal.addSingleLineOutputThatIsExactly("colprint 17", "** ** P2 ** ** ** ** ** ** "
               + "** ** ** ** ** ** ** ** **");
       expectOK("place 0;0;0;1");
       expectOK("place 2;10;2;11");
       expectOK("place 0;2;0;3");
       Terminal.addSingleLineOutputThatIsExactly("place 2;14;2;12", "P1 wins");
       /*Terminal.addMultipleLinesOutputThatIsExactly("print",
               Arrays.asList("P2 P2 P2 P2 ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "P2 ** ** ** ** ** ** P1 P1 P1 P1 P1 P1 P1 P1 ** ** P2",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** P2 ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** P2 ** ** ** ** ** ** ** ** ** ** ** ** ** **",
                             "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **"));*/
       Terminal.addSingleLineOutputThatIsExactly("print", "P2 P2 P2 P2 ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "P2 ** ** ** ** ** ** P1 P1 P1 P1 P1 P1 P1 P1 ** ** P2\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** P2 ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** P1 ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** P2 ** ** ** ** ** ** ** ** ** ** ** ** ** **\n"
                                                        + "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
       expectError("place 17;17;16;14");
   }
   
   /**
    * Test for draw
    */
   @Test
   public void draw() {
       for (int i = 0; i < 17; i++) {
           for (int j = 0; j < 17; j += 2) {
               expectOK("place " + i + ";" + j + ";" + i + ";" + (j + 1));
           }
       }
       Terminal.addSingleLineOutputThatIsExactly("rowprint 16", "P1 P1 P2 P2 P1 P1 P2 P2 P1 "
               + "P1 P2 P2 P1 P1 P2 P2 P1 P1");
       for (int i = 0; i < 15; i += 2) {
           expectOK("place " + 17 + ";" + i + ";" + 17 + ";" + (i + 1));
       }
       Terminal.addSingleLineOutputThatIsExactly("place 17;16;17;17", "draw");
       expectError("place 0;0;13;14");
       expectError("place 18;-5;-8;9");
   }
    
}
