package de.plpt.Connect6;

import de.plpt.Connect6.exceptions.Connect6GameException;

public class Connect6Game {

    //region varDef
    private GameFieldType fieldType;
    private final int gameSize;
    GameBoard board;
    private Player winner =null;
    //endregion

    //region constructor
    public Connect6Game(GameFieldType type, int gameSize) {
        board = new GameBoard(gameSize);
        this.gameSize = gameSize;
        this.fieldType = type;
    }
    //endregion

    //region place
    public boolean place(Player currPlayer, int rowNo1, int columnNo1, int rowNo2, int columnNo2) throws Connect6GameException {

        if (winner !=null || !board.hasFreeRows()) {
            return false;
        }

        Cell c1 = null;
        Cell c2 = null;
        if (fieldType == GameFieldType.STANDARD) {
            c1 = board.getCell(rowNo1, columnNo1);
            c2 = board.getCell(rowNo2, columnNo2);
        } else if (fieldType == GameFieldType.TORUS) {
            c1 = board.getCell(rowNo1 % gameSize, columnNo1 % gameSize);
            c2 = board.getCell(rowNo2 % gameSize, columnNo2 % gameSize);
        } else {
            throw new IllegalStateException("There is no GameType defined");
        }

        if (c1.equals(c2)) {
            throw new Connect6GameException(String.format("%s wanted to place second token on same position %s like first. Try again.", currPlayer, c1.getPosition()));
        }


        if (!c1.isEmpty()) {
            throw new Connect6GameException(String.format("Cell at %s is already used by %s", c1.getPosition(), c1.getPlayer()));
        } else if (!c2.isEmpty()) {
            throw new Connect6GameException(String.format("Cell at %s is already used by %s", c1.getPosition(), c1.getPlayer()));
        }

        c1.setPlayer(currPlayer);
        c2.setPlayer(currPlayer);

        return true;
    }
    //endregion

    //region getRow
    public Row getRow(int idx) {
        return board.getRow(idx);
    }
    //endregion

    //region getBoard
    public GameBoard getBoard() {
        return board;
    }
    //endregion

    //region printColumn
    public String printColumn(int colNo) {
        StringBuilder builder = new StringBuilder();
        for (Cell cell : board.getCells(colNo)) {
            builder.append(cell.getPlayer().toString());
            builder.append(" ");
        }
        return builder.toString().trim();
    }
    //endregion

    //region checkWinner
    public boolean checkWinner(int row, int clm, Player player) {

        boolean result = false;
        switch (fieldType) {
            case TORUS:
                result = checkWinnerTorusMode(row, clm, player);
                break;

            case STANDARD:
                result = checkWinnerDefaultMode(row, clm, player);
                break;
            default:
                return false;
        }

        if (result) winner = player;

        return result;

    }
    //endregion

    //region getWinner
    public Player getWinner() {
        return winner;
    }
    //endregion

    //region checkWinner
    private boolean checkWinnerDefaultMode(int row, int clm, Player player) {

        Cell c = board.getCell(row, clm);

        if (!c.getPlayer().equals(player)) {
            throw new IllegalStateException("Given cell owner isn't given Player");
        }


        int count = 0;
        for (Cell cell : board.getCells(clm)) {

            if (cell.getPlayer().equals(player)) {
                count++;
            } else {
                count = 0;
            }

            if (count >= 6) return true;

        }

        count = 0;
        for (Cell cell : board.getRow(row).getCells()) {
            if (cell.getPlayer().equals(player)) {
                count++;
            } else {
                count = 0;
            }

            if (count >= 6) return true;
        }


        count = 0;
        for (Cell cell : board.getDiagonalNE2SW(row, clm)) {
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

    //region checkWinner
    private boolean checkWinnerTorusMode(int row, int clm, Player player) {


        return false;
    }
    //endregion

    //region reset
    public void reset() {
        winner = null;
        board.reset();
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
    //endregion
}
