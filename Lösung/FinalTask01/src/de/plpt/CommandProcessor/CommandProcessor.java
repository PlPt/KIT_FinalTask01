package de.plpt.CommandProcessor;

import de.plpt.ArgumentParser.*;
import de.plpt.Connect6.CellPosition;
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

    /**
     * Initializes a new CommandProcessor object
     *
     * @param cliArgs CommandLineArguments as Game basic Parameters
     */
    public CommandProcessor(String[] cliArgs) {
        this.parser = new ArgumentParser(this);

        //region cliCheck
        if (cliArgs.length != 3) {
            throw new IllegalArgumentException("Expected 3 Parameters but got " + cliArgs.length);
        }

        type = GameFieldType.getValueByString(cliArgs[0]);

        if (type == null) {
            throw new IllegalArgumentException(String.format("Parameter[0]: GameType %s not found, please try again"
                    , cliArgs[0]));
        }


        try {
            gameSize = Integer.parseInt(cliArgs[1]);
        } catch (NumberFormatException nfex) {
            NumberFormatException nfx = new NumberFormatException(
                    String.format("Parameter[1]: %s is not a valid Integer", cliArgs[1]));
            nfx.initCause(nfex);
            throw nfx;
        }

        if ((gameSize < 17 || gameSize > 21) || gameSize % 2 != 0) {
            throw new IllegalArgumentException(
                    "Parameter[1]: Size of game is not in definded range (17,21) or is not even");
        }

        try {
            playerSize = Integer.parseInt(cliArgs[2]);
        } catch (NumberFormatException nfex) {
            NumberFormatException nfx = new NumberFormatException(
                    String.format("Parameter[2]: %s is not a valid Integer", cliArgs[2]));
            nfx.initCause(nfex);
            throw nfx;
        }


        if (playerSize < 2 || playerSize > 4) {
            throw new IllegalArgumentException(
                    String.format("Parameter[2]: Number of players in not between 2 and 4; given value: %s"
                            , playerSize));
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

    /**
     * Starts loop for processing Terminal input commands.
     * All input from Terminal will be passed and processed in ArgumentParser.
     * Loop will end when Command 'quit' entered.
     */
    public void processCommands() {

        while (!quit) {
            String line = Terminal.readLine();
            try {
                String result = parser.parse(line);
                if (result != null) Terminal.printLine(result);
            } catch (ArgumentParserExecutionException apeex) {
                Exception clientException = apeex.getTypedCause();
                Terminal.printError(String.format("[ArgumentParserExecutionException][%s] :: %s"
                        , clientException.getClass().getName(), clientException.getMessage()));

            } catch (ArgumentParserException apex) {

                Terminal.printError(String.format("[ArgumentParserException] :: %s", apex.getMessage()));

            } catch (IllegalArgumentException x) {
                Terminal.printError(String.format("[IllegalArgumentException][%s] :: %s",
                        x.getClass().getName(), x.getMessage()));
            } catch (IntervalViolationException e) {
                Terminal.printError(String.format("[IntervalViolationException] : %s", e.getMessage()));
            }
        }
    }
    //endregion

    //region Methods


    //region place

    /**
     * CommandFor: place
     * <p>
     * Places two player tokens on game board.
     * Token positions are identified by it's coordinates (row and column index)
     *
     * @param rowNo1    Index of row of first token
     * @param columnNo1 Index of column of first token
     * @param rowNo2    Index of row of second token
     * @param columnNo2 Index of column of second token
     * @return Status of placement, returs OK if success
     * @throws Connect6GameException is thrown when there is a problem due placing tokens by any player
     */
    @CommandInfo(command = "place (\\-?\\d+);(\\-?\\d+);(\\-?\\d+);(\\-?\\d+)")
    public String place(int rowNo1, int columnNo1, int rowNo2, int columnNo2) throws Connect6GameException {


        if (type == GameFieldType.STANDARD && (rowNo1 < 0 || columnNo1 < 0 || rowNo2 < 0 || columnNo2 < 0)) {
            throw new IllegalArgumentException("In 'standard' mode are negative agruments not allowed");
        }

        if (game.getWinner() != null && !game.getWinner().equals(Player.NONE)) {
            throw new Connect6GameException(String.format("There is already a winner: %s won"
                    , game.getWinner().toString()));
        } else if (game.getWinner() != null && game.getWinner().equals(Player.NONE)) {
            throw new Connect6GameException(String.format("There is no winner winner, game end with DRAW"
                    , game.getWinner().toString()));
        }

        CellPosition pos1 = new CellPosition(rowNo1, columnNo1);
        CellPosition pos2 = new CellPosition(rowNo2, columnNo2);
        boolean success = game.place(players[playerIndex], pos1, pos2);

        if (!game.getBoard().hasFreeRows() && success) {
            game.setWinner(Player.NONE);
            return "draw";
        }

        if (success && (game.checkWinner(pos1, players[playerIndex])
                || game.checkWinner(pos2, players[playerIndex]))) {
            game.setWinner(players[playerIndex]);
            return String.format("%s wins", players[playerIndex]);
        }

        playerIndex = (playerIndex + 1) % (playerSize); //set Next player

        return "OK";
    }
    //endregion


    //region rowprint

    /**
     * CommandFor: rowprint
     * Prints given row as String
     *
     * @param rowNo Index of row to print
     * @return String representation of given row
     */
    @CommandInfo(command = "rowprint (\\d+)")
    public String rowprint(int rowNo) {

        return game.getRow(rowNo).toString();
    }
    //endregion


    //region colprint

    /**
     * Prints the given column (Index) as String
     *
     * @param colNo Index of column to print
     * @return string representation of given column
     */
    @CommandInfo(command = "colprint (\\d+)")
    public String colprint(int colNo) {
        return game.printColumn(colNo);
    }
    //endregion

    //region print

    /**
     * Prints entire gameBoard as string
     *
     * @return string representation of GameBoard
     */
    @CommandInfo(command = "print")
    public String print() {
        return game.getBoard().toString();
    }
    //endregion


    //region state

    /**
     * CommandFor: state
     * <p>
     * Prints the state of given cell
     *
     * @param rowNum RowIndex of Cell for state
     * @param colNum ColumnIndex of Cell for state
     * @return Status of cell (** if it's empty and P1-P4 if it's used by player)
     */
    @CommandInfo(command = "state (\\-?\\d+);(\\-?\\d+)")
    public String state(int rowNum, int colNum) {

        CellPosition position = new CellPosition(rowNum, colNum);
        if (!position.isAcceptedPosition(type, gameSize)) {
            throw new IllegalArgumentException("Given position is not accepted for 'state'");
        }

        if (type == GameFieldType.TORUS) position = position.convertToNormal(gameSize);

        return game.getBoard().getCell(position).getPlayer().toString();
    }
    //endregion

    //region reset

    /**
     * CommandFor: reset gameBoard
     * Resets complete game to defaults
     *
     * @return OK if reset was successful
     */
    @CommandInfo(command = "reset")
    public String reset() {
        playerIndex = 0;
        game.reset();
        return "OK";
    }
    //endregion

    //region quit

    /**
     * CommandFor: quit -> exit loop
     * Exists the command process loop and ends program
     */
    @CommandInfo(command = "quit")
    public void quit() {
        this.quit = true;
    }
    //endregion


    //endregion
}
