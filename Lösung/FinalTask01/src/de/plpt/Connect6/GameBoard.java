package de.plpt.Connect6;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    //region varDef
    private List<Row> rows;
    //endregion

    //region constructor

    /**
     * Initialize a new GameBoard with current GameSize
     *
     * @param size Size of game
     */
    public GameBoard(int size) {
        this.rows = new ArrayList<Row>();
        for (int i = 0; i < size; i++) {
            rows.add(new Row(size, i));
        }
    }
    //endregion

    //region getRow

    /**
     * Returns a row at given index
     *
     * @param idx Index of Row
     * @return Row at specific index
     */
    public Row getRow(int idx) {
        return rows.get(idx);
    }
    //endregion

    //region getCell

    /**
     * Returns cell at specific Row and column index
     *
     * @param row    RowIndex fo cell
     * @param column ColumnIndex of cell
     * @return Cell at given Position
     */
    public Cell getCell(int row, int column) {
        return rows.get(row).get(column);
    }

    /**
     * Returns cell at given CellPosition
     *
     * @param position Position of Cell
     * @return Cell at given Position
     */
    public Cell getCell(CellPosition position) {
        return getCell(position.getRowIndex(), position.getCellIndex());
    }
    //endregion

    //region reset

    /**
     * Resets game board to defaults
     */
    public void reset() {
        for (Row row : rows) {
            row.reset();
        }
    }
    //endregion

    //region getDiagonalNe2SW

    /**
     * Returns a List of diagonal Cells at given Position in NE2SW direction
     *
     * @param row Index of row
     * @param clm Index of cell
     * @return List of cells with are in selected diagonal
     */
    public List<Cell> getDiagonalNE2SW(int row, int clm) {
        List<Cell> c = new ArrayList<Cell>();


        for (int i = (-1) * rows.size() - 1; i < 0; i++) {

            if (row + i >= 0 && clm + i >= 0 && row + i < rows.size() && clm + i < rows.size())
                c.add(getCell(row + i, clm + i));
        }


        for (int i = 0; i < rows.size(); i++) {

            if (row + i < rows.size() && clm + i < rows.size())
                c.add(getCell(row + i, clm + i));

        }


        return c;
    }
    //endregion

    //region getDiagonalNW2SE

    /**
     * Returns a List of diagonal Cells at given Position in NW2SE direction
     *
     * @param row Index of row
     * @param clm Index of cell
     * @return List of cells with are in selected diagonal
     */
    public List<Cell> getDiagonalNW2SE(int row, int clm) {
        List<Cell> c = new ArrayList<Cell>();


        for (int i = (-1) * rows.size() - 1; i < 0; i++) {

            if (row + i >= 0 && clm - i >= 0 && row + i < rows.size() && clm - i < rows.size())
                c.add(getCell(row + i, clm - i));


        }

        for (int i = 0; i < rows.size(); i++) {

            if (row + i < rows.size() && clm - i < rows.size() && clm - i >= 0)
                c.add(getCell(row + i, clm - i));

        }


        return c;
    }
    //endregion

    //region hasFreeRows

    /**
     * Indicates whether the board has free rows or cells
     *
     * @return true if there is mimimum one cell free
     */
    public boolean hasFreeRows() {

        for (Row row : rows) {
            if (row.hasFreeCells()) return true;
        }

        return false;
    }
    //endregion

    //region getCells

    /**
     * Retuns a List of cells for given column
     *
     * @param clmIdx ColumnIndex to get as List
     * @return List of Cells in given Column
     */
    public List<Cell> getCells(int clmIdx) {
        List<Cell> cells = new ArrayList<Cell>();

        for (Row row : rows) {
            cells.add(row.get(clmIdx));
        }
        return cells;
    }
    //endregion

    //region toString

    /**
     * Returns a String representation of GameBoard object
     * It's the string of the complete board
     *
     * @return String repr. of board
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Row row : rows) {
            builder.append(row.toString());
            builder.append("\n");
        }
        return builder.toString().trim();
    }
    //endregion


}
