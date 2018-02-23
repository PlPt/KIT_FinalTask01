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
 * QuitTest
 * 
 * @author Lucas Alber
 * @author Valentin Wagner
 *         09.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
public class Test021QuitTest {

    private TerminalMock terminalMock;

    public Test021QuitTest() {
    }

    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }


    @Test
    public void testQuitWithoutArgs() throws Exception {
        terminalMock.mock()
                .willReturn("quit ") //space at the end
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn("quit 346;aww") //args
                .willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();

        terminalMock.mock()
                .willReturn(" quit ") //spaces
                .willReturn("quit");

        Wrapper.main(new String[] {"torus", "18", "2"});
        assertThat(terminalMock.isError()).isTrue();
    }


}