package edu.kit.informatik.junit.mock;

import static com.google.common.truth.Truth.assertThat;
import edu.kit.informatik.Terminal;
import edu.kit.informatik.junit.TerminalMock;
import edu.kit.informatik.junit.Wrapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

/**
 * ExampleTest
 *
 * @author Lucas Alber
 * @author Valentin Wagner
 *         12.02.18
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Terminal.class)
public class Test014ExampleTest {
    
    private TerminalMock terminalMock;

    public Test014ExampleTest() {}
    
    @Before
    public void init() {
        this.terminalMock = new TerminalMock();
    }


    @Test
    public void testExampleFromThePDF() throws Exception {
        terminalMock.mock()
                .willReturn("place 6;3;6;8")
                .willReturn("place 3;2;1;7")
                .willReturn("place 6;4;6;7")
                .willReturn("reset")
                .willReturn("place 6;3;6;8")
                .willReturn("place 3;2;1;7")
                .willReturn("place 6;4;6;7")
                .willReturn("place 6;9;6;2")
                .willReturn("place 6;5;6;6")
                .willReturn("rowprint 6")
                .willReturn("quit");

        String[] outputs = new String[]{
                "OK", "OK", "OK", "OK",
                "OK", "OK", "OK", "OK",
                "P1 wins",
                "** ** P2 P1 P1 P1 P1 P1 P1 P2 ** ** ** ** ** ** ** **"
        };

        Wrapper.main(new String[] {"standard", "18", "2"});

        List<String> actualOutput = terminalMock.getCaptor().getAllValues();
        for(int i = 0; i < actualOutput.size(); i++){
            assertThat(actualOutput.get(i)).isEqualTo(outputs[i]);
        }

        assertThat(terminalMock.isError()).isFalse();    }


}