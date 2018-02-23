package de.plpt.Connect6;

import java.util.ArrayList;
import java.util.List;

public class Row {

    //region varDef
    private List<Cell> cellList;
    private int rowPos;
    //endregion

    //region constructor
    public Row(int size,int rowPos){
        cellList = new ArrayList<Cell>();
        this.rowPos = rowPos;
        for (int i = 0; i < size; i++) {
            cellList.add(new Cell(rowPos,i));
        }
    }
    //endregion

    //region getCells
    public List<Cell> getCells() {
        return cellList;
    }
    //endregion

    //region get
    public Cell get(int i){
        return cellList.get(i);
    }
    //endregion

    //region toString
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (Cell cell: cellList) {
            builder.append(cell.getPlayer());
            builder.append(" ");
        }

        return builder.toString().trim();
    }
    //endregion

    //region hasFreeCells
    public boolean hasFreeCells(){

        for (Cell c:cellList) {
            if(c.isEmpty()) return true;
        }

        return false;
    }
    //endregion

    //region reset
    public void reset() {
        for (Cell cell:cellList) {
            cell.setPlayer(Player.NONE);
        }
    }
    //endregion
}
