package de.plpt.Connect6;


import de.plpt.Connect6.exceptions.Connect6GameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Connect6Game {

    //region varDef
    private GameFieldType fieldType;
    private final int gameSize;
    private GameBoard board;
    private Player winner = null;
    //endregion


    //region constructor

    /**
     * Initializes a new Connect6Game instance with gameType and gameSize
     *
     * @param type     GameType for define rules which can be standard or torus
     * @param gameSize Size of gameField matrix
     */
    public Connect6Game(GameFieldType type, int gameSize) {
        board = new GameBoard(gameSize);
        this.gameSize = gameSize;
        this.fieldType = type;
    }
    //endregion

    //region place

    /**
     * Places  two game tokens on board at given CellPositions
     *
     * @param currPlayer player to who the tokens belong
     * @param position1  Position for token No. 1
     * @param position2  Position for token No. 2
     * @return true if placement succeeded, false otherwise
     * @throws Connect6GameException is thrown when something with the placement is goning wrong
     *                               e.g. inacceptable Positions
     */
    public boolean place(Player currPlayer, CellPosition position1, CellPosition position2)
            throws Connect6GameException {

        if (!position1.isAcceptedPosition(fieldType, gameSize) || !position2.isAcceptedPosition(fieldType, gameSize)) {
            throw new Connect6GameException("Given positions are not valid in this game type");
        }

        if (winner != null || !board.hasFreeRows()) {
            return false;
        }

        Cell c1 = null;
        Cell c2 = null;
        if (fieldType == GameFieldType.STANDARD) {
            c1 = board.getCell(position1);
            c2 = board.getCell(position2);
        } else if (fieldType == GameFieldType.TORUS) {
            c1 = board.getCell(position1.convertToNormal(gameSize));
            c2 = board.getCell(position2.convertToNormal(gameSize));
        } else {
            throw new IllegalStateException("There is no GameType defined");
        }

        if (c1.equals(c2)) {
            throw new Connect6GameException(
                    String.format("%s wanted to place second token on same position %s like first. Try again."
                            , currPlayer, c1.getPosition()));
        }


        if (!c1.isEmpty()) {
            throw new Connect6GameException(String.format("Cell at %s is already used by %s"
                    , c1.getPosition(), c1.getPlayer()));
        } else if (!c2.isEmpty()) {
            throw new Connect6GameException(String.format("Cell at %s is already used by %s"
                    , c1.getPosition(), c1.getPlayer()));
        }

        c1.setPlayer(currPlayer);
        c2.setPlayer(currPlayer);

        return true;
    }
    //endregion

    //region getRow

    /**
     * Returns the game row at given index
     *
     * @param idx Index of row to get
     * @return Row at given Index
     */
    public Row getRow(int idx) {
        return board.getRow(idx);
    }
    //endregion

    //region getBoard

    /**
     * Returns the GameBoard instance for this game
     *
     * @return GameBoard instance
     */
    public GameBoard getBoard() {
        return board;
    }
    //endregion

    //region printColumn

    /**
     * Prints given column as string
     *
     * @param colNo ColumnIndex
     * @return String representation of the given column
     */
    public String printColumn(int colNo) {
        StringBuilder builder = new StringBuilder();
        for (Cell cell : board.getCells(colNo)) {
            builder.append(cell.getPlayer().toString());
            builder.append(" ");
        }
        return builder.toString().trim();
    }
    //endregion

    //region getWinner

    /**
     * Returns the current winner as Player object
     * Returnal can be null if there is no winner and can be Player.NONE if the game end in a draw
     *
     * @return winner of current game
     */
    public Player getWinner() {
        return winner;
    }
    //endregion

    //region checkWinner

    /**
     * Identifies if if the changed cell helps the current player to win the game
     *
     * @param position last changed Cell
     * @param player   player to check win
     * @return true if the player won
     */
    public boolean checkWinner(CellPosition position, Player player) {

        CellPosition realPosition = position;
        if (fieldType == GameFieldType.TORUS) realPosition = position.convertToNormal(gameSize);


        Cell c = board.getCell(realPosition);

        if (!c.getPlayer().equals(player)) {
            throw new IllegalStateException("Given cell owner isn't given Player");
        }


        boolean checkHorizontal = checkLine(player, realPosition, new ILineDataProvider() {
            @Override
            public List<Cell> getLine(CellPosition realPosition) {
                return board.getCells(realPosition.getCellIndex());
            }
        });

        if (checkHorizontal) return true;


        boolean checkVertical = checkLine(player, realPosition, new ILineDataProvider() {
            @Override
            public List<Cell> getLine(CellPosition realPosition) {
                return board.getRow(realPosition.getRowIndex()).getCells();
            }
        });

        if (checkVertical) return true;


        CellPosition next = new CellPosition(-1, realPosition.getCellIndex()
                - realPosition.getRowIndex() - 1).convertToNormal(gameSize);


        boolean ne2SWDiag = checkDiagonal(player, realPosition, next, new ILineDataProvider() {
            @Override
            public List<Cell> getLine(CellPosition realPosition) {
                return board.getDiagonalNE2SW(realPosition.getRowIndex(), realPosition.getCellIndex());
            }

        });

        if (ne2SWDiag) return true;

        boolean nw2SEDiag = checkDiagonal(player, realPosition, next, new ILineDataProvider() {
            @Override
            public List<Cell> getLine(CellPosition realPosition) {
                return board.getDiagonalNW2SE(realPosition.getRowIndex(), realPosition.getCellIndex());
            }

        });

        if (nw2SEDiag) return true;


        return false;
    }
//endregion

    //region checkLine

    /**
     * Checks if player is winner on horizontal or vertical line, depending on implementation of ILineDataProvider
     *
     * @param player           Player to check if it's the winner
     * @param realPosition     CellPosition of cell to check it's line
     * @param lineDataProvider DataProvider for Cell data
     * @return true if given Player won in this line, false otherwise
     */
    private boolean checkLine(Player player, CellPosition realPosition, ILineDataProvider lineDataProvider) {
        int count = 0;

        List<Cell> cellList = new ArrayList<Cell>();
        cellList.addAll(lineDataProvider.getLine(realPosition));
        if (this.fieldType == GameFieldType.TORUS) cellList.addAll(cellList);

        for (Cell cell : cellList) {
            if (cell.getPlayer().equals(player)) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) return true;
        }
        return false;
    }
    //endregion

    //region checkDiagonal

    /**
     * Checks if player is  winner in a diagonal line
     * Type of diagonal depends on ILineProvider implementation
     *
     * @param player               Player to check win
     * @param realPositin          Position of cell to get it's diagonal
     * @param nextPosition         Position of next diagonal cell
     * @param diagonalDataProvider DataProvider for diagonalData
     * @return true if given player won, false otherwise
     */
    private boolean checkDiagonal(Player player, CellPosition realPositin, CellPosition nextPosition,
                                  ILineDataProvider diagonalDataProvider) {
        int count = 0;
        List<Cell> diag1 = new ArrayList<Cell>();
        diag1.addAll(diagonalDataProvider.getLine(realPositin));
        List<Cell> cells2 = (diagonalDataProvider.getLine(nextPosition));
        boolean isSameDig = new HashSet<>(diag1).equals(new HashSet<>(cells2));
        if (fieldType == GameFieldType.TORUS) {
            if (!isSameDig) {
                Collections.reverse(diag1);
                if (cells2.get(cells2.size() - 1).getPlayer().equals(player)) Collections.reverse(cells2);
                for (Cell cc : cells2) {
                    if (!diag1.contains(cc)) {
                        diag1.add(cc);
                    }
                }
            } else {
                diag1.addAll(diag1);
            }
        }
        for (Cell cell : diag1) {

            if (cell.getPlayer().equals(player)) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) return true;
        }
        return false;
    }
    //endregion

    //region reset

    /**
     * Resets a game and it's gameBoard
     */
    public void reset() {
        winner = null;
        board.reset();
    }
    //endregion

    //region setWinner

    /**
     * Sets a player as GameWinner
     *
     * @param winner player to set as winner
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }
    //endregion

    //region ILineDataProvider

    /**
     * DataProvider Interface for all Line checks
     */
    interface ILineDataProvider {
        /**
         * Returns a List of cells in a specific line on board
         * List content depends on implementation
         *
         * @param realPosition Position of Cell in line
         * @return List of cells in specific lines
         */
        List<Cell> getLine(CellPosition realPosition);
    }
    //endregion
}
