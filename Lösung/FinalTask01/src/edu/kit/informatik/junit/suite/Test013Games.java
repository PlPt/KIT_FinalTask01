package edu.kit.informatik.junit.suite;

import static edu.kit.informatik.junit.TestHelper.*;
import org.junit.jupiter.api.*;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.Wrapper;


public class Test013Games {

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

    @Test
    public void testWinStandardRow() {
        testArgs = new String[]{"standard", "18", "2"};
        expectOK("place 0;0;0;1");
        expectOK("place 5;4;3;2");
        expectOK("place 0;2;0;3");
        expectOK("place 9;7;6;4");
        Terminal.addSingleLineOutputThatIsExactly("place 0;4;0;5", "P1 wins");
    }

    @Test
    public void testWinTorusRow() {
        testArgs = new String[]{"torus", "18", "2"};
        expectOK("place 0;0;0;1");
        expectOK("place 5;4;3;2");
        expectOK("place 0;2;0;3");
        expectOK("place 9;7;6;4");
        Terminal.addSingleLineOutputThatIsExactly("place 0;-1;0;-2", "P1 wins");
    }

    @Test
    public void testWinStandardColumn() {
        testArgs = new String[]{"standard", "18", "2"};
        expectOK("place 0;0;1;0");
        expectOK("place 0;1;1;1");
        expectOK("place 2;0;3;0");
        expectOK("place 2;1;3;1");
        Terminal.addSingleLineOutputThatIsExactly("place 4;0;5;0", "P1 wins");
    }

    @Test
    public void testWinTorusColumn() {
        testArgs = new String[]{"torus", "18", "2"};
        expectOK("place 0;0;1;0");
        expectOK("place 0;1;1;1");
        expectOK("place 2;0;3;0");
        expectOK("place 2;1;3;1");
        Terminal.addSingleLineOutputThatIsExactly("place -1;0;-2;0", "P1 wins");
    }

    @Test
    public void testWinStandartDiagonalLeftToRight() {
        testArgs = new String[]{"standard", "18", "2"};
        expectOK("place 0;0;1;1");
        expectOK("place 5;6;4;5");
        expectOK("place 2;2;3;3");
        expectOK("place 8;7;6;5");
        Terminal.addSingleLineOutputThatIsExactly("place 4;4;5;5", "P1 wins");
    }

    @Test
    public void testWinTorusDiagonalLeftToRight() {
        testArgs = new String[]{"torus", "18", "2"};
        expectOK("place 0;0;1;1");
        expectOK("place 5;6;4;5");
        expectOK("place 2;2;3;3");
        expectOK("place 8;7;6;5");
        Terminal.addSingleLineOutputThatIsExactly("place -1;-1;-2;-2", "P1 wins");
    }

    @Test
    public void testWinStandartDiagonalRightToLeft() {
        testArgs = new String[]{"standard", "18", "2"};
        expectOK("place 17;17;16;16");
        expectOK("place 5;6;4;5");
        expectOK("place 15;15;14;14");
        expectOK("place 8;7;6;5");
        Terminal.addSingleLineOutputThatIsExactly("place 13;13;12;12", "P1 wins");
    }

    @Test
    public void testWinTorusDiagonalRightToLeft() {
        testArgs = new String[]{"torus", "18", "2"};
        expectOK("place 17;17;16;16");
        expectOK("place 5;6;4;5");
        expectOK("place 15;15;14;14");
        expectOK("place 8;7;6;5");
        Terminal.addSingleLineOutputThatIsExactly("place 18;18;19;19", "P1 wins");
    }

}
