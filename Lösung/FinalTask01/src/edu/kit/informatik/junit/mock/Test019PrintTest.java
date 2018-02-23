package edu.kit.informatik.junit.mock;

import static org.junit.Assert.*;
import static com.google.common.truth.Truth.assertThat;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.TerminalMock;
import edu.kit.informatik.junit.Wrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * PrintTest
 * @author Lucas Alber
 * @author Valentin Wagner
 *         09.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
public class Test019PrintTest {

    private TerminalMock terminalMock;

    public Test019PrintTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }

    @Test
    public void testPrintWorks() throws Exception{
        terminalMock.mock()
                .willReturn("print") //empty board at the beginning
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "4"});

        assertThat(terminalMock.getCaptor().getValue()).isEqualTo(
                "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **\n" +
                        "** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **"
        );
    }

    @Test
    public void testPrintWithoutArgs() throws Exception {
        terminalMock.mock()
                .willReturn(" print") //space at the beginning
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("print ") //space at the end
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});

        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("print") //correct command
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});

        assertThat(terminalMock.isError()).isFalse();
    }


}