package de.plpt.Connect6;

import java.util.ArrayList;
import java.util.List;

public class Row {

    //region varDef
    private List<Cell> cellList;
    private int rowPos;
    //endregion

    //region constructor

    /**
     * Initializes a new Row
     *
     * @param size   GameField Size
     * @param rowPos current Row position
     */
    public Row(int size, int rowPos) {
        cellList = new ArrayList<Cell>();
        this.rowPos = rowPos;
        for (int i = 0; i < size; i++) {
            cellList.add(new Cell(rowPos, i));
        }
    }
    //endregion

    //region getCells

    /**
     * Returns a List of Cells in current row
     *
     * @return List of cells in Row
     */
    public List<Cell> getCells() {
        return cellList;
    }
    //endregion

    //region get

    /**
     * Returns a cell at specific CellIndex
     *
     * @param cellIndex Index of Cell
     * @return Cell at given Index
     */
    public Cell get(int cellIndex) {
        return cellList.get(cellIndex);
    }
    //endregion

    //region toString

    /**
     * Returns String representation of Row object
     *
     * @return Row as string
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (Cell cell : cellList) {
            builder.append(cell.getPlayer());
            builder.append(" ");
        }

        return builder.toString().trim();
    }
    //endregion

    //region hasFreeCells

    /**
     * Indicates whether row has any free cell
     *
     * @return true if there is any free cell in this row
     */
    public boolean hasFreeCells() {

        for (Cell c : cellList) {
            if (c.isEmpty()) return true;
        }

        return false;
    }
    //endregion

    //region reset

    /**
     * Resets all Cells in this row to defaults
     */
    public void reset() {
        for (Cell cell : cellList) {
            cell.setPlayer(Player.NONE);
        }
    }
    //endregion
}
