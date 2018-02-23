package de.plpt.Connect6;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    //region varDef
    private List<Row> rows;
    //endregion

    //region constructor
    public GameBoard(int size) {
        this.rows = new ArrayList<Row>();
        for (int i = 0; i < size; i++) {
            rows.add(new Row(size, i));
        }
    }
    //endregion

    //region getRow
    public Row getRow(int i) {
        return rows.get(i);
    }
    //endregion

    //region getCell
    public Cell getCell(int row, int column) {
        return rows.get(row).get(column);
    }
    //endregion

    //region reset
    public void reset() {
        for (Row row : rows) {
            row.reset();
        }
    }
    //endregion

    //region getDiagonalNe2SW
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

    //region toString
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

    //region hasFreeRows
    public boolean hasFreeRows() {

        for (Row row : rows) {
            if (row.hasFreeCells()) return true;
        }

        return false;
    }
    //endregion

    //region getCells
    public List<Cell> getCells(int clmIdx) {
        List<Cell> cells = new ArrayList<Cell>();

        for (Row row : rows) {
            cells.add(row.get(clmIdx));
        }
        return cells;
    }
    //endregion
}
