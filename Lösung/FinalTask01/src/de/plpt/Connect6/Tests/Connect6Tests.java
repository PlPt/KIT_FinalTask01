package de.plpt.Connect6.Tests;

import de.plpt.Main;
import edu.kit.informatik.Terminal;
import org.junit.Test;

public class Connect6Tests {

    String gameType  = "";
    int gameSize = 0;
    int playerSize = 0;

    @org.junit.Before
    public void begin() {
        gameType = "";
        gameSize = 0;
        playerSize = 0;
        Terminal.enableTestingMode();
        Terminal.reset();
    }

    private  void  setParams(String gameType,int gameSize,int playerSize){

        this.gameType = gameType;
        this.gameSize = gameSize;
        this.playerSize = playerSize;
    }

    @Test
    public void defaultGivenTest() {
        setParams("standard",18,2);
        Terminal.addSingleLineOutputThatIsExactly("place 6;3;6;8", "OK");
        Terminal.addSingleLineOutputThatIsExactly("place 3;2;1;7", "OK");
        Terminal.addSingleLineOutputThatIsExactly("place 6;4;6;7", "OK");
        Terminal.addSingleLineOutputThatIsExactly("reset", "OK");
        Terminal.addSingleLineOutputThatIsExactly("place 6;3;6;8", "OK");
        Terminal.addSingleLineOutputThatIsExactly("place 3;2;1;7", "OK");
        Terminal.addSingleLineOutputThatIsExactly("place 6;4;6;7", "OK");
        Terminal.addSingleLineOutputThatIsExactly("place 6;9;6;2", "OK");
        Terminal.addSingleLineOutputThatIsExactly("place 6;5;6;6", "OK");
        Terminal.addSingleLineOutputThatIsExactly("rowprint 6", "** ** P2 P1 P1 P1 P1 P1 P1 P2 ** ** ** ** ** ** ** **");
        Terminal.addNoOutput("quit");
        callMain();
    }

    private void callMain() {
        Main.main(new String[]{gameType,String.valueOf(gameSize),String.valueOf(playerSize)});
        Terminal.flush();
    }
}
