package edu.kit.informatik.junit.suite;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;
import static edu.kit.informatik.junit.TestHelper.*;

/**
 * This one tests the rowprint and colprint commands.
 * @author Dominik Bez
 *
 */
public class Test012RowColPrint {

    /**
    *
    * Here you have to set which parameters you want to use for your test
    */
   public static String[] testArgs;
   
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
   public void invalidRowprint() {
       testArgs = new String[] {"standard", "20", "2"};
       expectError("Rowprint 5");
       expectError("row print 8");
       expectError(" rowprint 12");
       expectError("rowprint  18");
       expectError("rowprint 0 ");
       expectError("rowprint 7;8");
       expectError("rowprint 9 0");
       expectError("row-print 6;9");
       expectError("rowPrint 15;19");
       expectError("roprint 8");
   }
   
   @Test
   public void invalidColprint() {
       testArgs = new String[] {"standard", "20", "2"};
       expectError("Colprint 5");
       expectError("col print 8");
       expectError(" colprint 12");
       expectError("colprint  18");
       expectError("colprint 0 ");
       expectError("colprint 7;8");
       expectError("colprint 9 0");
       expectError("col-print 6;9");
       expectError("colPrint 15;19");
       expectError("coprint 8");
   }
   
   @ParameterizedTest
   @CsvSource({ "standard, 18, 2", "torus, 18, 2"})
   public void invalidRowprintTorus(String mode, String size, String p) {
       testArgs = new String[] {mode, size, p};
       expectError("rowprint 18");
       expectError("rowprint -18");
       expectError("rowprint 180");
       expectError("rowprint -1");
       expectError("rowprint -6");
       expectError("rowprint 20");
       
       expectError("colprint 18");
       expectError("colprint -18");
       expectError("colprint 180");
       expectError("colprint -1");
       expectError("colprint -6");
       expectError("colprint 20");
   }
   
   @Test
   public void validStandard() {
       testArgs = new String[] {"standard", "20", "2"};
       Terminal.addSingleLineOutputThatIsExactly("rowprint 6", "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
       Terminal.addSingleLineOutputThatIsExactly("colprint 16", "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
       expectOK("place 6;3;15;16");
       Terminal.addSingleLineOutputThatIsExactly("rowprint 6", "** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
       Terminal.addSingleLineOutputThatIsExactly("colprint 16", "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** P1 ** ** ** **");
   }
   
   @Test
   public void validTorus() {
       testArgs = new String[] {"torus", "20", "2"};
       Terminal.addSingleLineOutputThatIsExactly("rowprint 6", "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
       Terminal.addSingleLineOutputThatIsExactly("colprint 16", "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
       expectOK("place 26;-17;-5;36");
       Terminal.addSingleLineOutputThatIsExactly("rowprint 6", "** ** ** P1 ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
       Terminal.addSingleLineOutputThatIsExactly("colprint 16", "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** P1 ** ** ** **");
   }
    
}
