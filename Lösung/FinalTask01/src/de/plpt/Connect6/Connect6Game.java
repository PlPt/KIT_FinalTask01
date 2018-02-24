package de.plpt.Connect6;

import com.google.common.collect.Lists;
import de.plpt.Connect6.exceptions.Connect6GameException;

import java.util.ArrayList;
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
        int count = 0;
        List<Cell> cellList = new ArrayList<Cell>();
        cellList.addAll(board.getCells(realPosition.getCellIndex()));
        if (this.fieldType == GameFieldType.TORUS) cellList.addAll(cellList);
        for (Cell cell : cellList) {
            if (cell.getPlayer().equals(player)) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) return true;
        }
        count = 0;
        cellList = new ArrayList<Cell>();
        cellList.addAll(board.getRow(realPosition.getRowIndex()).getCells());
        if (fieldType == GameFieldType.TORUS) cellList.addAll(cellList);
        for (Cell cell : cellList) {
            if (cell.getPlayer().equals(player)) {
                count++;
            } else {
                count = 0;
            }

            if (count >= 6) return true;
        }
        count = 0;
        CellPosition next;
        int t1 = realPosition.getRowIndex();
        int t2 = realPosition.getCellIndex();
        t2 = t2 - t1;
        t1 = 0;
        next = new CellPosition(-1, t2 - 1).convertToNormal(gameSize);

        CellPosition nexxt = new CellPosition(-2, next.getCellIndex() - next.getRowIndex() - 2)
                .convertToNormal(gameSize);
        boolean isSameDig = nexxt.equals(realPosition);
        List<Cell> diag1 = new ArrayList<>();
        diag1.addAll(board.getDiagonalNE2SW(realPosition.getRowIndex(), realPosition.getCellIndex()));
        if (fieldType == GameFieldType.TORUS) {
            if (!isSameDig) {
                List<Cell> cells2 = ((board.getDiagonalNE2SW(next.getRowIndex(), next.getCellIndex())));
                diag1 = Lists.reverse(diag1);
                for (Cell cc : cells2) {
                    if (!diag1.contains(cc)) {
                        diag1.add(cc);
                    }
                }
            } else {
                diag1.addAll(diag1);
            }
        }
        //  System.out.println("diag1");
        for (Cell cell : diag1) {
            //  System.out.println("[" + cell.getPosition() + "]" + cell.getPlayer());
            if (cell.getPlayer().equals(player)) {
                count++;
            } else {
                count = 0;
            }
            if (count >= 6) return true;
        }
        count = 0;
        List<Cell> diag2 = new ArrayList<>();
        diag2.addAll(board.getDiagonalNW2SE(realPosition.getRowIndex(), realPosition.getCellIndex()));
        if (fieldType == GameFieldType.TORUS) {
            if (!isSameDig) {
                List<Cell> cells2 = ((board.getDiagonalNW2SE(next.getRowIndex(), next.getCellIndex())));
                diag2 = Lists.reverse(diag2);
                for (Cell cc : cells2) {
                    if (!diag2.contains(cc)) {
                        diag2.add(cc);
                    }
                }
            } else {
                diag2.addAll(diag2);
            }
        }
        //System.out.println("diag2");
        for (Cell cell : diag2) {
            //  System.out.println("[" + cell.getPosition() + "]" + cell.getPlayer());
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
}
