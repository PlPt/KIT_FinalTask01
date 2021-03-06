package edu.kit.informatik.junit.mock;

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
 * StateTest
 * @author Lucas Alber
 * @author Valentin Wagner
 *         12.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
public class Test022StateTest {

    private TerminalMock terminalMock;

    public Test022StateTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }


    @Test
    public void testStandartError() throws Exception {
        terminalMock.mock()
                .willReturn("state")
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state -1;2")
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state 1;2 ")
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state 1;18")
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();
    }

    @Test
    public void testTorusError() throws Exception {
        terminalMock.mock()
                .willReturn("state")
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("state 1;2 ")
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();
    }

    @Test
    public void testStandartEmpty() throws Exception {
        terminalMock.mock()
                .willReturn("state 0;0")
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "2"});
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("**");
    }


    @Test
    public void testTorusEmpty() throws Exception {
        terminalMock.mock()
                .willReturn("state -1;-1")
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "2"});
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("**");
    }


    @Test
    public void testStandartWithPlacement() throws Exception {
        terminalMock.mock()
                .willReturn("place 0;0;1;1")
                .willReturn("state 0;0")
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "2"});
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1");
    }

    @Test
    public void testTorusWithPlacement() throws Exception {
        terminalMock.mock()
                .willReturn("place -1;-1;1;1")
                .willReturn("state -1;-1")
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "2"});
        assertThat(terminalMock.isError()).isFalse();
        assertThat(terminalMock.getCaptor().getValue()).isEqualTo("P1");
    }
}