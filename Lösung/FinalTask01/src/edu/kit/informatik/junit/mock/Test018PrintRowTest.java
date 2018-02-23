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
 * PrintRowTest
 *
 * @author Valentin Wagner
 *         10.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
public class Test018PrintRowTest {

    private TerminalMock terminalMock;

    public Test018PrintRowTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }



    @Test
    public void testRowPrintError() throws Exception{
        terminalMock.mock()
                .willReturn("rowprint") //no args
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("rowprint ") //space
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("rowprint -1") //-1 on standard game mode
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("rowprint 18") //out of bounds on standard
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("rowprint -1") //torus game mode with row -1
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "3"});
        assertThat(terminalMock.isError()).isTrue();
    }

    @Test
    public void testRowPrint() throws Exception{
        terminalMock.mock()
                .willReturn("rowprint 1") //standard game mode with row 1
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"});
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
    }

    @Test
    public void testRowPrintWithPlacement() throws Exception{
        terminalMock.mock()
                .willReturn("place 0;0;0;2")
                .willReturn("rowprint 0")
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "4"});
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1 ** P1 ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
    }

}