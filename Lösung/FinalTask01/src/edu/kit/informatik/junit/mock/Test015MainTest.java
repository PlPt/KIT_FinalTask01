package edu.kit.informatik.junit.mock;

import static com.google.common.truth.Truth.assertThat;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.TerminalMock;
import edu.kit.informatik.junit.Wrapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * Tests the Main class
 * 
 * @author Lucas Alber
 * @author Valentin Wagner
 * 09.02.2018
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
public class Test015MainTest {

    private TerminalMock terminalMock;

    public Test015MainTest(){}

    @Before
    public void init(){
        this.terminalMock = new TerminalMock();
    }

    @Test
    public void main() throws Exception {

        terminalMock.mock().willReturn("quit");

        Wrapper.main(new String[] {"standard", "18", "3"}); //standard correct
        assertThat(terminalMock.isError()).isFalse();

        Wrapper.main(new String[] {"torus", "20", "4"});//torus correct
        assertThat(terminalMock.isError()).isFalse();

        Wrapper.main(new String[] {"standard2", "18", "3"}); //wrong game mode
        assertThat(terminalMock.isError()).isTrue();

        Wrapper.main(new String[] {"standard", "19", "3"}); //wrong board size
        assertThat(terminalMock.isError()).isTrue();

        Wrapper.main(new String[] {"standard", "18", "5"}); //too many players
        assertThat(terminalMock.isError()).isTrue();

        Wrapper.main(new String[] {null, null, null}); //no args at all
        assertThat(terminalMock.isError()).isTrue();

        Wrapper.main(new String[]{"standard", "18", "4", "ago"}); //too many args
        assertThat(terminalMock.isError()).isTrue();
    }



}