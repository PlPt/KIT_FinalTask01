package de.plpt.CommandProcessor;

import de.plpt.ArgumentParser.ArgumentParser;
import de.plpt.ArgumentParser.ArgumentParserException;
import de.plpt.ArgumentParser.ArgumentParserExecutionException;
import de.plpt.ArgumentParser.CommandInfo;
import de.plpt.Connect6.Connect6Game;
import de.plpt.Connect6.GameFieldType;
import de.plpt.Connect6.Player;
import de.plpt.Connect6.exceptions.Connect6GameException;
import edu.kit.informatik.Terminal;

public class CommandProcessor {

    //region varDef
    private boolean quit = false;
    private ArgumentParser parser;
    private Connect6Game game;

    private GameFieldType type = null;
    private int gameSize = 34;
    private Player[] players;
    private int playerSize;
    private int playerIndex;
    //endregion

    //region constructor
    public CommandProcessor(String[] cliArgs) {
        this.parser = new ArgumentParser(this);

        //region cliCheck
        if (cliArgs.length != 3) {
            throw new IllegalArgumentException("Expected 3 Parameters but got " + cliArgs.length);
        }

        type = GameFieldType.getValueByString(cliArgs[0]);

        if (type == null) {
            throw new IllegalArgumentException(String.format("Parameter[0]: GameType %s not found, please try again", cliArgs[0]));
        }


        try {
            gameSize = Integer.parseInt(cliArgs[1]);
        } catch (NumberFormatException nfex) {
            NumberFormatException nfx = new NumberFormatException(String.format("Parameter[1]: %s is not a valid Integer", cliArgs[1]));
            nfx.initCause(nfex);
            throw nfx;
        }

        if ((gameSize < 17 || gameSize > 21) || gameSize % 2 != 0) {
            throw new IllegalArgumentException("Parameter[1]: Size of game is not in definded range (17,21) or is not even");
        }

        try {
            playerSize = Integer.parseInt(cliArgs[2]);
        } catch (NumberFormatException nfex) {
            NumberFormatException nfx = new NumberFormatException(String.format("Parameter[2]: %s is not a valid Integer", cliArgs[2]));
            nfx.initCause(nfex);
            throw nfx;
        }


        if (playerSize < 2 || playerSize > 4) {
            throw new IllegalArgumentException(String.format("Parameter[2]: Number of players in not between 2 and 4; given value: %s", playerSize));
        }
        //endregion

        players = new Player[playerSize];
        for (int i = 0; i < playerSize; i++) {
            players[i] = new Player("P" + (i + 1));
        }
        playerIndex = 0;
        this.game = new Connect6Game(type, gameSize);
    }
    //endregion

    //region processCommands
    public void processCommands() {

        while (!quit) {
            String line = Terminal.readLine();
            try {
                String result = parser.parse(line);
                if (result != null) Terminal.printLine(result);
            } catch (ArgumentParserExecutionException apeex) {
                Exception clientException = apeex.getTypedCause();
                Terminal.printError(String.format("[ArgumentParserExecutionException][%s] :: %s", clientException.getClass().getName(), clientException.getMessage()));
            } catch (ArgumentParserException apex) {

                Terminal.printError(String.format("[ArgumentParserException] :: %s", apex.getMessage()));
            } catch (Exception x) {
                Terminal.printError(String.format("[EX][%s] :: %s", x.getClass().getName(), x.getMessage()));
            }
        }
    }
    //endregion

    //region Methods


    //region place
    @CommandInfo(command = "place (\\-?\\d+);(\\-?\\d+);(\\-?\\d+);(\\-?\\d+)")
    public String place(int rowNo1, int columnNo1, int rowNo2, int columnNo2) throws Connect6GameException {

        if (game.getWinner() != null && !game.getWinner().equals(Player.NONE)) {
            throw new Connect6GameException(String.format("There is already a winner: %s won", game.getWinner().toString()));
        }
        else if(game.getWinner() != null && game.getWinner().equals(Player.NONE)){
            throw new Connect6GameException(String.format("There is no winner winner, game end with DRAW", game.getWinner().toString()));
        }


        boolean success = game.place(players[playerIndex], rowNo1, columnNo1, rowNo2, columnNo2);

        if (!game.getBoard().hasFreeRows() && success){
            game.setWinner(Player.NONE);
            return "draw";
        }

        if (success && (game.checkWinner(rowNo1, columnNo1, players[playerIndex])
                || game.checkWinner(rowNo2, columnNo2, players[playerIndex]))) {
            return String.format("%s wins", players[playerIndex]);
        }

        playerIndex = (playerIndex + 1) % (playerSize);//set Next player

        return "OK";
    }
    //endregion


    //region rowprint
    @CommandInfo(command = "rowprint (\\d+)")
    public String rowprint(int rowNo) {

        return game.getRow(rowNo).toString();
    }
    //endregion


    //region colprint
    @CommandInfo(command = "colprint (\\d+)")
    public String colprint(int colNo) {
        return game.printColumn(colNo);
    }
    //endregion

    //region print
    @CommandInfo(command = "print")
    public String print() {
        return game.getBoard().toString();
    }
    //endregion


    //region state
    @CommandInfo(command = "state (\\d+);(\\d+)")
    public String state(int rowNum, int colNum) {
        return game.getBoard().getCell(rowNum, colNum).getPlayer().toString();
    }
    //endregion

    //region reset
    @CommandInfo(command = "reset")
    public String reset() {
        playerIndex =0;
        game.reset();
        return "OK";
    }
    //endregion

    //region quit
    @CommandInfo(command = "quit")
    public void quit() {
        this.quit = true;
    }
    //endregion


    //endregion
}
